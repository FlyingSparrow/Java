package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "binggousj")
public class MergerDataSmt implements Serializable {

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

    //并购方
    @Column(name = "BingGouF")
    private String acquirer;

    @Column(name = "BBingGou")
    private String beMergered;

    @Column(name = "HangYe")
    private String industry;

    @Column(name = "LeiXing")
    private String type;

    @Column(name = "SFKuaGuo")
    private String transnational;

    @Column(name = "Starttime")
    private String startTime;

    @Column(name = "Endtime")
    private String endTime;

    @Column(name = "JinE")
    private String mergerAmount;

    @Column(name = "ZhuangTai")
    private String mergerStatus;

    @Column(name = "GuQuan")
    private String stockEquity;

    @Column(name = "QiYeGuZhi")
    private String businessValuation;

    @Lob
    @Column(name = "Content")
    private String content;

    @Lob
    @Column(name = "BGFinfo")
    private String acquirerInfo;

    @Lob
    @Column(name = "BBGFinfo")
    private String beMegeredInfo;

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

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getBeMergered() {
        return beMergered;
    }

    public void setBeMergered(String beMergered) {
        this.beMergered = beMergered;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransnational() {
        return transnational;
    }

    public void setTransnational(String transnational) {
        this.transnational = transnational;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMergerAmount() {
        return mergerAmount;
    }

    public void setMergerAmount(String mergerAmount) {
        this.mergerAmount = mergerAmount;
    }

    public String getMergerStatus() {
        return mergerStatus;
    }

    public void setMergerStatus(String mergerStatus) {
        this.mergerStatus = mergerStatus;
    }

    public String getStockEquity() {
        return stockEquity;
    }

    public void setStockEquity(String stockEquity) {
        this.stockEquity = stockEquity;
    }

    public String getBusinessValuation() {
        return businessValuation;
    }

    public void setBusinessValuation(String businessValuation) {
        this.businessValuation = businessValuation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAcquirerInfo() {
        return acquirerInfo;
    }

    public void setAcquirerInfo(String acquirerInfo) {
        this.acquirerInfo = acquirerInfo;
    }

    public String getBeMegeredInfo() {
        return beMegeredInfo;
    }

    public void setBeMegeredInfo(String beMegeredInfo) {
        this.beMegeredInfo = beMegeredInfo;
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
