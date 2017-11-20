package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;
import com.wuxian99.finance.basedata.support.annotation.Ddic;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户账户资金明细，包括返佣、打款
 */
@Entity
@Table(name="amount_detail")
public class AmountDetailEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 用户ID
   */
  @Column(name = "userid")
  private Long userId;

  /**
   * 用户名
   */
  @Column(name = "username")
  private String userName;

  /**
   * 操作人，返佣的操作人为system，打款操作人为后台管理用户
   */
  @Column(name = "operator")
  private String operator;

  /**
   * 关联订单号
   */
  @Column(name = "orderid")
  private Long orderId;

  /**
   * 交易类型，1：1级返佣，2：2级返佣，3:3级返佣，4：打款
   */
  @Column(name = "type")
  private Long type;

  /**
   * 打款凭证号，扩展用
   */
  @Column(name = "voucher")
  private String voucher;

  /**
   * 交易金额，单位分
   */
  @Column(name = "amount")
  private Long amount;

  /**
   * 交易后余额，单位分
   */
  @Column(name = "prebalance")
  private Long preBalance;

  /**
   * 交易后余额，单位分
   */
  @Column(name = "afterbalance")
  private Long afterBalance;

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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getVoucher() {
    return voucher;
  }

  public void setVoucher(String voucher) {
    this.voucher = voucher;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(Long preBalance) {
    this.preBalance = preBalance;
  }

  public Long getAfterBalance() {
    return afterBalance;
  }

  public void setAfterBalance(Long afterBalance) {
    this.afterBalance = afterBalance;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
