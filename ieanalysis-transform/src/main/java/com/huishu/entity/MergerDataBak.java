package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "merger_bak")
public class MergerDataBak implements Serializable {

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
    //并购方
    @Column(name = "acquirer")
    private String acquirer;
    //被并购方
    @Lob
    @Column(name = "beMergered")
    private String beMergered;
    //行业
    @Column(name = "industry")
    private String industry;
    //类型
    @Column(name = "type")
    private String type;
    //是否跨国
    @Column(name = "transnational")
    private String transnational;
    //开始时间
    @Column(name = "start_time")
    private String startTime;
    //结束时间
    @Column(name = "end_time")
    private String endTime;
    //金额
    @Column(name = "merger_amount")
    private String mergerAmount;
    //并购状态
    @Column(name = "merger_status")
    private String mergerStatus;
    //股权
    @Column(name = "stock_equity")
    private String stockEquity;
    //企业估值
    @Column(name = "appraisement")
    private String businessValuation;

    @Lob
    @Column(name = "content")
    private String content;
    //并购方信息
    @Lob
    @Column(name = "acquirer_info")
    private String acquirerInfo;
    //被并购放信息
    @Lob
    @Column(name = "bemegered_info")
    private String beMegeredInfo;
    //标识
    @Column(name = "biaoshi")
    private String biaoShi;
    //来源
    @Column(name = "source")
    private String source;
    //产品
    @Column(name = "product")
    private String product;
    //并购事件
    @Column(name = "merger_event")
    private String mergerEvent;

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

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getBeMergered() {
        return beMergered;
    }

    public void setBeMergered(String beMergered) {
        this.beMergered = beMergered;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransnational() {
        return transnational;
    }

    public void setTransnational(String transnational) {
        this.transnational = transnational;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMergerAmount() {
        return mergerAmount;
    }

    public void setMergerAmount(String mergerAmount) {
        this.mergerAmount = mergerAmount;
    }

    public String getMergerStatus() {
        return mergerStatus;
    }

    public void setMergerStatus(String mergerStatus) {
        this.mergerStatus = mergerStatus;
    }

    public String getStockEquity() {
        return stockEquity;
    }

    public void setStockEquity(String stockEquity) {
        this.stockEquity = stockEquity;
    }

    public String getBusinessValuation() {
        return businessValuation;
    }

    public void setBusinessValuation(String businessValuation) {
        this.businessValuation = businessValuation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAcquirerInfo() {
        return acquirerInfo;
    }

    public void setAcquirerInfo(String acquirerInfo) {
        this.acquirerInfo = acquirerInfo;
    }

    public String getBeMegeredInfo() {
        return beMegeredInfo;
    }

    public void setBeMegeredInfo(String beMegeredInfo) {
        this.beMegeredInfo = beMegeredInfo;
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

    public String getMergerEvent() {
        return mergerEvent;
    }

    public void setMergerEvent(String mergerEvent) {
        this.mergerEvent = mergerEvent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
