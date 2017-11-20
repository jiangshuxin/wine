package com.wuxian99.finance.basedata.web.dto;

import com.google.gson.Gson;

/**
 * 登录请求参数
 */
public class LoginDto {

    private String merchantId;
    private String userName;
    private String password;
    //1:密码登录，2:验证码登录
    private Long type;
    //推荐码
    private String referralCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
