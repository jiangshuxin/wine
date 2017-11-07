package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.MerchantEntity;
import com.wuxian99.finance.basedata.domain.UserEntity;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.repository.wine.MerchantRepository;
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
    private MerchantRepository merchantRepository;

    @Override
    public Result<SigninUser> signin(SigninCommand signinCommand) {
        //FIXME
        SigninUser user = new SigninUser();

        MerchantEntity merchantEntity = merchantRepository.findByMerchantId(signinCommand.getAccount());

        if(merchantEntity == null){
            return Result.buildFail("账号或密码错误！");
        }
        if(!StringUtils.equals(merchantEntity.getPassword(),signinCommand.getPassword())){
            return Result.buildFail("账号或密码错误！");
        }

        user.setName(merchantEntity.getName());
        user.setDepartment(merchantEntity.getName());
        user.setAccount(merchantEntity.getMerchantId());
        return Result.buildSuccess(user);
    }

}
