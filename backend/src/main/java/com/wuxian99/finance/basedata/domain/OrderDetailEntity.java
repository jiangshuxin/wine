package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单详情
 */
@Entity
@Table(name="order_detail")
public class OrderDetailEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 订单号
   */
  @Column(name = "orderid")
  private Long orderId;

  /**
   * 商品编号
   */
  @Column(name = "mdseid")
  private Long mdseId;

  /**
   * 商品名称（冗余字段，方便查询）
   */
  @Column(name = "mdsename")
  private String mdseName;

  /**
   * 商品图片（冗余字段，方便查询）
   */
  @Column(name = "mdsesmallpic")
  private String mdseSmallPic;

  /**
   * 购买数量
   */
  private Long count;

  /**
   * 单价，单位分
   */
  private Long price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getMdseId() {
    return mdseId;
  }

  public void setMdseId(Long mdseId) {
    this.mdseId = mdseId;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getMdseName() {
    return mdseName;
  }

  public void setMdseName(String mdseName) {
    this.mdseName = mdseName;
  }

  public String getMdseSmallPic() {
    return mdseSmallPic;
  }

  public void setMdseSmallPic(String mdseSmallPic) {
    this.mdseSmallPic = mdseSmallPic;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
