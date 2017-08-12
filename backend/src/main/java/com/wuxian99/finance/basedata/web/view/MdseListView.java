package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 商品列表展示Bean
 */
public class MdseListView implements Serializable {

  private Long mdseId;

  private String name;

  private String nameEn;

  private String smallPic;

  private Long price;

  private String year;

  private Long status;

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

  public String getSmallPic() {
    return smallPic;
  }

  public void setSmallPic(String smallPic) {
    this.smallPic = smallPic;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }
}
