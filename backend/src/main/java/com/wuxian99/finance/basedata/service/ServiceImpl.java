package com.wuxian99.finance.basedata.service;

import com.wuxian99.finance.basedata.domain.entity.system.OperationLogEntity;
import com.wuxian99.finance.basedata.domain.model.ExecuteInfo;
import com.wuxian99.finance.basedata.domain.model.MetadataInfo;
import com.wuxian99.finance.basedata.domain.model.Select;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.repository.system.OperationLogRepository;
import com.wuxian99.finance.basedata.support.annotation.Ddic;
import com.wuxian99.finance.basedata.support.convert.ExecuteConvert;
import com.wuxian99.finance.basedata.support.util.ClassUtil;
import com.wuxian99.finance.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.List;

@Service
public class ServiceImpl implements IService {
	@Autowired
	ApplicationContext context;
	@Autowired
	ExecuteConvert convert;
	@Autowired
    OperationLogRepository operationLogRepository;

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
		if (info.getMetadata().getNeedLog() == IsOrNot.IS.ordinal())
			saveOperateLog(command.getPreAction(), info, obj);
		
		return afterExecute(obj, command);
	}

	private void saveOperateLog(String operateType, ExecuteInfo info, Object postPersist) {
		OperationLogEntity entity = new OperationLogEntity();
		entity.setOperateTable(info.getMetadata().getTableName());
		entity.setOperateType(operateType);
		entity.setPrePersist(info.getArgs()[0].toString());
		entity.setPostPersist(ObjectUtils.isEmpty(postPersist) ? null : postPersist.toString());
		operationLogRepository.save(entity);
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
			result.setRecordsTotal((int) pageResult.getTotalElements());
			result.setRecordsFiltered((int) pageResult.getTotalElements());
			result.setData(pageResult.getContent());
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
