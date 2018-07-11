package com.sparrow.ibe.bookingservice.airbook;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 旅客姓名项: 当婴儿票预订时,可以输入两组(中文和英文各一组)
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
public class PersonNameVO {

    /**
     * 旅客姓名语种，0..1，姓名的输入语言，中文 - ZH；英文 - EN，
     * 婴儿票输入护照信息前，需要输入英文姓名
     */
    private String languageType;
    /**
     * 旅客姓名，格式如：中文：张三；英文：Zhang/san
     */
    private String surname;

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
