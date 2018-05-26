package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "investment_bak")
public class InvestmentDataBak implements Serializable {

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
    //投资方
    @Lob
    @Column(name = "investor")
    private String investor;
    //投资公司
    @Column(name = "invested_company")
    private String investedCompany;
    //行业
    @Column(name = "industry")
    private String industry;
    //标签
    @Column(name = "label")
    private String label;
    //公司名称
    @Column(name = "company_name")
    private String companyName;
    //时间
    @Column(name = "time")
    private String time;
    //地域
    @Column(name = "region")
    private String region;

    //投资金额
    @Column(name = "amount")
    private String amount;
    //阶段
    @Column(name = "stage")
    private String stage;
    //轮数
    @Column(name = "round")
    private String round;
    //股权
    @Column(name = "equity")
    private String equity;

    @Lob
    @Column(name = "content")
    private String content;
    //信息
    @Lob
    @Column(name = "intro")
    private String intro;

    @Column(name = "biaoshi")
    private String biaoShi;

    @Column(name = "source")
    private String source;
    //产品
    @Column(name = "product")
    private String product;
    //估值
    @Column(name = "appraisement")
    private String appraisement;
    //投资事件
    @Column(name = "investment_event")
    private String investmentEvent;

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

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInvestedCompany() {
        return investedCompany;
    }

    public void setInvestedCompany(String investedCompany) {
        this.investedCompany = investedCompany;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAppraisement() {
        return appraisement;
    }

    public void setAppraisement(String appraisement) {
        this.appraisement = appraisement;
    }

    public String getInvestmentEvent() {
        return investmentEvent;
    }

    public void setInvestmentEvent(String investmentEvent) {
        this.investmentEvent = investmentEvent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
