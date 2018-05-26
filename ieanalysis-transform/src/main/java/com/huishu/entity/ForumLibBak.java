package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "forum_bak")
public class ForumLibBak implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 21495733126865136L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "url")
    private String fldUrlAddr;

    @Column(name = "fldrkdz")
    private String fldrkdz;

    @Column(name = "pdmc")
    private String pdmc;

    @Column(name = "site")
    private String webname;

    @Column(name = "title")
    private String fldtitle;

    @Column(name = "author")
    private String fldAuthor;

    @Column(name = "trip")
    private String fldftrip;

    @Column(name = "content")
    private String fldcontent;

    @Column(name = "hits")
    private String fldHits;

    @Column(name = "reply")
    private String fldReply;

    @Column(name = "pzr")
    private String pzr;

    @Column(name = "time")
    private String fldrecddate;

    @Column(name = "fldzhhfdate")
    private String fldzhhfdate;

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

    public String getFldftrip() {
        return fldftrip;
    }

    public void setFldftrip(String fldftrip) {
        this.fldftrip = fldftrip;
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

    public String getFldzhhfdate() {
        return fldzhhfdate;
    }

    public void setFldzhhfdate(String fldzhhfdate) {
        this.fldzhhfdate = fldzhhfdate;
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
