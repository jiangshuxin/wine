package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户收货地址
 */
@Entity
@Table(name="user_address")
public class UserAddressEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 关联的用户ID
   */
  @Column(name = "userid")
  private Long userId;

  /**
   * 收货人
   */
  private String reciver;

  /**
   * 收货人电话
   */
  private String phone;

  /**
   * 省市区
   */
  private String province;

  /**
   * 详细地址
   */
  private String address;

  /**
   * 是否为默认地址，1:是，0:否
   */
  @Column(name = "isdefualt")
  private Long isDefualt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public Long getIsDefualt() {
    return isDefualt;
  }

  public void setIsDefualt(Long isDefualt) {
    this.isDefualt = isDefualt;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
