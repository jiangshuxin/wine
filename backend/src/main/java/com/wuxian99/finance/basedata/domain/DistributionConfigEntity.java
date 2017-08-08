package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分销配置
 */
@Entity
@Table(name="distribution_config")
public class DistributionConfigEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  @Column(name = "merchantid")
  private String merchantId;

  /**
   * 商品编号，为空代表该酒庄的默认配置
   */
  @Column(name = "mdseid")
  private Long mdseId;

  /**
   * 返佣门槛金额，订单维度，单位分
   */
  private Long amount;

  /**
   * 一级返佣比例，0~100
   */
  private BigDecimal rebate1;

  /**
   * 二级返佣比例，0~100
   */
  private BigDecimal rebate2;

  /**
   * 三级返佣比例，0~100
   */
  private BigDecimal rebate3;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public Long getMdseId() {
    return mdseId;
  }

  public void setMdseId(Long mdseId) {
    this.mdseId = mdseId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public BigDecimal getRebate1() {
    return rebate1;
  }

  public void setRebate1(BigDecimal rebate1) {
    this.rebate1 = rebate1;
  }

  public BigDecimal getRebate2() {
    return rebate2;
  }

  public void setRebate2(BigDecimal rebate2) {
    this.rebate2 = rebate2;
  }

  public BigDecimal getRebate3() {
    return rebate3;
  }

  public void setRebate3(BigDecimal rebate3) {
    this.rebate3 = rebate3;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
