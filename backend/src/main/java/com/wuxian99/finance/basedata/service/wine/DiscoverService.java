package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import com.wuxian99.finance.basedata.repository.system.wine.DiscoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverService {

    @Autowired
    DiscoverRepository discoverRepository;

    public List<DiscoverEntity> findByMerchantId(String merchantId, Long type, int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return discoverRepository.findByMerchantIdAndType(merchantId, type, pageRequest);
    }
}
