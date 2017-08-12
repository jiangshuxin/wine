package com.wuxian99.finance.basedata.web.view;

import java.io.Serializable;

/**
 * 商户信息展示Bean
 */
public class MerchantView implements Serializable {

  private String merchantId;
  private String name;
  private String master;
  private String createYear;
  private String acreage;
  private String agroType;
  private String grapeType;
  private String treeAge;
  private String wineMaker;
  private String technology;
  private String output;
  private String barrel;
  private String address;
  private String capacity;
  private String description;

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

  public String getAgroType() {
    return agroType;
  }

  public void setAgroType(String agroType) {
    this.agroType = agroType;
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

  public String getTechnology() {
    return technology;
  }

  public void setTechnology(String technology) {
    this.technology = technology;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public String getBarrel() {
    return barrel;
  }

  public void setBarrel(String barrel) {
    this.barrel = barrel;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
