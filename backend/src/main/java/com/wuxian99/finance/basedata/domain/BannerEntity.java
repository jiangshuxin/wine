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
  private String merchantid;

  /**
   * 广告图片
   */
  private String pic;

  /**
   * 排序，前端按升序展示
   */
  private Long sortvalue;

  /**
   * 链接到该商品详情页
   */
  private Long mdseid;

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

  public Long getSortvalue() {
    return sortvalue;
  }

  public void setSortvalue(Long sortvalue) {
    this.sortvalue = sortvalue;
  }

  public Long getMdseid() {
    return mdseid;
  }

  public void setMdseid(Long mdseid) {
    this.mdseid = mdseid;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
