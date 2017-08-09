package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.MerchantEntity;
import com.wuxian99.finance.basedata.repository.wine.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sxjiang on 2017/8/8.
 */
@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public List<MerchantEntity> queryAll(){
        return merchantRepository.findAll();
    }
}
