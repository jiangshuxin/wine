package com.wuxian99.finance.basedata.web.dto;

/**
 * 登录请求参数
 */
public class LoginDto {

    private String userName;
    private String password;
    //1:密码登录，2:验证码登录
    private Long type;
    //分销上级ID
    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
