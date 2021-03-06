package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.DiscoverDetailEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscoverDetailRepository extends JpaRepository<DiscoverDetailEntity, Long> {
    List<DiscoverDetailEntity> findByDiscoverId(Long discoverId, Sort sort);
}
