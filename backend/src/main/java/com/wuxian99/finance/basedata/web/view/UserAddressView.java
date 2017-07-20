package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户收货地址展示Bean
 */
public class UserAddressView implements Serializable {

  private Long userId;
  private Long addressId;
  private Long isDefualt;
  private String reciver;
  private String phone;
  private String province;
  private String address;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public Long getIsDefualt() {
    return isDefualt;
  }

  public void setIsDefualt(Long isDefualt) {
    this.isDefualt = isDefualt;
  }

  public String getReciver() {
    return reciver;
  }

  public void setReciver(String reciver) {
    this.reciver = reciver;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
