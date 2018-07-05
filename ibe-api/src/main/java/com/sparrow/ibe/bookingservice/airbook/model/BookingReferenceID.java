package com.sparrow.ibe.bookingservice.airbook.model;

import java.io.Serializable;

/**
 * 订单号或PNR号，显示订单号或PNR号信息
 * 
 * @author wangjc
 * @date 2014-7-7
 */
public class BookingReferenceID implements Serializable {

	private static final long serialVersionUID = 3753598667527373376L;

	/**
	 * 订单号或PNR号(格式:数字或字母 5位或者6位,例:JDD8L1)
	 */
	private String id;
	/**
	 * 类型，Order或PNR
	 */
	private String idContext;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdContext() {
		return idContext;
	}

	public void setIdContext(String idContext) {
		this.idContext = idContext;
	}

}
