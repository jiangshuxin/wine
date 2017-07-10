package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.BannerEntity;

import java.util.List;

/**
 * Created by sxjiang on 2017/7/10.
 */
public interface BannerService {
    List<BannerEntity> findByMerchantId(String merchangid);
}
