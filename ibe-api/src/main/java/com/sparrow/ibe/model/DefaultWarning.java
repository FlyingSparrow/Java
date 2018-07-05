package com.sparrow.ibe.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 警告响应信息
 *
 * @author wangjianchun
 * @date 2018-7-4
 */
public class DefaultWarning implements Serializable {

    private static final long serialVersionUID = -5314974549784637311L;
    /**
     * 警告代码
     */
    private String code;
    /**
     * 警告类型
     */
    private String type;
    /**
     * 警告详细信息
     */
    private String shortText;
    /**
     * 中文警告信息
     */
    private String cnMessage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getCnMessage() {
        return cnMessage;
    }

    public void setCnMessage(String cnMessage) {
        this.cnMessage = cnMessage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
