package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 酒庄
 */
@Entity
@Table(name="merchant")
public class Merchant implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号，也是后台登录名（总店管理员admin，酒庄管理员M0001）
   */
  private String merchantid;

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
  private String createyear;

  /**
   * 葡萄园面积
   */
  private String acreage;

  /**
   * 土壤类型
   */
  private String agrotype;

  /**
   * 葡萄品种
   */
  private String grapetype;

  /**
   * 平均树龄
   */
  private String treeage;

  /**
   * 酿酒师
   */
  private String winemaker;

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

  public String getMerchantid() {
    return merchantid;
  }

  public void setMerchantid(String merchantid) {
    this.merchantid = merchantid;
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

  public String getCreateyear() {
    return createyear;
  }

  public void setCreateyear(String createyear) {
    this.createyear = createyear;
  }

  public String getAcreage() {
    return acreage;
  }

  public void setAcreage(String acreage) {
    this.acreage = acreage;
  }

  public String getAgrotype() {
    return agrotype;
  }

  public void setAgrotype(String agrotype) {
    this.agrotype = agrotype;
  }

  public String getGrapetype() {
    return grapetype;
  }

  public void setGrapetype(String grapetype) {
    this.grapetype = grapetype;
  }

  public String getTreeage() {
    return treeage;
  }

  public void setTreeage(String treeage) {
    this.treeage = treeage;
  }

  public String getWinemaker() {
    return winemaker;
  }

  public void setWinemaker(String winemaker) {
    this.winemaker = winemaker;
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
