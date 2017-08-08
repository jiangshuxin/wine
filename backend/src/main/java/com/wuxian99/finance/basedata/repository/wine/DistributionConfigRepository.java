package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.DistributionConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistributionConfigRepository extends JpaRepository<DistributionConfigEntity, Long> {
    List<DistributionConfigEntity> findByMerchantId(String merchantId);
}
