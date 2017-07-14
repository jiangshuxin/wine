package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.DiscoverDetailEntity;
import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import com.wuxian99.finance.basedata.repository.wine.DiscoverDetailRepository;
import com.wuxian99.finance.basedata.repository.wine.DiscoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverService {

    @Autowired
    DiscoverRepository discoverRepository;

    @Autowired
    DiscoverDetailRepository discoverDetailRepository;

    public List<DiscoverEntity> findByMerchantIdAndType(String merchantId, Long type, int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return discoverRepository.findByMerchantIdAndType(merchantId, type, pageRequest);
    }

    public List<DiscoverDetailEntity> findDetailByDiscoverId(Long discoverId) {
        Sort sort = new Sort("sortValue");
        return discoverDetailRepository.findByDiscoverId(discoverId, sort);
    }

}
