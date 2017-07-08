package com.wuxian99.finance.basedata.repository.system;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DdicItemRepository extends JpaRepository<DdicItemEntity, Integer> {
	@Query("select t from ddic_item t, ddic_category r where t.categoryId = r.id and r.name=?1 order by t.sortNo asc")
    List<DdicItemEntity> findByCategory(String category);
	@Query("select t from ddic_item t, ddic_category r where t.categoryId = r.id and r.name=?1 and t.key=?2")
    DdicItemEntity findByCategoryAndKey(String category, String key);
	
	@Cacheable(value = "ddic", key="#root.methodName")
	@Query("select t.value from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='departmentCn'")
	List<String> findDep();
	
	@Cacheable(value = "ddic", key="#root.methodName")
	@Query("select t.value from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='saleDimension'")
	List<String> findSaleDimension();
	
	@Cacheable(value = "ddic", key="#root.methodName + #p0")
	@Query("select t.key from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='unified_code_type' and t.attribute1=?1")
	String findCheckFinanceModule(String checkModule);
	
	@Cacheable(value = "ddic", key="#root.methodName")
	@Query("select t.key from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='unified_code_type'")
	List<String> findCheckFinanceModule();
	
	@Cacheable(value = "ddic", key="#root.methodName")
	@Query("select t.attribute1 from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='unified_code_type'")
	List<String> findCaculateModule();
	
	@Cacheable(value = "ddic", key="#root.methodName")
	@Query("select t.key from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='task_purpose'")
	List<String> findTaskPurpose();
	
	@Cacheable(value = "ddic", key="#root.methodName")
	@Query("select t.key from ddic_item t, ddic_category r where t.categoryId = r.id and r.name='task_type'")
	List<String> findTaskType();
}