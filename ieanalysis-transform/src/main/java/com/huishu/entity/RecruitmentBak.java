package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "recruitment_bak")
public class RecruitmentBak implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4354622787077180517L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "url")
    private String fldUrlAddr;

    @Column(name = "site")
    private String webname;

    @Column(name = "title")
    private String fldtitle;

    @Column(name = "publish_time")
    private String fldrecddate;

    /**
     * 岗位数量
     */
    @Column(name = "gwNum")
    private String gwNum;
    /**
     * 岗位工资
     */
    @Column(name = "gwGZ")
    private String gwGZ;

    @Column(name = "xueli")
    private String xueli;

    @Column(name = "addr")
    private String addr;

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

    public String getFldrecddate() {
        return fldrecddate;
    }

    public void setFldrecddate(String fldrecddate) {
        this.fldrecddate = fldrecddate;
    }

    public String getGwNum() {
        return gwNum;
    }

    public void setGwNum(String gwNum) {
        this.gwNum = gwNum;
    }

    public String getGwGZ() {
        return gwGZ;
    }

    public void setGwGZ(String gwGZ) {
        this.gwGZ = gwGZ;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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
