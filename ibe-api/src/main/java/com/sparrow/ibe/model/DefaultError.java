package com.sparrow.ibe.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author wangjianchun
 * @date 2018-7-4
 */
public class DefaultError implements Serializable {

    /**
     * 错误响应信息
     */
    private static final long serialVersionUID = 1128501737979283068L;
    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误类型
     */
    private String type;
    /**
     * 错误详细信息
     */
    private String shortText;
    /**
     * 中文错误信息
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
