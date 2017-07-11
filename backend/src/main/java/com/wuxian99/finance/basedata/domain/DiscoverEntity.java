package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 发现、动态
 */
@Entity
@Table(name="discover")
public class DiscoverEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  private String merchantId;

  /**
   * 图片，用于列表展示
   */
  private String pic;

  /**
   * 详情图片，长图
   */
  private String detailPic;

  /**
   * 类型，1:发现，2:动态
   */
  private Long type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getDetailPic() {
    return detailPic;
  }

  public void setDetailPic(String detailPic) {
    this.detailPic = detailPic;
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
