package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DiscoverRepository extends PagingAndSortingRepository<DiscoverEntity, Long> {
    List<DiscoverEntity> findByMerchantIdAndType(String merchantId, Long type, Pageable page);
}
