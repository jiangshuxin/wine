package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.entity.system.DdicItemEntity;
import com.wuxian99.finance.basedata.domain.model.Menu;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.repository.system.DdicItemRepository;
import com.wuxian99.finance.basedata.service.system.MenuService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 简易菜单实现
 * 排序号需满足父子结构(空格忽略):
 * 0010
 * 0010 0010
 * 0010 0020
 * 0010 0020 0010
 * 0010 0020 0020
 * 0010 0030
 * Created by sxjiang on 2017/3/22.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private DdicItemRepository ddicItemRepository;

    @Override
    public List<Menu> generate(SigninUser signinUser, String contextPath) {
        List<DdicItemEntity> entityList = ddicItemRepository.findByCategory("menu");

        List<Menu> result = new ArrayList<>();
        SortedMap<String,Menu> menuMap = new TreeMap<>();
        SortedMap<Integer,List<Menu>> levelMap = new TreeMap<>();
        for(DdicItemEntity entity : entityList){
            Menu menu = extractToMenu(entity,contextPath,signinUser);
            menuMap.put(entity.getSortNo(),menu);
            int level = menu.getLevel();

            List<Menu> menuList = levelMap.get(level);
            if(menuList == null) {
                menuList = new ArrayList<>();
                levelMap.put(level,menuList);
            }
            menuList.add(menu);
        }
        for(Integer level : levelMap.keySet()){
            if(level == 1){
                result.addAll(levelMap.get(level));
            }else{
                int startIndex = 0;
                int endIndex = 4 * (level - 1);
                List<Menu> tempList = levelMap.get(level);
                for(Menu m : tempList){
                    Menu parent = menuMap.get(StringUtils.substring(m.getSortNo(),startIndex,endIndex));
                    m.setParent(parent);
                    if(parent == null) continue;
                    List<Menu> children = parent.getChildren();
                    if(children == null) {
                        children = new ArrayList<>();
                        parent.setChildren(children);
                    }
                    children.add(m);
                }
            }
        }
        return result;
    }

    private Menu extractToMenu(DdicItemEntity entity, String contextPath, SigninUser signinUser){
        Menu menu = new Menu();
        menu.setKey(entity.getItemKey());
        menu.setName(entity.getItemValue());
        menu.setDesc(entity.getDescription());
        menu.setSortNo(entity.getSortNo());
        //默认uri   推荐以entity.getKey()作为moduleName配置到元数据表中
        menu.setUri(StringUtils.join(contextPath,"/menuRedirect/",entity.getItemKey(),"?redirect=","/",entity.getItemKey(),"/list"));
        String attribute2 = entity.getAttribute2();
        if(StringUtils.isNotEmpty(attribute2)){//直接在attribute2中配置servletPath
            menu.setUri(StringUtils.join(contextPath,"/menuRedirect/",entity.getItemKey(),"?redirect=",attribute2));
        }

        String attribute1 = entity.getAttribute1();//用于控制权限
//        if(StringUtils.isNotEmpty(attribute1) && signinUser != null && PropertyUtils.isReadable(signinUser,attribute1)){
//            Object result = null;
//            try {
//                result = PropertyUtils.getProperty(signinUser,attribute1);//auth
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if(result instanceof Boolean){
//                menu.setHidden(!(Boolean)result);
//            }
//        }

        if("admin".equals(attribute1) && signinUser != null && !"admin".equals(signinUser.getAccount())){
            menu.setHidden(true);
        }else{
            menu.setHidden(false);
        }

        int length = entity.getSortNo().length();
        int level = length / 4;
        menu.setLevel(level);
        return menu;
    }

    @Override
    public Menu queryMenuByKey(String key, String contextPath) {
        List<Menu> menuList = generate(null,contextPath);
        Map<String,Menu> menuMap = new HashMap<>();
        extractMenuMap(menuMap,menuList);
        return menuMap.get(key);
    }

    private void extractMenuMap(Map<String,Menu> menuMap, List<Menu> menuList){
        for(Menu m : menuList){
            menuMap.put(m.getKey(),m);
            if(m.getChildren() != null && m.getChildren().size() > 0){
                extractMenuMap(menuMap,m.getChildren());
            }
        }
    }
}
