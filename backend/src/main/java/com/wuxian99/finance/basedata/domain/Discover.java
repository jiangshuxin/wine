package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 发现、动态
 */
@Entity
@Table(name="discover")
public class Discover implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String merchantid;
  private String pic;
  private String detailpic;
  private Long type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMerchantid() {
    return merchantid;
  }

  public void setMerchantid(String merchantid) {
    this.merchantid = merchantid;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getDetailpic() {
    return detailpic;
  }

  public void setDetailpic(String detailpic) {
    this.detailpic = detailpic;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
