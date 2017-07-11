package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 广告
 */
@Entity
@Table(name="banner")
public class BannerEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  private String merchantId;

  /**
   * 广告图片
   */
  private String pic;

  /**
   * 排序，前端按升序展示
   */
  private Long sortValue;

  /**
   * 关联的商品ID，点击后跳转到该商品详情页
   */
  private Long mdseId;

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

  public Long getSortValue() {
    return sortValue;
  }

  public void setSortValue(Long sortValue) {
    this.sortValue = sortValue;
  }

  public Long getMdseiId() {
    return mdseId;
  }

  public void setMdseId(Long mdseId) {
    this.mdseId = mdseId;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
