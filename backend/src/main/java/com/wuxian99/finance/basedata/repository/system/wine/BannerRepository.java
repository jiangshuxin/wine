package com.wuxian99.finance.basedata.repository.system.wine;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<BannerEntity, Integer> {
    List<BannerEntity> findByMerchantId(String merchantId,Sort sort);
}
