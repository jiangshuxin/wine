package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;

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
  @Column(name = "merchantid")
  private String merchantId;

  /**
   * 图片路径(不在支撑系统直接编辑)
   */
  @Column(name = "pic_path")
  private String picPath;

  /**
   * 图片关联上传记录id
   */
  @Column(name = "pic_ref")
  @UploadRef(ref = "picPath")
  private Integer picRef;

  /**
   * 标题
   */
  private String title;

  /**
   * 标签，如活动、资讯、专题
   */
  private String tag;

  /**
   * 详情描述
   */
  private String description;

  /**
   * 创建时间
   */
  @Column(name = "createtime")
  private String createTime;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
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
