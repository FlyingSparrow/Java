package com.huishu.ieanalysis.echarts.vo;

import java.io.Serializable;

/**
 * @author wangjianchun
 */
public class DataLongVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4225774608066629821L;

	private String name;
	
	private Long value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
