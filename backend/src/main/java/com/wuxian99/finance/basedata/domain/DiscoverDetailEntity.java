package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 发现、动态详情
 */
@Entity
@Table(name="discover_detail")
public class DiscoverDetailEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 发现ID
   */
  @Column(name = "discoverid")
  private String discoverId;

  /**
   * 详情图片
   */
  private String pic;

  /**
   * 关联的商品ID，点击后跳转到该商品详情页
   */
  @Column(name = "mdseid")
  private Long mdseId;

  /**
   * 排序，前端按升序展示
   */
  @Column(name = "sortvalue")
  private Long sortValue;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDiscoverId() {
    return discoverId;
  }

  public void setDiscoverId(String discoverId) {
    this.discoverId = discoverId;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Long getMdseId() {
    return mdseId;
  }

  public void setMdseId(Long mdseId) {
    this.mdseId = mdseId;
  }

  public Long getSortValue() {
    return sortValue;
  }

  public void setSortValue(Long sortValue) {
    this.sortValue = sortValue;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
