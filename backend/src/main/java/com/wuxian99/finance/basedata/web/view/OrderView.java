package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 下单请求对象
 */
public class OrderView implements Serializable {

  private Long orderId;
  private String orderTime;
  private Long status;
  private Long amount;
  private Long mdseCount;
  private String mdseInfos;
  private String addressInfo;
  private String comment;
  private String logisticsInfo;
  private String invoiceInfo;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getMdseCount() {
    return mdseCount;
  }

  public void setMdseCount(Long mdseCount) {
    this.mdseCount = mdseCount;
  }

  public String getMdseInfos() {
    return mdseInfos;
  }

  public void setMdseInfos(String mdseInfos) {
    this.mdseInfos = mdseInfos;
  }

  public String getAddressInfo() {
    return addressInfo;
  }

  public void setAddressInfo(String addressInfo) {
    this.addressInfo = addressInfo;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getLogisticsInfo() {
    return logisticsInfo;
  }

  public void setLogisticsInfo(String logisticsInfo) {
    this.logisticsInfo = logisticsInfo;
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
