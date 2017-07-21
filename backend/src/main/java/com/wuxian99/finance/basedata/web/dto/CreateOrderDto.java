package com.wuxian99.finance.basedata.web.dto;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 下单请求参数
 */
public class CreateOrderDto implements Serializable {

  private Long userId;
  private Long addressId;
  private String merchantId;
  private String comment;
  private String mdseInfo;
  private String invoiceInfo;

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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getMdseInfo() {
    return mdseInfo;
  }

  public void setMdseInfo(String mdseInfo) {
    this.mdseInfo = mdseInfo;
  }

  public String getInvoiceInfo() {
    return invoiceInfo;
  }

  public void setInvoiceInfo(String invoiceInfo) {
    this.invoiceInfo = invoiceInfo;
  }

}
