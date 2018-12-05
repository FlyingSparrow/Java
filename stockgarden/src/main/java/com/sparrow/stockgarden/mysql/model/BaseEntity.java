package com.sparrow.stockgarden.mysql.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p>Title: BaseEntity</p>
 * <p>Description: 实体类的公共基类</p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

