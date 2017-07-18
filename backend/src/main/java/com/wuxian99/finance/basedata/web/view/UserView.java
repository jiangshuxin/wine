package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 用户信息展示Bean
 */
public class UserView implements Serializable {

  private String userId;
  private String userName;
  private String type;
  private String balance;
  private String realName;
  private String gender;
  private String birthday;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
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
