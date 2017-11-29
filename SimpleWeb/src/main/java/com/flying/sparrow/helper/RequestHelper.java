package com.flying.sparrow.helper;

import com.flying.sparrow.bean.Param;
import com.flying.sparrow.framework.bean.FormParam;
import com.flying.sparrow.util.ArrayUtil;
import com.flying.sparrow.util.CodecUtil;
import com.flying.sparrow.util.StreamUtil;
import com.flying.sparrow.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * 请求助手类
 * Created by wangjianchun on 2017/11/24.
 */
public final class RequestHelper {

    public static Param createParam(HttpServletRequest request)throws IOException{
        List<FormParam> formParamList = new ArrayList<FormParam>();
        formParamList.addAll(parseParameterNames(request));
        formParamList.addAll(parseInputStream(request));
        return new Param(formParamList);
    }

    private static Collection<? extends FormParam> parseParameterNames(HttpServletRequest request) {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if(ArrayUtil.isNotEmpty(fieldValues)){
                Object fieldValue;
                if(fieldValues.length == 1){
                    fieldValue = fieldValues[0];
                }else{
                    StringBuilder sd = new StringBuilder();
                    for(int i=0; i<fieldValues.length; i++){
                        sd.append(fieldValues[i]);
                        if(i != fieldValues.length -1){
                            sd.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sd.toString();
                }
                formParamList.add(new FormParam(fieldName, fieldValue));
            }
        }
        return formParamList;
    }

    private static List<FormParam> parseInputStream(HttpServletRequest request) {
        List<FormParam> formParamList = new ArrayList<FormParam>();
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if(StringUtils.isNotEmpty(body)){
            String[] kvs = StringUtils.split(body,"&");
            if(ArrayUtil.isNotEmpty(kvs)){
                for(String kv : kvs){
                    String[] array = StringUtils.split(kv, "=");
                    if(ArrayUtil.isNotEmpty(array) && array.length == 2){
                        String fieldName = array[0];
                        String fieldValue = array[1];
                        formParamList.add(new FormParam(fieldName, fieldValue));
                    }
                }
            }
        }

        return formParamList;
    }

}
