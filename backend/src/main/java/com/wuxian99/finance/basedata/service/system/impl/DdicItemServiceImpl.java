package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import com.wuxian99.finance.basedata.repository.system.DdicItemRepository;
import com.wuxian99.finance.basedata.service.system.DdicItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by za-jiangshuxin on 2017/8/2.
 */
@Service
public class DdicItemServiceImpl implements DdicItemService {
    @Autowired
    DdicItemRepository ddicItemRepository;

    @Override
    public List<DdicItemEntity> findByCategory(String category) {
        return ddicItemRepository.findByCategory(category);
    }
}
