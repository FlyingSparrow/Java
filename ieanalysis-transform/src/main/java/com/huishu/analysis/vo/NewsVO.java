package com.huishu.analysis.vo;

import com.huishu.entity.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;

import java.util.Date;

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

    private String bofangshu;

    private String fabushijian;

    private String url;

    private String title;

    private String content;

    private Date postTime;

    private String author;

    private String urls;

    /**
     * 观看数
     */
    private String viewNum;

    /**
     * 点赞数
     */
    private String likeNum;

    /**
     * 岗位数量
     */
    private String gwNum;
    /**
     * 岗位工资
     */
    private String gwGZ;

    private String xueli;

    private String addr;

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

    public static NewsVO create(ForumLibBak source, SiteLib siteLib){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);
        fillSiteLibInfo(siteLib, target);

        return target;
    }

    public static NewsVO create(VideoBak source, SiteLib siteLib){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);
        fillSiteLibInfo(siteLib, target);

        return target;
    }

    public static NewsVO create(Wechat source, SiteLib siteLib){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);
        fillSiteLibInfo(siteLib, target);

        return target;
    }

    public static NewsVO create(RecruitmentBak source){
        NewsVO target = new NewsVO();
        BeanUtils.copyProperties(source, target);

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

    public String getBofangshu() {
        return bofangshu;
    }

    public void setBofangshu(String bofangshu) {
        this.bofangshu = bofangshu;
    }

    public String getFabushijian() {
        return fabushijian;
    }

    public void setFabushijian(String fabushijian) {
        this.fabushijian = fabushijian;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
