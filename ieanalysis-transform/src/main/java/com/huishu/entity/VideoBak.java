package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "video_bak")
public class VideoBak implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 21495733126865136L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "url_addr")
    private String fldUrlAddr;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String faburen;

    @Column(name = "publish_time")
    private String fabushijian;

    @Column(name = "collection_time")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFldUrlAddr() {
        return fldUrlAddr;
    }

    public void setFldUrlAddr(String fldUrlAddr) {
        this.fldUrlAddr = fldUrlAddr;
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
