package com.flying.sparrow.bean;

import com.flying.sparrow.util.CastUtil;
import com.flying.sparrow.util.CollectionUtil;

import java.util.Map;

/**
 * 请求参数对象
 * Created by wangjianchun on 2017/11/9.
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
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public boolean isEmpty(){
        return CollectionUtil.isEmpty(paramMap);
    }
}
