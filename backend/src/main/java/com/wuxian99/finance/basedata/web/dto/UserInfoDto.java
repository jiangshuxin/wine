package com.wuxian99.finance.basedata.web.dto;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 用户信息展示Bean
 */
public class UserInfoDto implements Serializable {

  private Long userId;
  private String userName;
  private Long type;
  private Long balance;
  private String realName;
  private String gender;
  private String birthday;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
