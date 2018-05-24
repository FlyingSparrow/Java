package com.huishu.ieanalysis.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author wangjianchun
 */
public class ConditionDTO {

    private String province;

    private String area;

    private Integer year;

    private Integer contrastYear;

    private Integer month;

    private Integer dataType;

    private List<String> publishType;

    private String department;

    private Integer pageNumber;

    private Integer pageSize;
    /**
     * 1,媒体;2, 社会 ;3,行业
     */
    private String vectorType;

    private String policyInfoType;

    private Date date;

    private String startTime;

    private String endTime;

    private Long hotEventMark;

    private Long day;
    /**
     * 1,媒体，2，社交
     */
    private Long reportType;

    private List<String> sites;

    private List<String> jobsName;

    private List<String> socialChannels;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getContrastYear() {
        return contrastYear;
    }

    public void setContrastYear(Integer contrastYear) {
        this.contrastYear = contrastYear;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<String> getPublishType() {
        return publishType;
    }

    public void setPublishType(List<String> publishType) {
        this.publishType = publishType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getVectorType() {
        return vectorType;
    }

    public void setVectorType(String vectorType) {
        this.vectorType = vectorType;
    }

    public String getPolicyInfoType() {
        return policyInfoType;
    }

    public void setPolicyInfoType(String policyInfoType) {
        this.policyInfoType = policyInfoType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Long getHotEventMark() {
        return hotEventMark;
    }

    public void setHotEventMark(Long hotEventMark) {
        this.hotEventMark = hotEventMark;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getReportType() {
        return reportType;
    }

    public void setReportType(Long reportType) {
        this.reportType = reportType;
    }

    public List<String> getSites() {
        return sites;
    }

    public void setSites(List<String> sites) {
        this.sites = sites;
    }

    public List<String> getJobsName() {
        return jobsName;
    }

    public void setJobsName(List<String> jobsName) {
        this.jobsName = jobsName;
    }

    public List<String> getSocialChannels() {
        return socialChannels;
    }

    public void setSocialChannels(List<String> socialChannels) {
        this.socialChannels = socialChannels;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
