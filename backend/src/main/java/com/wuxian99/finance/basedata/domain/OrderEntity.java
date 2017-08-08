package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单
 */
@Entity
@Table(name="wine_order")
public class OrderEntity implements Serializable {

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
   * 0:已取消，1:未支付，2:已支付，3:已完成(已记录运单信息)
   */
  private Long status;

  /**
   * 支付完成时间
   */
  @Column(name = "paytime")
  private String payTime;

  /**
   * 支付流水号
   */
  @Column(name = "payseqs")
  private String paySeqs;

  /**
   * 支付二维码图片
   */
  @Column(name = "paypic")
  private String payPic;

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

  /**
   * 收货人
   */
  private String receiver;

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
   * 返佣标记，0:未返佣，1:已返佣，2:不需要返佣(酒庄未配置返佣规则，或订单未达到返佣门槛金额，或者用户没有分销上级)
   */
  @Column(name = "commissionflag")
  private Long commissionFlag;

  /**
   * 最后查询支付状态的时间，毫秒数，不存数据库
   */
  @Transient
  private long lastQueryPayStatusTime;

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

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
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

  public String getPayPic() {
    return payPic;
  }

  public void setPayPic(String payPic) {
    this.payPic = payPic;
  }

  public long getLastQueryPayStatusTime() {
    return lastQueryPayStatusTime;
  }

  public Long getCommissionFlag() {
    return commissionFlag;
  }

  public void setCommissionFlag(Long commissionFlag) {
    this.commissionFlag = commissionFlag;
  }

  public void setLastQueryPayStatusTime(long lastQueryPayStatusTime) {
    this.lastQueryPayStatusTime = lastQueryPayStatusTime;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
