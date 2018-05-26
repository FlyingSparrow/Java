package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "tuichushijian")
public class QuitDataTz implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -79564435348390014L;

    @Id
    @Column(name = "fldRecdId", unique = true, nullable = false)
    private String fldRecdId;

    @Column(name = "fldItemId")
    private Integer fldItemId;

    @Column(name = "fldPubRecdId")
    private String fldPubRecdId;

    @Column(name = "fldAttCount")
    private Integer fldAttCount;

    @Column(name = "fldIconId")
    private Integer fldIconId;

    @Column(name = "fldSiteId")
    private Integer fldSiteId;

    @Column(name = "fldReadFlag")
    private Integer fldReadFlag;

    @Column(name = "fldFlowFlag")
    private Integer fldFlowFlag;

    @Column(name = "fldRecdPwd")
    private String fldRecdPwd;

    @Column(name = "fldBoldShow")
    private Integer fldBoldShow;

    @Column(name = "fldRecdTextClr")
    private Integer fldRecdTextClr;

    @Column(name = "fldRecdBgndClr")
    private Integer fldRecdBgndClr;

    @Column(name = "fldReadFromUrl")
    private Integer fldReadFromUrl;

    @Column(name = "fldURLHash")
    private String fldURLHash;

    @Column(name = "fldUrlAddr")
    private String fldUrlAddr;

    @Column(name = "fldCitedCount")
    private Integer fldCitedCount;

    @Column(name = "chanpin")
    private String product;

    //退出机构
    @Column(name = "tuichujigou")
    private String investor;

    @Column(name = "zongtouzie")
    private String zongtouzie;

    //回报金额
    @Column(name = "huibaojine")
    private String returnAmount;

    //回报倍数
    @Column(name = "huibaolv")
    private String returnMultiple;

    @Lob
    @Column(name = "biaodiqiye")
    private String companyName;

    @Column(name = "zhucedi")
    private String region;

    @Column(name = "hangye")
    private String industry;

    @Column(name = "riqi")
    private String time;

    @Column(name = "tuichufangshi")
    private String quitWay;

    @Lob
    @Column(name = "tuichushijian")
    private String quitEvent;

    @Column(name = "biaoshi")
    private String biaoShi;

    public String getFldRecdId() {
        return fldRecdId;
    }

    public void setFldRecdId(String fldRecdId) {
        this.fldRecdId = fldRecdId;
    }

    public Integer getFldItemId() {
        return fldItemId;
    }

    public void setFldItemId(Integer fldItemId) {
        this.fldItemId = fldItemId;
    }

    public String getFldPubRecdId() {
        return fldPubRecdId;
    }

    public void setFldPubRecdId(String fldPubRecdId) {
        this.fldPubRecdId = fldPubRecdId;
    }

    public Integer getFldAttCount() {
        return fldAttCount;
    }

    public void setFldAttCount(Integer fldAttCount) {
        this.fldAttCount = fldAttCount;
    }

    public Integer getFldIconId() {
        return fldIconId;
    }

    public void setFldIconId(Integer fldIconId) {
        this.fldIconId = fldIconId;
    }

    public Integer getFldSiteId() {
        return fldSiteId;
    }

    public void setFldSiteId(Integer fldSiteId) {
        this.fldSiteId = fldSiteId;
    }

    public Integer getFldReadFlag() {
        return fldReadFlag;
    }

    public void setFldReadFlag(Integer fldReadFlag) {
        this.fldReadFlag = fldReadFlag;
    }

    public Integer getFldFlowFlag() {
        return fldFlowFlag;
    }

    public void setFldFlowFlag(Integer fldFlowFlag) {
        this.fldFlowFlag = fldFlowFlag;
    }

    public String getFldRecdPwd() {
        return fldRecdPwd;
    }

    public void setFldRecdPwd(String fldRecdPwd) {
        this.fldRecdPwd = fldRecdPwd;
    }

    public Integer getFldBoldShow() {
        return fldBoldShow;
    }

    public void setFldBoldShow(Integer fldBoldShow) {
        this.fldBoldShow = fldBoldShow;
    }

    public Integer getFldRecdTextClr() {
        return fldRecdTextClr;
    }

    public void setFldRecdTextClr(Integer fldRecdTextClr) {
        this.fldRecdTextClr = fldRecdTextClr;
    }

    public Integer getFldRecdBgndClr() {
        return fldRecdBgndClr;
    }

    public void setFldRecdBgndClr(Integer fldRecdBgndClr) {
        this.fldRecdBgndClr = fldRecdBgndClr;
    }

    public Integer getFldReadFromUrl() {
        return fldReadFromUrl;
    }

    public void setFldReadFromUrl(Integer fldReadFromUrl) {
        this.fldReadFromUrl = fldReadFromUrl;
    }

    public String getFldURLHash() {
        return fldURLHash;
    }

    public void setFldURLHash(String fldURLHash) {
        this.fldURLHash = fldURLHash;
    }

    public String getFldUrlAddr() {
        return fldUrlAddr;
    }

    public void setFldUrlAddr(String fldUrlAddr) {
        this.fldUrlAddr = fldUrlAddr;
    }

    public Integer getFldCitedCount() {
        return fldCitedCount;
    }

    public void setFldCitedCount(Integer fldCitedCount) {
        this.fldCitedCount = fldCitedCount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getZongtouzie() {
        return zongtouzie;
    }

    public void setZongtouzie(String zongtouzie) {
        this.zongtouzie = zongtouzie;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnMultiple() {
        return returnMultiple;
    }

    public void setReturnMultiple(String returnMultiple) {
        this.returnMultiple = returnMultiple;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuitWay() {
        return quitWay;
    }

    public void setQuitWay(String quitWay) {
        this.quitWay = quitWay;
    }

    public String getQuitEvent() {
        return quitEvent;
    }

    public void setQuitEvent(String quitEvent) {
        this.quitEvent = quitEvent;
    }

    public String getBiaoShi() {
        return biaoShi;
    }

    public void setBiaoShi(String biaoShi) {
        this.biaoShi = biaoShi;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
