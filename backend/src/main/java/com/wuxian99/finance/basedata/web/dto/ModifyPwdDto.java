package com.wuxian99.finance.basedata.web.dto;

/**
 * 修改密码请求参数
 */
public class ModifyPwdDto {

    private Long userId;
    private String password;
    private String verifyCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
