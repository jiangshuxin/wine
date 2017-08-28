package com.wuxian99.finance.basedata.web.view;

import java.io.Serializable;

/**
 * 商户信息展示Bean
 */
public class MerchantView implements Serializable {

  private String merchantId;
  private String name;
  private String nameEn;
  private String master;
  private String createYear;
  private String acreage;
  private String grapeType;
  private String treeAge;
  private String wineMaker;
  private String output;
  private String level;
  private String description;
  private String soilAndClimate;
  private String tourismLink;
  private String videoLink;
  private String[] chateauPics;
  private String[] landPics;
  private String[] certPics;
  private String[] prizePics;

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

  public String getMaster() {
    return master;
  }

  public void setMaster(String master) {
    this.master = master;
  }

  public String getCreateYear() {
    return createYear;
  }

  public void setCreateYear(String createYear) {
    this.createYear = createYear;
  }

  public String getAcreage() {
    return acreage;
  }

  public void setAcreage(String acreage) {
    this.acreage = acreage;
  }

  public String getGrapeType() {
    return grapeType;
  }

  public void setGrapeType(String grapeType) {
    this.grapeType = grapeType;
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

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSoilAndClimate() {
    return soilAndClimate;
  }

  public void setSoilAndClimate(String soilAndClimate) {
    this.soilAndClimate = soilAndClimate;
  }

  public String getTourismLink() {
    return tourismLink;
  }

  public void setTourismLink(String tourismLink) {
    this.tourismLink = tourismLink;
  }

  public String[] getLandPics() {
    return landPics;
  }

  public void setLandPics(String[] landPics) {
    this.landPics = landPics;
  }

  public String[] getCertPics() {
    return certPics;
  }

  public void setCertPics(String[] certPics) {
    this.certPics = certPics;
  }

  public String[] getPrizePics() {
    return prizePics;
  }

  public void setPrizePics(String[] prizePics) {
    this.prizePics = prizePics;
  }

  public String[] getChateauPics() {
    return chateauPics;
  }

  public void setChateauPics(String[] chateauPics) {
    this.chateauPics = chateauPics;
  }

  public String getVideoLink() {
    return videoLink;
  }

  public void setVideoLink(String videoLink) {
    this.videoLink = videoLink;
  }
}
