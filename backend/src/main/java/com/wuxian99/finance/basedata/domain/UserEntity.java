package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;
import com.wuxian99.finance.basedata.support.annotation.Ddic;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户
 */
@Entity
@Table(name="user")
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  @Column(name = "merchantid")
  private String merchantId;

  /**
   * 分销上级用户ID
   */
  @Column(name = "parentid")
  private Long parentId;

  /**
   * 登录用户名，即手机号
   */
  @Column(name = "username")
  private String userName;

  /**
   * 登录密码
   */
  private String password;

  /**
   * 状态，1:正常，0:停用
   */
  @Ddic(name = "userStatus",mapTo = "statusName")
  private Long status;

  @Transient
  private String statusName;

  /**
   * 角色，1:销售（管理员添加），2:普通用户（前端注册）
   */
  @Ddic(name = "userType",mapTo = "typeName")
  private Long type;

  @Transient
  private String typeName;

  /**
   * 返佣余额，单位分
   */
  private Long balance = 0L;

  /**
   * 姓名
   */
  @Column(name = "realname")
  private String realName;

  /**
   * 性别
   */
  private String gender;

  /**
   * 生日
   */
  private String birthday;

  /**
   * 推荐码
   */
  @Column(name = "referralcode")
  private String referralCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

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

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
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

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
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
