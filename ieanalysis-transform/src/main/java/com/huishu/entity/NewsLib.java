package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "news")
public class NewsLib implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 21495733126865136L;

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

    @Column(name = "fldrkdz")
    private String fldrkdz;

    @Column(name = "pdmc")
    private String pdmc;

    @Column(name = "webname")
    private String webname;

    @Column(name = "fldtitle")
    private String fldtitle;

    @Column(name = "fldAuthor")
    private String fldAuthor;

    @Column(name = "fldzhuanzai")
    private String fldzhuanzai;

    @Lob
    @Column(name = "Fldcontent")
    private String fldcontent;

    @Column(name = "fldHits")
    private String fldHits;

    @Column(name = "fldReply")
    private String fldReply;

    @Lob
    @Column(name = "fldpinglunurl")
    private String fldpinglunurl;

    @Column(name = "pzr")
    private String pzr;

    @Column(name = "fldrecddate")
    private String fldrecddate;

    @Column(name = "date_2")
    private String date2;

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

    public String getFldrkdz() {
        return fldrkdz;
    }

    public void setFldrkdz(String fldrkdz) {
        this.fldrkdz = fldrkdz;
    }

    public String getPdmc() {
        return pdmc;
    }

    public void setPdmc(String pdmc) {
        this.pdmc = pdmc;
    }

    public String getWebname() {
        return webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
    }

    public String getFldtitle() {
        return fldtitle;
    }

    public void setFldtitle(String fldtitle) {
        this.fldtitle = fldtitle;
    }

    public String getFldAuthor() {
        return fldAuthor;
    }

    public void setFldAuthor(String fldAuthor) {
        this.fldAuthor = fldAuthor;
    }

    public String getFldzhuanzai() {
        return fldzhuanzai;
    }

    public void setFldzhuanzai(String fldzhuanzai) {
        this.fldzhuanzai = fldzhuanzai;
    }

    public String getFldcontent() {
        return fldcontent;
    }

    public void setFldcontent(String fldcontent) {
        this.fldcontent = fldcontent;
    }

    public String getFldHits() {
        return fldHits;
    }

    public void setFldHits(String fldHits) {
        this.fldHits = fldHits;
    }

    public String getFldReply() {
        return fldReply;
    }

    public void setFldReply(String fldReply) {
        this.fldReply = fldReply;
    }

    public String getFldpinglunurl() {
        return fldpinglunurl;
    }

    public void setFldpinglunurl(String fldpinglunurl) {
        this.fldpinglunurl = fldpinglunurl;
    }

    public String getPzr() {
        return pzr;
    }

    public void setPzr(String pzr) {
        this.pzr = pzr;
    }

    public String getFldrecddate() {
        return fldrecddate;
    }

    public void setFldrecddate(String fldrecddate) {
        this.fldrecddate = fldrecddate;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
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
