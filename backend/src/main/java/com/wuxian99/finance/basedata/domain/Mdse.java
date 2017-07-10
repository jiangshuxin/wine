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
  private String merchantid;
  private String name;
  private String nameen;
  private Long price;
  private Long status;
  private String catagory;
  private String winetype;
  private String grapetype;
  private String year;
  private String degree;
  private String ml;
  private String treeage;
  private String winemaker;
  private String merchantname;
  private String productarea;
  private String smallpic;
  private String bigpic;
  private String reason;
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
