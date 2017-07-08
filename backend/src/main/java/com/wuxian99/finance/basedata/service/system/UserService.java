package com.wuxian99.finance.basedata.service.system;

import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.common.Result;
import com.wuxian99.finance.common.SigninCommand;

/**
 * Created by sxjiang on 2017/3/23.
 */
public interface UserService {
    Result<SigninUser> signin(SigninCommand signinCommand);
}
