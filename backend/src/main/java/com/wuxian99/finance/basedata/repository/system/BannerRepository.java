package com.wuxian99.finance.basedata.repository.system;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.domain.entity.system.DdicCategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sxjiang on 2017/7/10.
 */
public interface BannerRepository extends JpaRepository<BannerEntity, Integer> {
    List<BannerEntity> findByMerchantid(String merchantid,Sort sort);
}
