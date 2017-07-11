package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 酒庄
 */
@Entity
@Table(name="merchant")
public class MerchantEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号，也是后台登录名（总店管理员admin，酒庄管理员M0001）
   */
  private String merchantId;

  /**
   * 后台登录密码
   */
  private String password;

  /**
   * 酒庄名称(总店，XXX酒庄）
   */
  private String name;

  /**
   * 状态，1:正常，0:停用
   */
  private Long status;

  /**
   * 庄主
   */
  private String master;

  /**
   * 创建时间
   */
  private String createYear;

  /**
   * 葡萄园面积
   */
  private String acreage;

  /**
   * 土壤类型
   */
  private String agroType;

  /**
   * 葡萄品种
   */
  private String grapeType;

  /**
   * 平均树龄
   */
  private String treeAge;

  /**
   * 酿酒师
   */
  private String wineMaker;

  /**
   * 酿造工艺
   */
  private String technology;

  /**
   * 年产量
   */
  private String output;

  /**
   * 橡木桶
   */
  private String barrel;

  /**
   * 酒庄地址
   */
  private String address;

  /**
   * 接待能力
   */
  private String capacity;

  /**
   * 详细描述
   */
  private String description;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
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

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
