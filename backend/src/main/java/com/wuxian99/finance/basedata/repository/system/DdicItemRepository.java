package com.wuxian99.finance.basedata.repository.system;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DdicItemRepository extends JpaRepository<DdicItemEntity, Integer> {
	@Query("select t from ddic_item t, ddic_category r where t.categoryId = r.id and r.name=?1 order by t.sortNo asc")
    List<DdicItemEntity> findByCategory(String category);
	@Query("select t from ddic_item t, ddic_category r where t.categoryId = r.id and r.name=?1 and t.itemKey=?2")
    DdicItemEntity findByCategoryAndItemKey(String category, String itemKey);

}