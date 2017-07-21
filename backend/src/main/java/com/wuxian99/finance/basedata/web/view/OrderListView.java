package com.wuxian99.finance.basedata.web.view;

import java.util.List;

/**
 * 订单列表
 */
public class OrderListView {

  private Long orderId;
  private String orderTime;
  private Long status;
  private Long amount;
  private Long mdseCount;
  private List<OrderMdseView> mdseInfos;

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

  public List<OrderMdseView> getMdseInfos() {
    return mdseInfos;
  }

  public void setMdseInfos(List<OrderMdseView> mdseInfos) {
    this.mdseInfos = mdseInfos;
  }

}
