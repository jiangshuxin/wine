package com.wuxian99.finance.basedata.service;

import com.wuxian99.finance.basedata.domain.model.ExecuteInfo;
import com.wuxian99.finance.basedata.domain.model.MetadataInfo;
import com.wuxian99.finance.basedata.domain.model.Select;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.system.MetadataService;
import com.wuxian99.finance.basedata.service.system.impl.UploadFileService;
import com.wuxian99.finance.basedata.support.annotation.Ddic;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;
import com.wuxian99.finance.basedata.support.convert.ExecuteConvert;
import com.wuxian99.finance.basedata.support.util.ClassUtil;
import com.wuxian99.finance.common.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceImpl implements IService {
	@Autowired
	ApplicationContext context;
	@Autowired
	ExecuteConvert convert;
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    MetadataService metadataService;
	/*
	 * 1.组合方法执行要素 2.执行方法返回结果 3.将结果转化为view层Result
	 */
	@Override
	public Result<?> executeCud(CUDCommand command, SigninUser signinUser) {
		ExecuteInfo info = convert.convert(command,signinUser);

		// FIXME 3. JSR-303 BeanValidator校验
		if (!StringUtils.isEmpty(info.getVerificationResult())) {
			CUDResult<Object> result = new CUDResult<Object>();
			result.setSuccess(false);
			result.setErrorMsg(info.getVerificationResult());
			return result;
		}
		// 方法执行
		Object obj = execute(info);

		return afterExecute(obj, command);
	}

	@Override
	public Result<?> executeFind(QueryCommand command, SigninUser signinUser) {
		ExecuteInfo info = convert.convert(command,signinUser);

		// 方法执行
		Object obj = execute(info);
		// 填充数据字典
		fillDdic(obj, info.getMetadata());
		return afterExecute(obj, command);
	}

	@SuppressWarnings("unchecked")
	private Result<?> afterExecute(Object obj, Command command) {
		if (command.isQuery()) {
			Page<?> pageResult = (Page<?>) obj;
			PageableResult result = new PageableResult();
            List<?> content = pageResult.getContent();
            result.setRecordsTotal((int) pageResult.getTotalElements());
			result.setRecordsFiltered((int) pageResult.getTotalElements());
			result.setData(content);

            List<Integer> ids = new ArrayList<>();
            Map<String,UploadRef> uploadRefMap = null;
            for(int i=0;i<content.size();i++){
                Object element = content.get(i);
                if(i == 0){
                    uploadRefMap = metadataService.getEntityUploadRefMap().get(element.getClass().getSimpleName());
                }

                if(uploadRefMap != null && !uploadRefMap.isEmpty()){
                    for(String key : uploadRefMap.keySet()){
                        try {
                            Object property = PropertyUtils.getProperty(element, key);
                            if(property != null){
                                ids.add(Integer.parseInt(property.toString()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            //load upload_file
			Map files = new HashMap<>();
			List list = new ArrayList();
            Map<String,UploadFileInfo> uploadFileInfos = uploadFileService.findByIds(ids);
            //List<UploadFileInfo> uploadFileInfos = uploadFileService.findByIds(ids.toArray(new Integer[0]));

            list.add(uploadFileInfos);
			files.put("files",uploadFileInfos);
			result.setFiles(files);
			return result;
		} else {
			CUDResult<Object> result = new CUDResult<Object>();
			result.setData(obj);
			result.setSuccess(true);
			return result;
		}
	}

	@SuppressWarnings({ "unchecked" })
	private void fillDdic(Object obj, MetadataInfo metadata) {
		List<PropertyDescriptor> pds = ClassUtil.findDdicAnnationProperty(metadata.getPds());
		if (pds.isEmpty())
			return;
		Page<?> pageResult = (Page<?>) obj;
		List<?> content = pageResult.getContent();
		for (Object e : content) {
			for (PropertyDescriptor pd : pds) {
				Ddic ddic = pd.getReadMethod().getAnnotation(Ddic.class);
				String mapFrom = StringUtils.isEmpty(ddic.name())
						? metadata.getTableName() + Constants.UNDERLINE_SEPERATOR + pd.getName() : ddic.name();
				List<Select> items = (List<Select>) metadata.getDdicInfo().get(mapFrom);
				// 找到数据字典K,V
				String ddicKey = (String) ReflectionUtils.invokeMethod(pd.getReadMethod(), e);
				String ddicValue = findDdicValue(ddicKey, items);
				// 设值
				String mapTo = ddic.mapTo();
				ReflectionUtils.invokeMethod(ClassUtil.findProperty(metadata.getPds(), mapTo).getWriteMethod(), e,
						ddicValue);
			}
		}
	}

	/**
	 * @param ddicKey
	 * @param items
	 * @return
	 */
	private String findDdicValue(String ddicKey, List<Select> items) {
		for (Select item : items) {
			if (item.getKey().equals(ddicKey)) {
				return item.getValue();
			}
		}
		throw new IllegalArgumentException("key:: " + ddicKey + " in ddic not exist");
	}

	/**
	 * @param info
	 * @return
	 */
	private Object execute(ExecuteInfo info) {
		// 2.获取repository实例
		Object bean = context.getBean(info.getRepository());
		
		return ClassUtil.invoke(bean, info.getAction(), info.getArgs());
	}

}
