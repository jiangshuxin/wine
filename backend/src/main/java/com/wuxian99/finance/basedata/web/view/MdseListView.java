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

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
