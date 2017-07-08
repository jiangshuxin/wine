package com.wuxian99.finance.basedata.support.util;

import com.wuxian99.finance.basedata.domain.model.SigninUser;

/**
 * Created by sxjiang on 2017/5/15.
 */
public class SigninUserUtil {
    private static final ThreadLocal<SigninUser> userThreadLocal = new ThreadLocal();

    public static void set(SigninUser signinUser){
        userThreadLocal.set(signinUser);
    }

    public static SigninUser get(){
        SigninUser user = userThreadLocal.get();
        if(user == null) user = new SigninUser();
        return user;
    }

    public static void clear(){
        userThreadLocal.remove();
    }
}
