package com.wuxian99.finance.basedata.service.wine.impl;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.repository.system.BannerRepository;
import com.wuxian99.finance.basedata.service.wine.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sxjiang on 2017/7/10.
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerRepository bannerRepository;
    @Override
    public List<BannerEntity> findByMerchantId(String merchangid) {
        Sort sort = new Sort("sortvalue");
        return bannerRepository.findByMerchantid(merchangid,sort);
    }
}
