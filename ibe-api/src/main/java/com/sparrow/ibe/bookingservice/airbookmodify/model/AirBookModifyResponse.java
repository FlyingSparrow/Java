package com.sparrow.ibe.bookingservice.airbookmodify.model;

import com.sparrow.ibe.model.DefaultError;
import com.sparrow.ibe.model.DefaultWarning;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务响应model类
 *
 * @author wangjc 2014-7-7
 */
public class AirBookModifyResponse implements Serializable {

    private static final long serialVersionUID = -3867646293954753864L;

    /**
     * 错误信息集合
     */
    private List<DefaultError> errorList;

    /**
     * 错误信息集合
     */
    private List<DefaultWarning> warningList;

    /**
     * 备注信息集合
     */
    private List<String> commentList;

    /**
     * 预订序列号
     */
    private String bookingReferenceID;

    private String bookingReferenceIDContext;

    public String getBookingReferenceID() {
        return bookingReferenceID;
    }

    public void setBookingReferenceID(String bookingReferenceID) {
        this.bookingReferenceID = bookingReferenceID;
    }

    public String getBookingReferenceIDContext() {
        return bookingReferenceIDContext;
    }

    public void setBookingReferenceIDContext(String bookingReferenceIDContext) {
        this.bookingReferenceIDContext = bookingReferenceIDContext;
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
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
