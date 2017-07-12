package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.BannerEntity;
import com.wuxian99.finance.basedata.repository.system.wine.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    @Autowired
    BannerRepository bannerRepository;

    public List<BannerEntity> findByMerchantId(String merchantId) {
        Sort sort = new Sort("sortValue");
        return bannerRepository.findByMerchantId(merchantId,sort);
    }
}
