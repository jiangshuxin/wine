package com.wuxian99.finance.basedata.web.view;

import java.io.Serializable;

/**
 * 商品详情展示Bean
 */
public class MdseView implements Serializable {

  private Long mdseId;
  private String name;
  private String nameEn;
  private Long price;
  private Long status;
  private String catagory;
  private String wineType;
  private String grapeType;
  private String year;
  private String degree;
  private String ml;
  private String treeAge;
  private String wineMaker;
  private String merchantName;
  private String productArea;
  private String smallPic;
  private String bigPic1;
  private String bigPic2;
  private String bigPic3;
  private String bigPic4;
  private String reason;
  private String storyPic;

  public Long getMdseId() {
    return mdseId;
  }

  public void setMdseId(Long mdseId) {
    this.mdseId = mdseId;
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

  public String getBigPic1() {
    return bigPic1;
  }

  public void setBigPic1(String bigPic1) {
    this.bigPic1 = bigPic1;
  }

  public String getBigPic2() {
    return bigPic2;
  }

  public void setBigPic2(String bigPic2) {
    this.bigPic2 = bigPic2;
  }

  public String getBigPic3() {
    return bigPic3;
  }

  public void setBigPic3(String bigPic3) {
    this.bigPic3 = bigPic3;
  }

  public String getBigPic4() {
    return bigPic4;
  }

  public void setBigPic4(String bigPic4) {
    this.bigPic4 = bigPic4;
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
}
