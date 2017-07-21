package com.wuxian99.finance.basedata.web.view;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情
 */
public class OrderView implements Serializable {

  private Long orderId;
  private String orderTime;
  private Long status;
  private Long amount;
  private Long mdseCount;
  private List<OrderMdseView> mdseInfos;
  private String address;
  private String phone;
  private String reciver;
  private String province;
  private String comment;
  private String logisticsCompany;
  private String logisticsSeqs;
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

  public  List<OrderMdseView> getMdseInfos() {
    return mdseInfos;
  }

  public void setMdseInfos( List<OrderMdseView> mdseInfos) {
    this.mdseInfos = mdseInfos;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getReciver() {
    return reciver;
  }

  public void setReciver(String reciver) {
    this.reciver = reciver;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
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

}
