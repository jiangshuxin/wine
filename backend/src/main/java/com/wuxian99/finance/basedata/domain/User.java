package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户
 */
@Entity
@Table(name="user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 分销上级用户ID
   */
  private Long parentid;

  /**
   * 登录用户名，即手机号
   */
  private String username;

  /**
   * 登录密码
   */
  private String password;

  /**
   * 状态，1:正常，0:停用
   */
  private Long status;

  /**
   * 角色，1:销售（管理员添加），2:普通用户（前端注册）
   */
  private Long type;

  /**
   * 返佣余额，单位分
   */
  private Long balance;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 姓名
   */
  private String realname;

  /**
   * 性别
   */
  private String gender;

  /**
   * 生日
   */
  private String birthday;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentid() {
    return parentid;
  }

  public void setParentid(Long parentid) {
    this.parentid = parentid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
