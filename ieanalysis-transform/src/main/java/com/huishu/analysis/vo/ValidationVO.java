package com.huishu.analysis.vo;

import com.huishu.entity.*;
import com.huishu.utils.DateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wangjianchun
 * @create 2018/5/31
 */
public class ValidationVO {

    /**
     * 省份
     */
    private String province;
    /**
     * 行业
     */
    private String industry;
    /**
     * 发布时间
     */
    private String fldrecddate;
    /**
     * 内容
     */
    private String fldcontent;
    /**
     * 标题
     */
    private String fldtitle;
    /**
     * url
     */
    private String fldUrlAddr;
    /**
     * 岗位工资
     */
    private String gwGZ;
    /**
     * 地址
     */
    private String addr;

    private ValidationVO(String province, String industry, String fldrecddate,
                         String fldcontent, String fldtitle, String fldUrlAddr){
        this.province = province;
        this.industry = industry;
        this.fldrecddate = fldrecddate;
        this.fldcontent = fldcontent;
        this.fldtitle = fldtitle;
        this.fldUrlAddr = fldUrlAddr;
    }

    private ValidationVO(String fldUrlAddr){
        this.fldUrlAddr = fldUrlAddr;
    }

    public ValidationVO(String province, String industry, String publishDate, String content, String title) {
        this.province = province;
        this.industry = industry;
        this.fldrecddate = publishDate;
        this.fldcontent = content;
        this.fldtitle = title;
    }

    public ValidationVO(String gwGZ, String addr, String publishDate) {
        this.gwGZ = gwGZ;
        this.addr = addr;
        this.fldrecddate = publishDate;
    }

    public static ValidationVO create(NewsLibBak news, SiteLib siteLib){
        return new ValidationVO(siteLib.getProvince(), siteLib.getIndustry(),
                news.getFldrecddate(), news.getFldcontent(), news.getFldtitle(), news.getFldUrlAddr());
    }

    public static ValidationVO create(PolicyBak news, SiteLib siteLib){
        return new ValidationVO(siteLib.getProvince(), siteLib.getIndustry(),
                news.getFldrecddate(), news.getFldcontent(), news.getFldtitle(), news.getFldUrlAddr());
    }

    public static ValidationVO create(ZongheBak news, SiteLib siteLib){
        return new ValidationVO(siteLib.getProvince(), siteLib.getIndustry(),
                news.getFldrecddate(), news.getFldcontent(), news.getFldtitle(), news.getFldUrlAddr());
    }

    public static ValidationVO create(ForumLibBak forumLibBak, SiteLib siteLib){
        return new ValidationVO(siteLib.getProvince(), siteLib.getIndustry(),
                forumLibBak.getFldrecddate(), forumLibBak.getFldcontent(),
                forumLibBak.getFldtitle(), forumLibBak.getFldUrlAddr());
    }

    public static ValidationVO create(VideoBak videoBak){
        return new ValidationVO(videoBak.getUrl());
    }

    public static ValidationVO create(Wechat wechat, SiteLib siteLib){
        String publishDate = DateUtils.getFormatTime(wechat.getPostTime());

        return new ValidationVO(siteLib.getProvince(), siteLib.getIndustry(),
                publishDate, wechat.getContent(), wechat.getTitle());
    }

    public static ValidationVO create(RecruitmentBak recruitmentBak){
        return new ValidationVO(recruitmentBak.getGwGZ(), recruitmentBak.getAddr(), recruitmentBak.getFldrecddate());
    }

    public String getProvince() {
        return province;
    }

    public String getIndustry() {
        return industry;
    }

    public String getFldrecddate() {
        return fldrecddate;
    }

    public String getFldcontent() {
        return fldcontent;
    }

    public String getFldtitle() {
        return fldtitle;
    }

    public String getFldUrlAddr() {
        return fldUrlAddr;
    }

    public String getGwGZ() {
        return gwGZ;
    }

    public String getAddr() {
        return addr;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
