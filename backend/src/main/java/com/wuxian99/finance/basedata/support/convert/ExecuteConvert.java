package com.wuxian99.finance.basedata.support.convert;

import com.wuxian99.finance.basedata.domain.model.ExecuteInfo;
import com.wuxian99.finance.basedata.domain.model.MetadataInfo;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.system.MetadataService;
import com.wuxian99.finance.basedata.service.system.impl.UploadFileService;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;
import com.wuxian99.finance.basedata.support.util.ClassUtil;
import com.wuxian99.finance.common.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Component
public class ExecuteConvert {
    @Autowired
    ApplicationContext context;
	@Autowired
    MetadataService metadataService;
	@Autowired
	EntityManager entityManager;
	@Autowired
	UploadFileService uploadFileService;
	static final String DEFAULT_JSR303_MSG = "系统JSR303验证失败, 包含未配置消息";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ExecuteInfo convert(Command source, SigninUser signinUser) {
		ExecuteInfo info = new ExecuteInfo();
		info.setAction(source.getAction());

		MetadataInfo metadata = metadataService.findByModule(source.getModule());
		if (metadata == null) {
			throw new IllegalStateException(String.format(
					"cannot find metadata,please check table METADATA.module_name = %s exists?", source.getModule()));
		}
		info.setMetadata(metadata);
		info.setRepository(metadata.getRepositoryInstance());

		try {
			if (source.isQuery()) {
				QueryCommand command = (QueryCommand) source;
				Example example = constructExample(metadata, command, signinUser);

				List<OrderCommand> orderCommandList = command.getOrder();
				List<Sort.Order> orderList = new ArrayList<>();
				for (OrderCommand oc : orderCommandList) {
					orderList.add(new Sort.Order(Sort.Direction.fromString(oc.getDir()), oc.getColumn()));
				}
				PageRequest pageRequest = new PageRequest(command.getStart() / command.getLength(), command.getLength(),
						new Sort(orderList));
				info.setArgs(new Object[] { example, pageRequest });
			} else {
				CUDCommand command = (CUDCommand) source;
				Map<String, Map<String, Object>> dataMap = command.getData();
				Collection<Map<String, Object>> dataMapValues = dataMap.values();
				List dataList = new ArrayList<>();
                // 2.获取repository实例
                Object repository = context.getBean(info.getRepository());
                EntityType<?> entityType = entityManager.getMetamodel().entity(ClassUtils.getClass(metadata.getEntityClass()));
                SingularAttribute<?, ?> id = entityType.getId(entityType.getIdType().getJavaType());
                String idName = id.getName();
                for (Map<String, Object> value : dataMapValues) {
					Object entity = ClassUtil.getEntity(metadata.getEntityClass());
                    ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
                    ConvertUtils.register(new DateConverter(null), Date.class);

					//仅当edit时候执行
                    if("edit".equalsIgnoreCase(command.getPreAction()) && PropertyUtils.isWriteable(entity,idName) && value.get(idName) != null && PropertyUtils.isWriteable(entity,"department")){
                        Object condition = ClassUtil.getEntity(metadata.getEntityClass());
                        BeanUtils.setProperty(condition,idName,value.get(idName));
                        BeanUtils.setProperty(condition,"department",signinUser.getDepartment());

                        Object original = ClassUtil.invoke(repository, "findOne", new Object[]{Example.of(condition)});
                        BeanUtils.populate(original, value);
                        dataList.add(original);
                    }else{
                        BeanUtils.populate(entity, value);
                        dataList.add(entity);
                    }

					Map<String, UploadRef> uploadRefMap = metadataService.getEntityUploadRefMap().get(metadata.getEntityJavaType().getSimpleName());
					if(uploadRefMap != null){
						List<Integer> ids = new ArrayList<>();
						for(String key : uploadRefMap.keySet()){
							String property = BeanUtils.getProperty(entity, key);
							ids.add(Integer.parseInt(property));
						}
						Map<String,UploadFileInfo> uploadFileInfoMap = uploadFileService.findByIds(ids);
						for(String key : uploadRefMap.keySet()){
							UploadRef uploadRef = uploadRefMap.get(key);
							if(PropertyUtils.isWriteable(entity,uploadRef.ref())){
								String property = BeanUtils.getProperty(entity, key);
								UploadFileInfo uploadFileInfo = uploadFileInfoMap.get(property);
								PropertyUtils.setProperty(entity,uploadRef.ref(),uploadFileInfo.getRelative_path());
							}
						}
					}
				}
				info.setVerificationResult(validate(dataList));
				info.setArgs(new Object[] { dataList });
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("command args convert excuteInfo exception!");
		}

		return info;
	}

	/**
	 * @param list
	 * @return
	 */
	private String validate(List<?> list) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		int size = list.size();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < size; i++){
			String result = validateOne(validator, list.get(i));
			if(!StringUtils.isEmpty(result)){
				buffer.append("list[" + i + "]:"+result+";");
			}
		}
		return buffer.toString();
	}

	/**
	 * @param validator
	 * @param entity
	 * @return
	 */
	private String validateOne(Validator validator, Object entity) {
		Set<ConstraintViolation<Object>> violations = validator.validate(entity);
		if (violations.size() == 0)
			return "";
		StringBuffer buf = new StringBuffer();
		for (ConstraintViolation<Object> violation : violations) {
			if (StringUtils.isEmpty(violation.getMessage()))
				buf.append(DEFAULT_JSR303_MSG + "\n");
			buf.append(violation.getMessage() + "\n");
		}
		return buf.toString();
	}

	/**
	 * @param entity
	 * @throws ClassNotFoundException
	 */
	// private void setUserInfo(Object entity, boolean isNew, MetadataInfo
	// metadata) throws ClassNotFoundException {
	// for (PropertyDescriptor pd : metadata.getPds()) {
	// if (pd.getName().equals(Constants.USER_CREATE) && isNew) {
	// ReflectionUtils.invokeMethod(pd.getWriteMethod(), entity, new Object[] {
	// Constants.DEFAULT_USER });
	// } else if (pd.getName().equals(Constants.USER_CREATE_TIME) && isNew) {
	// ReflectionUtils.invokeMethod(pd.getWriteMethod(), entity, new Object[] {
	// new Timestamp(System.currentTimeMillis()) });
	// } else if (pd.getName().equals(Constants.USER_EDIT) && !isNew) {
	// ReflectionUtils.invokeMethod(pd.getWriteMethod(), entity, new Object[] {
	// Constants.DEFAULT_USER });
	// } else if (pd.getName().equals(Constants.USER_EDIT_TIME) && !isNew) {
	// ReflectionUtils.invokeMethod(pd.getWriteMethod(), entity, new Object[] {
	// new Timestamp(System.currentTimeMillis()) });
	// }
	// }
	// }

	/**
	 * @param entity
	 * @param command
	 * @param signinUser
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("rawtypes")
	private Example constructExample(MetadataInfo metadata, QueryCommand command, SigninUser signinUser) throws Exception {
		Object entity = ClassUtil.getEntity(metadata.getEntityClass());
		if (command.getColumns() == null) 
			return Example.of(entity);
		

		// 2. 处理QueryCloumn
		for (SubQueryCommand column : command.getColumns()) {
			PropertyDescriptor pd = ClassUtil.findProperty(metadata.getPds(), column.getData());
			if (notWriteable(pd, column)) 
				continue;
			Class<?> parameterType = PropertyUtils.getPropertyType(entity, column.getData());
			if(parameterType == null){
				throw new IllegalStateException(String.format("there is no setter/getter for property=%s",column.getData()));
			}
			// 优先找String类型匹配
			if(column.getNullable() != null) {
				if(column.getNullable()){
					PropertyUtils.setProperty(entity, column.getData(), null);
				}else{
					PropertyUtils.setProperty(entity, column.getData(), "%");
				}
			} else if (String.class.equals(parameterType)) {
				PropertyUtils.setProperty(entity, column.getData(), column.getSearch().getValue());
			} else {
				Constructor<?> constructor = ConstructorUtils.getAccessibleConstructor(parameterType, String.class);
				if (constructor != null) {
					PropertyUtils.setProperty(entity, column.getData(),
							constructor.newInstance(column.getSearch().getValue()));
				}
			}
		}

		Set<String> ingorePaths = constructIngorePaths(metadata, command);//不参与where条件的拼接
		//当前用户有所属部门
		if(!StringUtils.isEmpty(signinUser.getDepartment())){
			if(PropertyUtils.isWriteable(entity, "department")){
				PropertyUtils.setProperty(entity, "department", signinUser.getDepartment());
			}
		}else {
			if(!ingorePaths.contains("department")){
				ingorePaths.add("department");//没有所属部门的用户  department不作为where条件
			}
		}
		String[] ingorePathArr = ingorePaths.toArray(new String[ingorePaths.size()]);
		ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues().withIgnorePaths(ingorePathArr);
		matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
		matcher = matcher.withMatcher("id", ExampleMatcher.GenericPropertyMatchers.contains());
		matcher = matcher.withMatcher("unifiedId", ExampleMatcher.GenericPropertyMatchers.contains());

		return Example.of(entity, matcher);
	}
	
	/**
	 * @param pd
	 * @param column
	 * @return
	 */
	private boolean notWriteable(PropertyDescriptor pd, SubQueryCommand column) {
		return (column.getData() == null) || (pd != null && !pd.getPropertyType().equals(String.class)
				&& ("").equals(column.getSearch().getValue()));
	}

	/**
	 * @param metadata
	 * @param command
	 * @return
	 */
	private Set<String> constructIngorePaths(MetadataInfo metadata, QueryCommand command) {
		Set<String> ingorePaths = new HashSet<>();
		// 1. 初始,所有空字段都忽略
		ingorePaths.addAll(getNotQueryColumns(metadata, command.getColumns()));
		// 2. nullable不忽略,where xxx is null
		ingorePaths.removeAll(getNullQueryIngoreColumns(command.getColumns()));
		// 3. 默认不忽略部门
		ingorePaths.remove("department");
		ingorePaths.remove(null);  // 去空
		return ingorePaths;
	}

	/**
	 * 非前端查询字段
	 * 
	 * @param metadata
	 * @param columns
	 * @return
	 */
	private Set<String> getNotQueryColumns(MetadataInfo metadata, List<SubQueryCommand> columns) {
		Set<String> notQueryProperties = new HashSet<>();
		Map<String,String> map = new HashMap<>();

		for (SubQueryCommand column : columns) {
			if(column.getSearch() != null){
				map.put(column.getData(),column.getSearch().getValue());
			}
		}

		for(PropertyDescriptor pd : metadata.getPds()){
			if(map.containsKey(pd.getName()) && !StringUtils.isEmpty(map.get(pd.getName()))){
				continue;
			}
			notQueryProperties.add(pd.getName());
		}
		return notQueryProperties;
	}
	
	/**
	 * 忽略的空值
	 * 
	 * @param columns
	 * @return
	 */
	private Set<String> getNullQueryIngoreColumns(List<SubQueryCommand> columns) {
		Set<String> nullQueryIngoreColumns = new HashSet<>();
		for (SubQueryCommand column : columns) {
			if (column == null || column.getSearch() == null || (column.getNullable()!=null && column.getNullable())){
				nullQueryIngoreColumns.add(column.getData());
			}
		}
		return nullQueryIngoreColumns;
	}

	/**
	 * @param pd
	 * @param columns
	 * @return
	 */
	private boolean isQueryProperty(PropertyDescriptor pd, List<SubQueryCommand> columns) {
		for (SubQueryCommand column : columns) {
			if (pd.getName().equals(column.getData()))
				return true;
		}
		return false;
	}

}
