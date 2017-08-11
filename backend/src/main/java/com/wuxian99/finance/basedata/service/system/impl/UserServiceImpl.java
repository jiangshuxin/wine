package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.UserEntity;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.repository.wine.UserRepository;
import com.wuxian99.finance.basedata.service.system.UserService;
import com.wuxian99.finance.basedata.support.util.StringUtils;
import com.wuxian99.finance.common.Result;
import com.wuxian99.finance.common.SigninCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sxjiang on 2017/3/23.
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result<SigninUser> signin(SigninCommand signinCommand) {
        //FIXME
        SigninUser user = new SigninUser();

        UserEntity userEntity = userRepository.findByUserName(signinCommand.getAccount());
        if(userEntity == null){
            return Result.buildFail("账号或密码错误！");
        }
       // if(StringUtils.equals(userEntity.))

        user.setName("蒋树新");
        user.setDepartment("财富酒庄集团总部");
        user.setAccount("sxjiang");
        return Result.buildSuccess(user);
    }

}
