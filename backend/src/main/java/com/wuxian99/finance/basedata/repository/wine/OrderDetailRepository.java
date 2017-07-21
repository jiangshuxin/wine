package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>, JpaSpecificationExecutor<OrderDetailEntity>{

    List<OrderDetailEntity> findByOrderId(Long orderId);

}
