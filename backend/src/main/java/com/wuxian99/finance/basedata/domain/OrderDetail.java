package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单详情
 */
@Entity
@Table(name="order_detail")
public class OrderDetail implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private Long orderid;
  private Long mdseid;
  private Long count;
  private Long price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderid() {
    return orderid;
  }

  public void setOrderid(Long orderid) {
    this.orderid = orderid;
  }

  public Long getMdseid() {
    return mdseid;
  }

  public void setMdseid(Long mdseid) {
    this.mdseid = mdseid;
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

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
