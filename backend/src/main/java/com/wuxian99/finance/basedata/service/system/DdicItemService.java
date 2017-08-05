package com.wuxian99.finance.basedata.service.system;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by za-jiangshuxin on 2017/8/2.
 */
public interface DdicItemService {

    List<DdicItemEntity> findByCategory(String category);

    Map<String,List<DdicItemEntity>> findAll();
}
