package com.wuxian99.finance.basedata.domain.model;

/**
 * Created by sxjiang on 2017/3/23.
 */
public class SigninUser {
    private String account;
    private String domain;
    private String password;
    private String name;
    private String department;
    private Boolean sysAuth;
    private Boolean financeAuth;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getSysAuth() {
        return sysAuth;
    }

    public void setSysAuth(Boolean sysAuth) {
        this.sysAuth = sysAuth;
    }

    public Boolean getFinanceAuth() {
        return financeAuth;
    }

    public void setFinanceAuth(Boolean financeAuth) {
        this.financeAuth = financeAuth;
    }
}
