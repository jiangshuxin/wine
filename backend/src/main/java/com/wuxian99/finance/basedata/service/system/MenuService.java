package com.wuxian99.finance.basedata.service.system;

import com.wuxian99.finance.basedata.domain.model.Menu;
import com.wuxian99.finance.basedata.domain.model.SigninUser;

import java.util.List;

/**
 * Created by sxjiang on 2017/3/22.
 */
public interface MenuService {
    List<Menu> generate(SigninUser userId, String contextPath);
    Menu queryMenuByKey(String key, String contextPath);
}
