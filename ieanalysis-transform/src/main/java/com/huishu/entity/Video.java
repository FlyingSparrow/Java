package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "video")
public class Video implements Serializable {

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

    @Column(name = "name")
    private String name;

    @Column(name = "faburen")
    private String faburen;

    @Column(name = "fabushijian")
    private String fabushijian;

    @Column(name = "caijishijian")
    private Date caijishijian;

    @Column(name = "laiyuan")
    private String laiyuan;

    @Lob
    @Column(name = "tupain")
    private String tupain;

    @Column(name = "bofangshu")
    private String bofangshu;

    @Column(name = "url")
    private String url;

    @Lob
    @Column(name = "jianjie")
    private String jianjie;

    @Column(name = "webname")
    private String webname;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaburen() {
        return faburen;
    }

    public void setFaburen(String faburen) {
        this.faburen = faburen;
    }

    public String getFabushijian() {
        return fabushijian;
    }

    public void setFabushijian(String fabushijian) {
        this.fabushijian = fabushijian;
    }

    public Date getCaijishijian() {
        return caijishijian;
    }

    public void setCaijishijian(Date caijishijian) {
        this.caijishijian = caijishijian;
    }

    public String getLaiyuan() {
        return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
        this.laiyuan = laiyuan;
    }

    public String getTupain() {
        return tupain;
    }

    public void setTupain(String tupain) {
        this.tupain = tupain;
    }

    public String getBofangshu() {
        return bofangshu;
    }

    public void setBofangshu(String bofangshu) {
        this.bofangshu = bofangshu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getWebname() {
        return webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
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
