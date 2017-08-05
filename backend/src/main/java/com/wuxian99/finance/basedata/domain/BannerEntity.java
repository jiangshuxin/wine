package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;

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
  @Column(name = "merchantid")
  private String merchantId;

  /**
   * 广告图片路径(不在支撑系统直接编辑)
   */
  @Column(name = "pic_path")
  private String picPath;

  /**
   * 广告图片关联上传记录id
   */
  @Column(name = "pic_ref")
  @UploadRef(ref = "picPath")
  private Integer picRef;

  /**
   * 排序，前端按升序展示
   */
  @Column(name = "sortvalue")
  private Long sortValue;

  /**
   * 关联的商品ID，点击后跳转到该商品详情页
   */
  @Column(name = "mdseid")
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

  public String getPicPath() {
    return picPath;
  }

  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }

  public Integer getPicRef() {
    return picRef;
  }

  public void setPicRef(Integer picRef) {
    this.picRef = picRef;
  }

  public Long getSortValue() {
    return sortValue;
  }

  public void setSortValue(Long sortValue) {
    this.sortValue = sortValue;
  }

  public Long getMdseId() {
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
