package com.huishu.analysis.vo;

import com.huishu.entity.NewsLibBak;
import com.huishu.entity.PolicyBak;
import com.huishu.entity.SiteLib;
import com.huishu.entity.ZongheBak;
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
     * 文章url
     */
    private String fldUrlAddr;

    private ValidationVO(String province, String industry, String fldrecddate,
                         String fldcontent, String fldtitle, String fldUrlAddr){
        this.province = province;
        this.industry = industry;
        this.fldrecddate = fldrecddate;
        this.fldcontent = fldcontent;
        this.fldtitle = fldtitle;
        this.fldUrlAddr = fldUrlAddr;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
