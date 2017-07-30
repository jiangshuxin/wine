package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoverRepository extends JpaRepository<DiscoverEntity, Long> {
    Page<DiscoverEntity> findByMerchantIdAndType(String merchantId, Long type, Pageable page);
}
