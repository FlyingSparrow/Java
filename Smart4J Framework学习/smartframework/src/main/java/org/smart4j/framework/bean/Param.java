package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 请求参数对象
 * @author wangjianchun
 * @create 2017/12/26
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }

    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * @author wangjianchun
     * @description 获取所有字段信息
     * @create 2017/12/26
     */
    public Map<String, Object> getMap(){
        return paramMap;
    }

}
