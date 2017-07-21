package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.DiscoverDetailEntity;
import com.wuxian99.finance.basedata.domain.DiscoverEntity;
import com.wuxian99.finance.basedata.repository.wine.DiscoverDetailRepository;
import com.wuxian99.finance.basedata.repository.wine.DiscoverRepository;
import com.wuxian99.finance.basedata.web.dto.QueryDiscoverListDto;
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

    public List<DiscoverEntity> findDiscovers(QueryDiscoverListDto queryDiscoverListDto) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = queryDiscoverListDto.convert(sort);
        return discoverRepository.findByMerchantIdAndType(queryDiscoverListDto.getMerchantId(), queryDiscoverListDto.getType(), pageRequest);
    }

    public List<DiscoverDetailEntity> findDetailByDiscoverId(Long discoverId) {
        Sort sort = new Sort("sortValue");
        return discoverDetailRepository.findByDiscoverId(discoverId, sort);
    }

}
