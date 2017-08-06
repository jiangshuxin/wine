package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Long>, JpaSpecificationExecutor<MerchantEntity>{

}
