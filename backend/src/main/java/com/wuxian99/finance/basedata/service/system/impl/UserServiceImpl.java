package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.system.UserService;
import com.wuxian99.finance.common.Result;
import com.wuxian99.finance.common.SigninCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by sxjiang on 2017/3/23.
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result<SigninUser> signin(SigninCommand signinCommand) {
        //FIXME
        SigninUser user = new SigninUser();
        user.setName("蒋树新");
        user.setDepartment("ics");
        user.setAccount("sxjiang");
        return Result.buildSuccess(user);
    }

}
