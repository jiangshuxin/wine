package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.entity.system.DdicCategoryEntity;
import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import com.wuxian99.finance.basedata.repository.system.DdicCategoryRepository;
import com.wuxian99.finance.basedata.repository.system.DdicItemRepository;
import com.wuxian99.finance.basedata.service.system.DdicItemService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by za-jiangshuxin on 2017/8/2.
 */
@Service
public class DdicItemServiceImpl implements DdicItemService {
    @Autowired
    DdicItemRepository ddicItemRepository;
    @Autowired
    DdicCategoryRepository ddicCategoryRepository;

    @Override
    public List<DdicItemEntity> findByCategory(String category) {
        return ddicItemRepository.findByCategory(category);
    }

    @Override
    public Map<String, DdicItemEntity> findMapByCategory(String category) {
        List<DdicItemEntity> ddicItemEntityList = this.findByCategory(category);
        Map<String,DdicItemEntity> map = new HashMap<>();
        for(DdicItemEntity entity : ddicItemEntityList){
            map.put(entity.getItemKey(),entity);
        }
        return map;
    }

    @Override
    public Map<String, List<DdicItemEntity>> findAll() {
        List<DdicCategoryEntity> categoryEntityList = ddicCategoryRepository.findAll();
        List<DdicItemEntity> ddicItemEntityList = ddicItemRepository.findAll();
        Map<Integer, List<DdicItemEntity>> map = new HashMap<>();
        Map<Integer, String> idMap = new HashMap<>();
        for(DdicCategoryEntity categoryEntity : categoryEntityList){
            map.put(categoryEntity.getId(),new ArrayList<>());
            idMap.put(categoryEntity.getId(),categoryEntity.getName());
        }

        for(DdicItemEntity ddicItemEntity : ddicItemEntityList){
            List<DdicItemEntity> itemEntities = map.get(ddicItemEntity.getCategoryId());
            if(itemEntities == null)continue;
            itemEntities.add(ddicItemEntity);
        }

        Map<String, List<DdicItemEntity>> resultMap = new HashMap<>();
        for(Integer key : map.keySet()){
            resultMap.put(idMap.get(key),map.get(key));
        }
        return resultMap;
    }
}
