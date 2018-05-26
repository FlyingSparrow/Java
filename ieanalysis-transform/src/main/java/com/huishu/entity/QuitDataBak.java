package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "quit_bak")
public class QuitDataBak implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -79564435348390014L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "url")
    private String fldUrlAddr;

    //退出企业
    @Column(name = "invested_company")
    private String investedCompany;

    //退出机构
    @Column(name = "investor")
    private String investor;

    //资本类型
    @Column(name = "type")
    private String type;

    //回报金额
    @Column(name = "return_amount")
    private String returnAmount;

    //回报倍数
    @Column(name = "return_multiple")
    private String returnMultiple;
    //公司名称
    @Lob
    @Column(name = "company_name")
    private String companyName;
    //地域
    @Column(name = "region")
    private String region;
    //行业
    @Column(name = "industry")
    private String industry;
    //行业
    @Column(name = "organization")
    private String organization;
    //时间
    @Column(name = "time")
    private String time;
    //退出方式
    @Column(name = "quit_way")
    private String quitWay;
    //股票编码
    @Column(name = "stock_code")
    private String stockCode;
    //基金名称
    @Column(name = "fund_name")
    private String fundName;
    //内容
    @Lob
    @Column(name = "content")
    private String content;
    //退出回报
    @Lob
    @Column(name = "quit_return")
    private String quitReturn;
    //投资时间
    @Lob
    @Column(name = "investment_event")
    private String investmentEvent;
    //退出事件
    @Lob
    @Column(name = "quit_event")
    private String quitEvent;
    //产品
    @Column(name = "product")
    private String product;
    //融资金额
    @Column(name = "zongtouzie")
    private String zongtouzie;

    @Column(name = "biaoshi")
    private String biaoShi;
    //来源
    @Column(name = "source")
    private String source;

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

    public String getInvestedCompany() {
        return investedCompany;
    }

    public void setInvestedCompany(String investedCompany) {
        this.investedCompany = investedCompany;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnMultiple() {
        return returnMultiple;
    }

    public void setReturnMultiple(String returnMultiple) {
        this.returnMultiple = returnMultiple;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuitWay() {
        return quitWay;
    }

    public void setQuitWay(String quitWay) {
        this.quitWay = quitWay;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuitReturn() {
        return quitReturn;
    }

    public void setQuitReturn(String quitReturn) {
        this.quitReturn = quitReturn;
    }

    public String getInvestmentEvent() {
        return investmentEvent;
    }

    public void setInvestmentEvent(String investmentEvent) {
        this.investmentEvent = investmentEvent;
    }

    public String getQuitEvent() {
        return quitEvent;
    }

    public void setQuitEvent(String quitEvent) {
        this.quitEvent = quitEvent;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getZongtouzie() {
        return zongtouzie;
    }

    public void setZongtouzie(String zongtouzie) {
        this.zongtouzie = zongtouzie;
    }

    public String getBiaoShi() {
        return biaoShi;
    }

    public void setBiaoShi(String biaoShi) {
        this.biaoShi = biaoShi;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
