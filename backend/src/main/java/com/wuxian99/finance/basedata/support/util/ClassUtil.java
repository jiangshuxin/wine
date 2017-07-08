package com.wuxian99.finance.basedata.support.util;

import com.wuxian99.finance.basedata.support.annotation.Ddic;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {
	private final static Logger logger = LoggerFactory.getLogger(ClassUtil.class);
	/**
	 * @param entityClass
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object getEntity(String entityClass) {
		try {
			Class<?> clazz = getJavaType(entityClass);
			return clazz.newInstance();
		} catch (Exception e) {
			logger.error(entityClass + " not exist");
			throw new ProviderNotFoundException("entityClass not exist");
		}
		
	}
	
	public static Object getInstance(Class<?> javaType) {
		try {
			return javaType.newInstance();
		} catch (Exception e) {
			logger.error(javaType + " not exist");
			throw new ProviderNotFoundException("javaType not exist");
		}
		
	}

	/**
	 * @param entityClass
	 * @return
	 */
	public static Class<?> getJavaType(String entityClass) {
		try {
			return Class.forName(entityClass);
		} catch (Exception e) {
			logger.error(entityClass + " not exist");
			throw new ProviderNotFoundException("entityClass not exist");
		}
	}
	
	/**
	 * @param beanProperties
	 * @return
	 */
	public static List<PropertyDescriptor> findDdicAnnationProperty(PropertyDescriptor[] beanProperties) {
		List<PropertyDescriptor> pdResult = new ArrayList<>();
		for (PropertyDescriptor pd : beanProperties) {
			Ddic ddic = pd.getReadMethod().getAnnotation(Ddic.class);
			if (!ObjectUtils.isEmpty(ddic)) {
				pdResult.add(pd);
			}
		}
		return pdResult;
	}
	
	/**
	 * @param beanProperties
	 * @param name
	 * @return
	 */
	public static PropertyDescriptor findProperty(PropertyDescriptor[] beanProperties, String name) {
		for (PropertyDescriptor pd : beanProperties) {
			if (pd.getName().equals(name))
				return pd;
		}
		return null;
	}
	
	/**
	 * @param beanProperties
	 * @param name
	 * @return
	 */
	public static PropertyDescriptor findPropertyIgnoreCase(PropertyDescriptor[] beanProperties, String name) {
		for (PropertyDescriptor pd : beanProperties) {
			if (StringUtils.equalsIgnoreCase(pd.getName(), name))
				return pd;
		}
		return null;
	}
	
	/**
     * 根据索引获得超类的参数类型.
     * 
     * @param clazz 超类类型
     * @param index 索引
     */
    public static Class<?> getGenricType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)){
        	 return Object.class;
        }
        
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (isObjectType (params, index)) {
        	 return Object.class;
        }
        return (Class<?>) params[index];
    }
    
    private static boolean isObjectType (Type[] params, int index){
    	return (index >= params.length || index < 0)
    	|| !(params[index] instanceof Class);
    }
    
    public static Object invoke(Object bean, String methodName, Object[] args) {
    	// 2.获取参数类型
		Class<?>[] parameterTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}

		// 3.执行方法
		Object result = null;
		try {
			result = MethodUtils.invokeMethod(bean, methodName, args, parameterTypes);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			String errorMsg = "error occurs when DataTable invokes general CRUD repository, please check wrapped exception!";
			if(rootCause != null && org.apache.commons.lang3.StringUtils.isNotEmpty(rootCause.getMessage())){
				errorMsg = rootCause.getMessage();
				throw new IllegalStateException(errorMsg, rootCause);
			}
			throw new IllegalStateException(errorMsg, e);
		}
		return result;
    }

}
