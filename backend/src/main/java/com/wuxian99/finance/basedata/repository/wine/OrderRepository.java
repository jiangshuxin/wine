package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.OrderEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity>{

    @Query("select t from OrderEntity t where t.userId = ?1 and (t.status=?2 or t.status=?3)")
    List<OrderEntity> findByUserIdAndStatus(Long userId, Long status1, Long status2, PageRequest page);

}
