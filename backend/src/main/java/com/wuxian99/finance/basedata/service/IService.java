package com.wuxian99.finance.basedata.service;

import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.common.CUDCommand;
import com.wuxian99.finance.common.QueryCommand;
import com.wuxian99.finance.common.Result;

public interface IService {
	Result<?> executeCud(CUDCommand command, SigninUser signinUser);

	Result<?> executeFind(QueryCommand command, SigninUser signinUser);
}
