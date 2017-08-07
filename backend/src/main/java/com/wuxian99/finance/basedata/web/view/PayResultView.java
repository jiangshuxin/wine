package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

/**
 * 支付发起返回信息
 */
public class PayResultView {

  //微信内H5支付
  private String appId;
  private String timeStamp;
  private String nonceStr;
  private String packAge;
  private String signType;
  private String paySign;

  //PC扫码支付
  private String payPic;

  public String getPayPic() {
    return payPic;
  }

  public void setPayPic(String payPic) {
    this.payPic = payPic;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getPaySign() {
    return paySign;
  }

  public void setPaySign(String paySign) {
    this.paySign = paySign;
  }

  public String getPackAge() {
    return packAge;
  }

  public void setPackAge(String packAge) {
    this.packAge = packAge;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
