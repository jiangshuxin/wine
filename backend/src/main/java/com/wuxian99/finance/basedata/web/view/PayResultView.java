package com.wuxian99.finance.basedata.web.view;

/**
 * 支付发起返回信息
 */
public class PayResultView {

  private String payPic;
  private String prepayId;

  public String getPayPic() {
    return payPic;
  }

  public void setPayPic(String payPic) {
    this.payPic = payPic;
  }

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }
}
