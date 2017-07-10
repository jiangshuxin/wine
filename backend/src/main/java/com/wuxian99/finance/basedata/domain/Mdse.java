package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品
 */
@Entity
@Table(name="mdse")
public class Mdse implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  private String merchantid;

  /**
   * 中文名称
   */
  private String name;

  /**
   * 英文名称
   */
  private String nameen;

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
  private String winetype;

  /**
   * 葡萄品种
   */
  private String grapetype;

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
  private String treeage;

  /**
   * 酿酒师
   */
  private String winemaker;

  /**
   * 酒庄名称
   */
  private String merchantname;

  /**
   * 产区
   */
  private String productarea;

  /**
   * 小图路径，用于列表和购物车展示
   */
  private String smallpic;

  /**
   * 大图路径，用于商品详情页展示
   */
  private String bigpic;

  /**
   * 推荐理由
   */
  private String reason;

  /**
   * 品鉴与酒庄故事，长图
   */
  private String storypic;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameen() {
    return nameen;
  }

  public void setNameen(String nameen) {
    this.nameen = nameen;
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

  public String getWinetype() {
    return winetype;
  }

  public void setWinetype(String winetype) {
    this.winetype = winetype;
  }

  public String getGrapetype() {
    return grapetype;
  }

  public void setGrapetype(String grapetype) {
    this.grapetype = grapetype;
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

  public String getMerchantname() {
    return merchantname;
  }

  public void setMerchantname(String merchantname) {
    this.merchantname = merchantname;
  }

  public String getProductarea() {
    return productarea;
  }

  public void setProductarea(String productarea) {
    this.productarea = productarea;
  }

  public String getSmallpic() {
    return smallpic;
  }

  public void setSmallpic(String smallpic) {
    this.smallpic = smallpic;
  }

  public String getBigpic() {
    return bigpic;
  }

  public void setBigpic(String bigpic) {
    this.bigpic = bigpic;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getStorypic() {
    return storypic;
  }

  public void setStorypic(String storypic) {
    this.storypic = storypic;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
