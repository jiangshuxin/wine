package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import com.wuxian99.finance.basedata.domain.entity.system.MetadataEntity;
import com.wuxian99.finance.basedata.domain.model.MetadataInfo;
import com.wuxian99.finance.basedata.domain.model.Select;
import com.wuxian99.finance.basedata.repository.system.DdicItemRepository;
import com.wuxian99.finance.basedata.repository.system.MetadataRepository;
import com.wuxian99.finance.basedata.service.system.MetadataService;
import com.wuxian99.finance.basedata.support.annotation.Ddic;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;
import com.wuxian99.finance.basedata.support.util.ClassUtil;
import com.wuxian99.finance.basedata.support.util.StringUtils;
import com.wuxian99.finance.common.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class MetadataServiceImpl implements MetadataService, InitializingBean {
	@Autowired
	EntityManager entityManager;
	@Autowired
	MetadataRepository metadataRepository;
	@Autowired
	DdicItemRepository ddicItemRepository;
	@Autowired
	ApplicationContext context;

	private Map<String, String> entityMap = new HashMap<String, String>();
	private Map<String,Map<String,UploadRef>> entityUploadRefMap = new HashMap<>();
	private static final String ENTITY_SUFFIX = "Entity";
	private static final String REPOSITORY_SUFFIX = "Repository";

	@Override
	@Cacheable(value = "metadata")
	public List<MetadataInfo> findAll() {
		List<MetadataEntity> entities = metadataRepository.findAll();
		List<MetadataInfo> result = new ArrayList<MetadataInfo>();
		for (MetadataEntity entity : entities) {
			MetadataInfo Info = new MetadataInfo();
			BeanUtils.copyProperties(entity, Info);
			fillMeta(Info);
			result.add(Info);
		}
		return result;
	}

	@Override
	@Cacheable(value = "metadata", key = "#module")
	public MetadataInfo findByModule(String module) {
		MetadataEntity entity = metadataRepository.findOne(module);
		if (entity == null)
			return null;
		MetadataInfo Info = new MetadataInfo();
		BeanUtils.copyProperties(entity, Info);
		fillMeta(Info);
		return Info;
	}

	@Override
	@CachePut(value = "metadata", key = "#metadataInfo.moduleName")
	public MetadataInfo save(MetadataInfo metadataInfo) {
		MetadataEntity entity = new MetadataEntity();
		BeanUtils.copyProperties(metadataInfo, entity);
		metadataRepository.save(entity);
		return metadataInfo;
	}

	/**
	 * 填充metaInfo上下文属性
	 * 
	 * @param metadataInfo
	 */
	private void fillMeta(MetadataInfo metadataInfo) {
		String entityClass = entityMap.get(metadataInfo.getTableName());
		String[] arr = entityClass.split("\\.");
		String repository = arr[arr.length - 1].replace(ENTITY_SUFFIX, REPOSITORY_SUFFIX);
		String repositoryInstance = StringUtils.firstLetterToLower(repository);
		Object repositoryBean = context.getBean(repositoryInstance);
		metadataInfo.setRepositoryBean(repositoryBean);
		metadataInfo.setEntityClass(entityClass);
		metadataInfo.setEntityJavaType(ClassUtil.getJavaType(entityClass));
		metadataInfo.setRepositoryInstance(repositoryInstance);
		metadataInfo.setPds(ReflectUtils.getBeanProperties(metadataInfo.getEntityJavaType()));
		setDdic(metadataInfo);
	}

	/**
	 * 填充select数据字典信息
	 * 
	 * @param metadataInfo
	 */
	private void setDdic(MetadataInfo metadataInfo) {
		List<PropertyDescriptor> pds = ClassUtil.findDdicAnnationProperty(metadataInfo.getPds());
		for (PropertyDescriptor pd : pds) {
			Ddic ddic = pd.getReadMethod().getAnnotation(Ddic.class);
			String category = StringUtils.isEmpty(ddic.name())
					? metadataInfo.getTableName() + Constants.UNDERLINE_SEPERATOR + pd.getName() : ddic.name();
			List<DdicItemEntity> ddicItems = ddicItemRepository.findByCategory(category);
			for (DdicItemEntity item : ddicItems) {
				Select select = new Select(item.getItemKey(), item.getItemValue());
				metadataInfo.getDdicInfo().put(category, select);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		for (EntityType<?> entity : entities) {
			entityMap.put(entity.getName(), entity.getBindableJavaType().getName()); // key:table,// value:EntityClass

			Class<?> javaType = entity.getJavaType();
			Field[] fields = javaType.getDeclaredFields();
			Map<String,UploadRef> uploadRefMap = new HashMap<>();
			entityUploadRefMap.put(javaType.getSimpleName(),uploadRefMap);
			for(Field field : fields){
				UploadRef uploadRef = AnnotationUtils.getAnnotation(field, UploadRef.class);
				if(uploadRef != null){
					uploadRefMap.put(field.getName(),uploadRef);
				}
			}
		}
	}

	@Override
	public Map<String, Object> findDdic(String module) {
		MetadataInfo meta = findByModule(module);
		Map<String, Object> map = new HashMap<>();
		for (PropertyDescriptor pd : meta.getPds()) {
			Ddic ddic = pd.getReadMethod().getAnnotation(Ddic.class);
			if (ddic != null)
				map.put(pd.getName(), meta.getDdicInfo().get(ddic.name()));
		}
		return map;
	}

	public Map<String, Map<String, UploadRef>> getEntityUploadRefMap() {
		return entityUploadRefMap;
	}
}
