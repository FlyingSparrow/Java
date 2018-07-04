package com.sparrow.ibe.bookingservice.airbook.model;

import java.io.Serializable;

/**
 * PNR预订服务响应结果类
 * @author wangjc
 * @date 2014-7-7
 */
public class AirBookResponse implements Serializable {

	private static final long serialVersionUID = -4903131714783791605L;
	
	/*//成功响应结果，0..n，一个AirReservation可对应一个订单号或一个PNR的信息
	private List<AirReservation> airReservationList = new ArrayList<AirReservation>();
	
	//错误信息集合，0..n
	private List<DefaultError> errorList = new ArrayList<DefaultError>();
	
	//警告信息集合，0..n
	private List<DefaultWarning> warningList = new ArrayList<DefaultWarning>();

	public List<AirReservation> getAirReservationList() {
		return airReservationList;
	}
	public void setAirReservationList(List<AirReservation> airReservationList) {
		this.airReservationList = airReservationList;
	}
	public List<DefaultError> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<DefaultError> errorList) {
		this.errorList = errorList;
	}
	public List<DefaultWarning> getWarningList() {
		return warningList;
	}
	public void setWarningList(List<DefaultWarning> warningList) {
		this.warningList = warningList;
	}*/
	
}
