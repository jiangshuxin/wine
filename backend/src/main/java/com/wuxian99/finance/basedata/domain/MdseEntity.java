package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;
import com.wuxian99.finance.basedata.support.annotation.Ddic;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品
 */
@Entity
@Table(name="mdse")
public class MdseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 酒庄编号
   */
  @Column(name = "merchantid")
  private String merchantId;

  /**
   * 酒庄名称
   */
  @Column(name = "merchantname")
  private String merchantName;

  /**
   * 产区
   */
  @Column(name = "productarea")
  private String productArea;

  /**
   * 中文名称
   */
  private String name;

  /**
   * 英文名称
   */
  @Column(name = "nameen")
  private String nameEn;

  /**
   * 价格，单位分
   */
  private Long price;

  /**
   * 状态，1:上架，0:下架，2:售罄
   */
  @Ddic(name = "mdseStatus",mapTo = "statusName")
  private Long status;

  @Transient
  private String statusName;

  /**
   * 分类，1:红葡萄酒，2:白葡萄酒，3:起泡酒，4:冰酒，5:桃红葡萄酒
   */
  @Ddic(name = "mdseCatagory",mapTo = "catagoryName")
  private String catagory;

  @Transient
  private String catagoryName;

  /**
   * 酒品类型
   */
  @Column(name = "winetype")
  private String wineType;

  /**
   * 葡萄品种
   */
  @Column(name = "grapetype")
  private String grapeType;

  /**
   * 生产年份
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
  @Column(name = "treeage")
  private String treeAge;

  /**
   * 酿酒师介绍
   */
  @Column(name = "winemaker")
  private String wineMaker;

  /**
   * 商品小图，用于列表和购物车展示
   */
  @Column(name = "smallpic")
  private String smallPic;

  @Column(name = "smallpic_ref")
  @UploadRef(ref = "smallPic")
  private String smallPicRef;

  /**
   * 商品大图1，用于商品详情页展示
   */
  @Column(name = "bigpic1")
  private String bigPic1;

  @Column(name = "bigpic1_ref")
  @UploadRef(ref = "bigPic1")
  private String bigPic1Ref;

  /**
   * 商品大图2，用于商品详情页展示
   */
  @Column(name = "bigpic2")
  private String bigPic2;

  @Column(name = "bigpic2_ref")
  @UploadRef(ref = "bigPic2")
  private String bigPic2Ref;

  /**
   * 商品大图3，用于商品详情页展示
   */
  @Column(name = "bigpic3")
  private String bigPic3;

  @Column(name = "bigpic3_ref")
  @UploadRef(ref = "bigPic3")
  private String bigPic3Ref;

  /**
   * 商品大图4，用于商品详情页展示
   */
  @Column(name = "bigpic4")
  private String bigPic4;

  @Column(name = "bigpic4_ref")
  @UploadRef(ref = "bigPic4")
  private String bigPic4Ref;


  //=======================================废弃的属性Start==========================================
  /**
   * 推荐理由
   */
  @Deprecated
  private String reason;

  /**
   * 故事和品鉴
   */
  @Column(name = "storypic")
  @Deprecated
  private String storyPic;

  @Column(name = "storypic_ref")
  @UploadRef(ref = "storyPic")
  @Deprecated
  private String storyPicRef;
  //=======================================废弃的属性End==========================================



  //=======================================新增的属性Start==========================================
  /**
   * 酿造工艺
   */
  private String technology;

  /**
   * 橡木桶
   */
  private String barrel;

  /**
   * 罐装时间
   */
  @Column(name = "fillingtime")
  private String fillingTime;

  /**
   * 保质期
   */
  @Column(name = "expirydate")
  private String expiryDate;

  /**
   * 适饮温度
   */
  @Column(name = "drinktemperature")
  private String drinkTemperature;

  /**
   * 醒酒时间
   */
  @Column(name = "sobertime")
  private String soberTime;

  /**
   * 搭配推荐
   */
  @Column(name = "collocation")
  private String collocation;

  /**
   * 产量
   */
  private String yield;

  /**
   * 库存量
   */
  private String inventory;

  /**
   * 得奖照片1
   */
  @Column(name = "prizepic1")
  private String prizePic1;

  @Column(name = "prizepic1_ref")
  @UploadRef(ref = "prizePic1")
  private String prizePic1Ref;

  /**
   * 得奖照片2
   */
  @Column(name = "prizepic2")
  private String prizePic2;

  @Column(name = "prizepic2_ref")
  @UploadRef(ref = "prizePic2")
  private String prizePic2Ref;

  /**
   * 得奖照片3
   */
  @Column(name = "prizepic3")
  private String prizePic3;

  @Column(name = "prizepic3_ref")
  @UploadRef(ref = "prizePic3")
  private String prizePic3Ref;

  /**
   * 得奖照片4
   */
  @Column(name = "prizepic4")
  private String prizePic4;

  @Column(name = "prizepic4_ref")
  @UploadRef(ref = "prizePic4")
  private String prizePic4Ref;

  /**
   * 检验报告1
   */
  @Column(name = "inspectionreportpic1")
  private String inspectionReportPic1;

  @Column(name = "inspectionreportpic1_ref")
  @UploadRef(ref = "inspectionReportPic1")
  private String inspectionReportPic1Ref;

  /**
   * 检验报告2
   */
  @Column(name = "inspectionreportpic2")
  private String inspectionReportPic2;

  @Column(name = "inspectionreportpic2_ref")
  @UploadRef(ref = "inspectionReportPic2")
  private String inspectionReportPic2Ref;

  /**
   * 检验报告3
   */
  @Column(name = "inspectionreportpic3")
  private String inspectionReportPic3;

  @Column(name = "inspectionreportpic3_ref")
  @UploadRef(ref = "inspectionReportPic3")
  private String inspectionReportPic3Ref;

  /**
   * 检验报告4
   */
  @Column(name = "inspectionreportpic4")
  private String inspectionReportPic4;

  @Column(name = "inspectionreportpic4_ref")
  @UploadRef(ref = "inspectionReportPic4")
  private String inspectionReportPic4Ref;
  //=======================================新增的属性End==========================================


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

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCatagory() {
    return catagory;
  }

  public void setCatagory(String catagory) {
    this.catagory = catagory;
  }

  public String getCatagoryName() {
    return catagoryName;
  }

  public void setCatagoryName(String catagoryName) {
    this.catagoryName = catagoryName;
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

  public String getSmallPicRef() {
    return smallPicRef;
  }

  public void setSmallPicRef(String smallPicRef) {
    this.smallPicRef = smallPicRef;
  }

  public String getBigPic1Ref() {
    return bigPic1Ref;
  }

  public void setBigPic1Ref(String bigPic1Ref) {
    this.bigPic1Ref = bigPic1Ref;
  }

  public String getBigPic2Ref() {
    return bigPic2Ref;
  }

  public void setBigPic2Ref(String bigPic2Ref) {
    this.bigPic2Ref = bigPic2Ref;
  }

  public String getBigPic3Ref() {
    return bigPic3Ref;
  }

  public void setBigPic3Ref(String bigPic3Ref) {
    this.bigPic3Ref = bigPic3Ref;
  }

  public String getBigPic4Ref() {
    return bigPic4Ref;
  }

  public void setBigPic4Ref(String bigPic4Ref) {
    this.bigPic4Ref = bigPic4Ref;
  }

  public String getStoryPicRef() {
    return storyPicRef;
  }

  public void setStoryPicRef(String storyPicRef) {
    this.storyPicRef = storyPicRef;
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

  public String getPrizePic1() {
    return prizePic1;
  }

  public void setPrizePic1(String prizePic1) {
    this.prizePic1 = prizePic1;
  }

  public String getPrizePic1Ref() {
    return prizePic1Ref;
  }

  public void setPrizePic1Ref(String prizePic1Ref) {
    this.prizePic1Ref = prizePic1Ref;
  }

  public String getPrizePic2() {
    return prizePic2;
  }

  public void setPrizePic2(String prizePic2) {
    this.prizePic2 = prizePic2;
  }

  public String getPrizePic2Ref() {
    return prizePic2Ref;
  }

  public void setPrizePic2Ref(String prizePic2Ref) {
    this.prizePic2Ref = prizePic2Ref;
  }

  public String getPrizePic3() {
    return prizePic3;
  }

  public void setPrizePic3(String prizePic3) {
    this.prizePic3 = prizePic3;
  }

  public String getPrizePic3Ref() {
    return prizePic3Ref;
  }

  public void setPrizePic3Ref(String prizePic3Ref) {
    this.prizePic3Ref = prizePic3Ref;
  }

  public String getPrizePic4() {
    return prizePic4;
  }

  public void setPrizePic4(String prizePic4) {
    this.prizePic4 = prizePic4;
  }

  public String getPrizePic4Ref() {
    return prizePic4Ref;
  }

  public void setPrizePic4Ref(String prizePic4Ref) {
    this.prizePic4Ref = prizePic4Ref;
  }

  public String getInspectionReportPic1() {
    return inspectionReportPic1;
  }

  public void setInspectionReportPic1(String inspectionReportPic1) {
    this.inspectionReportPic1 = inspectionReportPic1;
  }

  public String getInspectionReportPic1Ref() {
    return inspectionReportPic1Ref;
  }

  public void setInspectionReportPic1Ref(String inspectionReportPic1Ref) {
    this.inspectionReportPic1Ref = inspectionReportPic1Ref;
  }

  public String getInspectionReportPic2() {
    return inspectionReportPic2;
  }

  public void setInspectionReportPic2(String inspectionReportPic2) {
    this.inspectionReportPic2 = inspectionReportPic2;
  }

  public String getInspectionReportPic2Ref() {
    return inspectionReportPic2Ref;
  }

  public void setInspectionReportPic2Ref(String inspectionReportPic2Ref) {
    this.inspectionReportPic2Ref = inspectionReportPic2Ref;
  }

  public String getInspectionReportPic3() {
    return inspectionReportPic3;
  }

  public void setInspectionReportPic3(String inspectionReportPic3) {
    this.inspectionReportPic3 = inspectionReportPic3;
  }

  public String getInspectionReportPic3Ref() {
    return inspectionReportPic3Ref;
  }

  public void setInspectionReportPic3Ref(String inspectionReportPic3Ref) {
    this.inspectionReportPic3Ref = inspectionReportPic3Ref;
  }

  public String getInspectionReportPic4() {
    return inspectionReportPic4;
  }

  public void setInspectionReportPic4(String inspectionReportPic4) {
    this.inspectionReportPic4 = inspectionReportPic4;
  }

  public String getInspectionReportPic4Ref() {
    return inspectionReportPic4Ref;
  }

  public void setInspectionReportPic4Ref(String inspectionReportPic4Ref) {
    this.inspectionReportPic4Ref = inspectionReportPic4Ref;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
