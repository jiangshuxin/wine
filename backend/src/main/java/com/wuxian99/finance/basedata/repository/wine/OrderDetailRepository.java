package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.MdseEntity;
import com.wuxian99.finance.basedata.domain.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>, JpaSpecificationExecutor<OrderDetailEntity>{

}
