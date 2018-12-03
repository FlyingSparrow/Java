package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * PNR预订服务的请求模型类，存储请求的参数信息
 * 
 * @author wangjc
 * @date 2014-7-7
 */
public class AirBookRequest implements Serializable {

	/**
	 * 请求id
	 */
	private String requestId;
	/**
	 * 请求创建时间
	 */
	private Long createdTime;
	/**
	 * 是否验证航段状态
	 */
	private String segmentCheckInd;

	/**
	 * 是否同类型旅客预定在同一 PNR
	 */
	private String ptcBindInd;

	/**
	 * 是否作 RT
	 */
	private String displayResInd;

	/**
	 * 业务类型
	 */
	private String pseudoCityCode;

	/**
	 * 是否自动添加ARNK地面运输航段
	 */
	private String autoARNKInd;

	/**
	 * 出发到达信息集合，required，1..n，最少一个
	 */
	private List<OriginDestinationOption> originDestinationOptionList;

	/**
	 * 旅客信息集合，1..n
	 */
	private List<AirTraveler> airTravelerList;

	/**
	 * 其他服务信息集合，0..n，OSI信息
	 */
	private List<OtherServiceInformation> osiList;

	/**
	 * 特殊服务信息，0..n
	 */
	private List<SpecialServiceRequest> ssrList;

	/**
	 * 特殊备注信息，0..n
	 */
	private List<SpecialRemark> rmkList;

	/**
	 * 出票时限，required
	 */
	private String ticketTimeLimit;

	/**
	 * 联系方式，0..n
	 */
	private List<String> contactInfoList;

	/**
	 * 封口方式，0..1，建议输入KI
	 */
	private String envelopType;

	/**
	 * 封口延迟，0..1，default true，若需一次封口，请设置为false
	 */
	private String envelopDelay;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public String getSegmentCheckInd() {
		return segmentCheckInd;
	}

	public void setSegmentCheckInd(String segmentCheckInd) {
		this.segmentCheckInd = segmentCheckInd;
	}

	public String getPtcBindInd() {
		return ptcBindInd;
	}

	public void setPtcBindInd(String ptcBindInd) {
		this.ptcBindInd = ptcBindInd;
	}

	public String getDisplayResInd() {
		return displayResInd;
	}

	public void setDisplayResInd(String displayResInd) {
		this.displayResInd = displayResInd;
	}

	public String getPseudoCityCode() {
		return pseudoCityCode;
	}

	public void setPseudoCityCode(String pseudoCityCode) {
		this.pseudoCityCode = pseudoCityCode;
	}

	public String getAutoARNKInd() {
		return autoARNKInd;
	}

	public void setAutoARNKInd(String autoARNKInd) {
		this.autoARNKInd = autoARNKInd;
	}

	public List<OriginDestinationOption> getOriginDestinationOptionList() {
		return originDestinationOptionList;
	}

	public void setOriginDestinationOptionList(List<OriginDestinationOption> originDestinationOptionList) {
		this.originDestinationOptionList = originDestinationOptionList;
	}

	public List<AirTraveler> getAirTravelerList() {
		return airTravelerList;
	}

	public void setAirTravelerList(List<AirTraveler> airTravelerList) {
		this.airTravelerList = airTravelerList;
	}

	public List<OtherServiceInformation> getOsiList() {
		return osiList;
	}

	public void setOsiList(List<OtherServiceInformation> osiList) {
		this.osiList = osiList;
	}

	public List<SpecialServiceRequest> getSsrList() {
		return ssrList;
	}

	public void setSsrList(List<SpecialServiceRequest> ssrList) {
		this.ssrList = ssrList;
	}

	public List<SpecialRemark> getRmkList() {
		return rmkList;
	}

	public void setRmkList(List<SpecialRemark> rmkList) {
		this.rmkList = rmkList;
	}

	public String getTicketTimeLimit() {
		return ticketTimeLimit;
	}

	public void setTicketTimeLimit(String ticketTimeLimit) {
		this.ticketTimeLimit = ticketTimeLimit;
	}

	public List<String> getContactInfoList() {
		return contactInfoList;
	}

	public void setContactInfoList(List<String> contactInfoList) {
		this.contactInfoList = contactInfoList;
	}

	public String getEnvelopType() {
		return envelopType;
	}

	public void setEnvelopType(String envelopType) {
		this.envelopType = envelopType;
	}

	public String getEnvelopDelay() {
		return envelopDelay;
	}

	public void setEnvelopDelay(String envelopDelay) {
		this.envelopDelay = envelopDelay;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
