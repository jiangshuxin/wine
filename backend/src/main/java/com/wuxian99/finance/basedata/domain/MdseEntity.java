package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品
 */
@Entity
@Table(name="mdse")
public class MdseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  private String merchantId;

  /**
   * 中文名称
   */
  private String name;

  /**
   * 英文名称
   */
  private String nameEn;

  /**
   * 价格，单位分
   */
  private Long price;

  /**
   * 状态，1:上架，0:下架，2:售罄
   */
  private Long status;

  /**
   * 分类，1:红葡萄酒，2:白葡萄酒，3:起泡酒，4:冰酒
   */
  private String catagory;

  /**
   * 酒品类型
   */
  private String wineType;

  /**
   * 葡萄品种
   */
  private String grapeType;

  /**
   * 年份
   */
  private String year;

  /**
   * 酒精度
   */
  private String degree;

  /**
   * 净含量
   */
  private String ml;

  /**
   * 平均树龄
   */
  private String treeAge;

  /**
   * 酿酒师
   */
  private String wineMaker;

  /**
   * 酒庄名称
   */
  private String merchantName;

  /**
   * 产区
   */
  private String productArea;

  /**
   * 小图路径，用于列表和购物车展示
   */
  private String smallPic;

  /**
   * 大图路径，用于商品详情页展示
   */
  private String bigPic;

  /**
   * 推荐理由
   */
  private String reason;

  /**
   * 品鉴与酒庄故事，长图
   */
  private String storyPic;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameEn() {
    return nameEn;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getCatagory() {
    return catagory;
  }

  public void setCatagory(String catagory) {
    this.catagory = catagory;
  }

  public String getWineType() {
    return wineType;
  }

  public void setWineType(String wineType) {
    this.wineType = wineType;
  }

  public String getGrapeType() {
    return grapeType;
  }

  public void setGrapeType(String grapeType) {
    this.grapeType = grapeType;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getMl() {
    return ml;
  }

  public void setMl(String ml) {
    this.ml = ml;
  }

  public String getTreeAge() {
    return treeAge;
  }

  public void setTreeAge(String treeAge) {
    this.treeAge = treeAge;
  }

  public String getWineMaker() {
    return wineMaker;
  }

  public void setWineMaker(String wineMaker) {
    this.wineMaker = wineMaker;
  }

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  public String getProductArea() {
    return productArea;
  }

  public void setProductArea(String productArea) {
    this.productArea = productArea;
  }

  public String getSmallPic() {
    return smallPic;
  }

  public void setSmallPic(String smallPic) {
    this.smallPic = smallPic;
  }

  public String getBigPic() {
    return bigPic;
  }

  public void setBigPic(String bigPic) {
    this.bigPic = bigPic;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getStoryPic() {
    return storyPic;
  }

  public void setStoryPic(String storyPic) {
    this.storyPic = storyPic;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
