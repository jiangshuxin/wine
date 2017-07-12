package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单
 */
@Entity
@Table(name="order")
public class OrderEntityEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 订单时间
   */
  private String time;

  /**
   * 买家用户ID
   */
  @Column(name = "userid")
  private Long userId;

  /**
   * 收货地址ID
   */
  @Column(name = "addressid")
  private Long addressId;

  /**
   * 酒庄编号
   */
  @Column(name = "merchantid")
  private String merchantId;

  /**
   * 订单金额，单位分
   */
  private Long amount;

  /**
   * 商品数量
   */
  @Column(name = "mdsecount")
  private Long mdseCount;

  /**
   * 支付金额，单位分，暂时不考虑优惠券和运费，所以订单金额等于支付金额
   */
  @Column(name = "payamount")
  private Long payAmount;

  /**
   * 1:待付款，2:待收货，3:已完成，4:已过期
   */
  private Long status;

  /**
   * 支付时间
   */
  @Column(name = "paytime")
  private String payTime;

  /**
   * 支付流水号
   */
  @Column(name = "payseqs")
  private String paySeqs;

  /**
   * 买家备注
   */
  private String comment;

  /**
   * 快递公司名称
   */
  @Column(name = "logisticscompany")
  private String logisticsCompany;

  /**
   * 快递单号
   */
  @Column(name = "logisticsseqs")
  private String logisticsSeqs;

  /**
   * 发票信息
   */
  @Column(name = "invoiceinfo")
  private String invoiceInfo;

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

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public Long getAmount() {
    return amount;
  }

  public Long getMdseCount() {
    return mdseCount;
  }

  public void setMdseCount(Long mdseCount) {
    this.mdseCount = mdseCount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getPayAmount() {
    return payAmount;
  }

  public void setPayAmount(Long payAmount) {
    this.payAmount = payAmount;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getPayTime() {
    return payTime;
  }

  public void setPayTime(String payTime) {
    this.payTime = payTime;
  }

  public String getPaySeqs() {
    return paySeqs;
  }

  public void setPaySeqs(String paySeqs) {
    this.paySeqs = paySeqs;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getLogisticsCompany() {
    return logisticsCompany;
  }

  public void setLogisticsCompany(String logisticsCompany) {
    this.logisticsCompany = logisticsCompany;
  }

  public String getLogisticsSeqs() {
    return logisticsSeqs;
  }

  public void setLogisticsSeqs(String logisticsSeqs) {
    this.logisticsSeqs = logisticsSeqs;
  }

  public String getInvoiceInfo() {
    return invoiceInfo;
  }

  public void setInvoiceInfo(String invoiceInfo) {
    this.invoiceInfo = invoiceInfo;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
