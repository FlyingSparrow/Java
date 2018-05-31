package com.huishu.analysis.vo;

import com.huishu.entity.NewsLibBak;
import com.huishu.entity.PolicyBak;
import com.huishu.entity.SiteLib;
import com.huishu.entity.ZongheBak;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;

/**
 * @author wangjianchun
 * @create 2018/5/31
 */
public class NewsVO {

    private Long id;

    private String fldUrlAddr;

    private String fldrkdz;

    private String pdmc;

    private String webname;

    private String fldtitle;

    private String fldAuthor;

    private String fldzhuanzai;

    private String fldcontent;

    private String fldHits;

    private String fldReply;

    private String fldpinglunurl;

    private String pzr;

    private String fldrecddate;

    private String date2;

    private String biaoShi;

    private String type;

    private String industry;

    private String area;

    private String province;

    public static NewsVO create(NewsLibBak source, SiteLib siteLib){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);
        fillSiteLibInfo(siteLib, target);

        return target;
    }

    public static NewsVO create(PolicyBak source, SiteLib siteLib){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);
        fillSiteLibInfo(siteLib, target);

        return target;
    }

    public static NewsVO create(ZongheBak source, SiteLib siteLib){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);
        fillSiteLibInfo(siteLib, target);

        return target;
    }

    private static void fillSiteLibInfo(SiteLib siteLib, NewsVO target) {
        target.setProvince(siteLib.getProvince());
        target.setArea(siteLib.getArea());
        target.setIndustry(siteLib.getIndustry());
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
