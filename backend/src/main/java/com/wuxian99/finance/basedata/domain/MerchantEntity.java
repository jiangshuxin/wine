package com.wuxian99.finance.basedata.domain;

import com.google.gson.Gson;
import com.wuxian99.finance.basedata.support.annotation.Ddic;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;

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
    @Column(name = "merchantid")
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
     * 酒庄英文名称
     */
    @Column(name = "nameen")
    private String nameEn;

    /**
     * 状态，1:正常，0:停用
     */
    @Ddic(name = "merchantStatus",mapTo = "statusName")
    private Long status;

    @Transient
    private String statusName;

    /**
     * 庄主（法人）
     */
    private String master;

    /**
     * 注册时间
     */
    @Column(name = "createyear")
    private String createYear;

    /**
     * 种植面积
     */
    private String acreage;

    /**
     * 葡萄种植种类，多个逗号隔开
     */
    @Column(name = "grapetype")
    private String grapeType;

    /**
     * 平均树龄
     */
    @Column(name = "treeage")
    private String treeAge;

    /**
     * 酿酒师及介绍
     */
    @Column(name = "winemaker")
    private String wineMaker;

    /**
     * 年产量
     */
    private String output;

    /**
     * 酒庄级别和级别介绍
     */
    private String level;

    /**
     * 酒庄文字简介
     */
    private String description;

    /**
     * 实时土壤和气候信息
     */
    @Column(name = "soilandclimate")
    private String soilAndClimate;

    /**
     * 参观预约链接
     */
    @Column(name = "tourismlink")
    private String tourismLink;


    /**
     * 酒庄视频链接
     */
    @Column(name = "videolink")
    private String videoLink;

    /**
     * 酒庄照片1
     */
    @Column(name = "chateaupic1")
    private String chateauPic1;

    @Column(name = "chateaupic1_ref")
    @UploadRef(ref = "chateauPic1")
    private String chateauPic1Ref;

    /**
     * 酒庄照片2
     */
    @Column(name = "chateaupic2")
    private String chateauPic2;

    @Column(name = "chateaupic2_ref")
    @UploadRef(ref = "chateauPic2")
    private String chateauPic2Ref;

    /**
     * 酒庄照片3
     */
    @Column(name = "chateaupic3")
    private String chateauPic3;

    @Column(name = "chateaupic3_ref")
    @UploadRef(ref = "chateauPic3")
    private String chateauPic3Ref;

    /**
     * 酒庄照片4
     */
    @Column(name = "chateaupic4")
    private String chateauPic4;

    @Column(name = "chateaupic4_ref")
    @UploadRef(ref = "chateauPic4")
    private String chateauPic4Ref;

    /**
     * 种植地照片1
     */
    @Column(name = "landpic1")
    private String landPic1;

    @Column(name = "landpic1_ref")
    @UploadRef(ref = "landPic1")
    private String landPic1Ref;

    /**
     * 种植地照片2
     */
    @Column(name = "landpic2")
    private String landPic2;

    @Column(name = "landpic2_ref")
    @UploadRef(ref = "landPic2")
    private String landPic2Ref;

    /**
     * 种植地照片3
     */
    @Column(name = "landpic3")
    private String landPic3;

    @Column(name = "landpic3_ref")
    @UploadRef(ref = "landPic3")
    private String landPic3Ref;

    /**
     * 种植地照片4
     */
    @Column(name = "landpic4")
    private String landPic4;

    @Column(name = "landpic4_ref")
    @UploadRef(ref = "landPic4")
    private String landPic4Ref;

    /**
     * 部分证照1
     */
    @Column(name = "certpic1")
    private String certPic1;

    @Column(name = "certpic1_ref")
    @UploadRef(ref = "certPic1")
    private String certPic1Ref;

    /**
     * 部分证照2
     */
    @Column(name = "certpic2")
    private String certPic2;

    @Column(name = "certpic2_ref")
    @UploadRef(ref = "certPic2")
    private String certPic2Ref;

    /**
     * 部分证照3
     */
    @Column(name = "certpic3")
    private String certPic3;

    @Column(name = "certpic3_ref")
    @UploadRef(ref = "certPic3")
    private String certPic3Ref;

    /**
     * 部分证照4
     */
    @Column(name = "certpic4")
    private String certPic4;

    @Column(name = "certpic4_ref")
    @UploadRef(ref = "certPic4")
    private String certPic4Ref;

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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
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

    public String getLandPic1() {
        return landPic1;
    }

    public void setLandPic1(String landPic1) {
        this.landPic1 = landPic1;
    }

    public String getLandPic1Ref() {
        return landPic1Ref;
    }

    public void setLandPic1Ref(String landPic1Ref) {
        this.landPic1Ref = landPic1Ref;
    }

    public String getLandPic2() {
        return landPic2;
    }

    public void setLandPic2(String landPic2) {
        this.landPic2 = landPic2;
    }

    public String getLandPic2Ref() {
        return landPic2Ref;
    }

    public void setLandPic2Ref(String landPic2Ref) {
        this.landPic2Ref = landPic2Ref;
    }

    public String getLandPic3() {
        return landPic3;
    }

    public void setLandPic3(String landPic3) {
        this.landPic3 = landPic3;
    }

    public String getLandPic3Ref() {
        return landPic3Ref;
    }

    public void setLandPic3Ref(String landPic3Ref) {
        this.landPic3Ref = landPic3Ref;
    }

    public String getLandPic4() {
        return landPic4;
    }

    public void setLandPic4(String landPic4) {
        this.landPic4 = landPic4;
    }

    public String getLandPic4Ref() {
        return landPic4Ref;
    }

    public void setLandPic4Ref(String landPic4Ref) {
        this.landPic4Ref = landPic4Ref;
    }

    public String getCertPic1() {
        return certPic1;
    }

    public void setCertPic1(String certPic1) {
        this.certPic1 = certPic1;
    }

    public String getCertPic1Ref() {
        return certPic1Ref;
    }

    public void setCertPic1Ref(String certPic1Ref) {
        this.certPic1Ref = certPic1Ref;
    }

    public String getCertPic2() {
        return certPic2;
    }

    public void setCertPic2(String certPic2) {
        this.certPic2 = certPic2;
    }

    public String getCertPic2Ref() {
        return certPic2Ref;
    }

    public void setCertPic2Ref(String certPic2Ref) {
        this.certPic2Ref = certPic2Ref;
    }

    public String getCertPic3() {
        return certPic3;
    }

    public void setCertPic3(String certPic3) {
        this.certPic3 = certPic3;
    }

    public String getCertPic3Ref() {
        return certPic3Ref;
    }

    public void setCertPic3Ref(String certPic3Ref) {
        this.certPic3Ref = certPic3Ref;
    }

    public String getCertPic4() {
        return certPic4;
    }

    public void setCertPic4(String certPic4) {
        this.certPic4 = certPic4;
    }

    public String getCertPic4Ref() {
        return certPic4Ref;
    }

    public void setCertPic4Ref(String certPic4Ref) {
        this.certPic4Ref = certPic4Ref;
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

    public String getChateauPic1() {
        return chateauPic1;
    }

    public void setChateauPic1(String chateauPic1) {
        this.chateauPic1 = chateauPic1;
    }

    public String getChateauPic1Ref() {
        return chateauPic1Ref;
    }

    public void setChateauPic1Ref(String chateauPic1Ref) {
        this.chateauPic1Ref = chateauPic1Ref;
    }

    public String getChateauPic2() {
        return chateauPic2;
    }

    public void setChateauPic2(String chateauPic2) {
        this.chateauPic2 = chateauPic2;
    }

    public String getChateauPic2Ref() {
        return chateauPic2Ref;
    }

    public void setChateauPic2Ref(String chateauPic2Ref) {
        this.chateauPic2Ref = chateauPic2Ref;
    }

    public String getChateauPic3() {
        return chateauPic3;
    }

    public void setChateauPic3(String chateauPic3) {
        this.chateauPic3 = chateauPic3;
    }

    public String getChateauPic3Ref() {
        return chateauPic3Ref;
    }

    public void setChateauPic3Ref(String chateauPic3Ref) {
        this.chateauPic3Ref = chateauPic3Ref;
    }

    public String getChateauPic4() {
        return chateauPic4;
    }

    public void setChateauPic4(String chateauPic4) {
        this.chateauPic4 = chateauPic4;
    }

    public String getChateauPic4Ref() {
        return chateauPic4Ref;
    }

    public void setChateauPic4Ref(String chateauPic4Ref) {
        this.chateauPic4Ref = chateauPic4Ref;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
