package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

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
  private String[] bigPics;
  private String technology;
  private String barrel;
  private String fillingTime;
  private String expiryDate;
  private String drinkTemperature;
  private String soberTime;
  private String collocation;
  private String yield;
  private String inventory;
  private String[] prizePics;
  private String[] inspectionReportPics;

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

  public String[] getBigPics() {
    return bigPics;
  }

  public void setBigPics(String[] bigPics) {
    this.bigPics = bigPics;
  }

  public String getTechnology() {
    return technology;
  }

  public void setTechnology(String technology) {
    this.technology = technology;
  }

  public String getBarrel() {
    return barrel;
  }

  public void setBarrel(String barrel) {
    this.barrel = barrel;
  }

  public String getFillingTime() {
    return fillingTime;
  }

  public void setFillingTime(String fillingTime) {
    this.fillingTime = fillingTime;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getDrinkTemperature() {
    return drinkTemperature;
  }

  public void setDrinkTemperature(String drinkTemperature) {
    this.drinkTemperature = drinkTemperature;
  }

  public String getSoberTime() {
    return soberTime;
  }

  public void setSoberTime(String soberTime) {
    this.soberTime = soberTime;
  }

  public String getCollocation() {
    return collocation;
  }

  public void setCollocation(String collocation) {
    this.collocation = collocation;
  }

  public String getYield() {
    return yield;
  }

  public void setYield(String yield) {
    this.yield = yield;
  }

  public String getInventory() {
    return inventory;
  }

  public void setInventory(String inventory) {
    this.inventory = inventory;
  }

  public String[] getPrizePics() {
    return prizePics;
  }

  public void setPrizePics(String[] prizePics) {
    this.prizePics = prizePics;
  }

  public String[] getInspectionReportPics() {
    return inspectionReportPics;
  }

  public void setInspectionReportPics(String[] inspectionReportPics) {
    this.inspectionReportPics = inspectionReportPics;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
