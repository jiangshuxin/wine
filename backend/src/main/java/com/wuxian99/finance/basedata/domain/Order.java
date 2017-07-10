package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单
 */
@Entity
@Table(name="order")
public class Order implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String time;
  private Long userid;
  private Long addressid;
  private String merchantid;
  private Long amount;
  private Long payamount;
  private Long status;
  private String paytime;
  private String payseqs;
  private String comment;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public Long getAddressid() {
    return addressid;
  }

  public void setAddressid(Long addressid) {
    this.addressid = addressid;
  }

  public String getMerchantid() {
    return merchantid;
  }

  public void setMerchantid(String merchantid) {
    this.merchantid = merchantid;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getPayamount() {
    return payamount;
  }

  public void setPayamount(Long payamount) {
    this.payamount = payamount;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getPaytime() {
    return paytime;
  }

  public void setPaytime(String paytime) {
    this.paytime = paytime;
  }

  public String getPayseqs() {
    return payseqs;
  }

  public void setPayseqs(String payseqs) {
    this.payseqs = payseqs;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
