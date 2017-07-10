package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 分销配置
 */
@Entity
@Table(name="distribution_config")
public class DistributionConfig implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String merchantid;
  private Long mdseid;
  private Long amount;
  private Long rebate1;
  private Long rebate2;
  private Long rebate3;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMerchantid() {
    return merchantid;
  }

  public void setMerchantid(String merchantid) {
    this.merchantid = merchantid;
  }

  public Long getMdseid() {
    return mdseid;
  }

  public void setMdseid(Long mdseid) {
    this.mdseid = mdseid;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getRebate1() {
    return rebate1;
  }

  public void setRebate1(Long rebate1) {
    this.rebate1 = rebate1;
  }

  public Long getRebate2() {
    return rebate2;
  }

  public void setRebate2(Long rebate2) {
    this.rebate2 = rebate2;
  }

  public Long getRebate3() {
    return rebate3;
  }

  public void setRebate3(Long rebate3) {
    this.rebate3 = rebate3;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
