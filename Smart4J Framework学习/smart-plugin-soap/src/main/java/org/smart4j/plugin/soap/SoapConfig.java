package org.smart4j.plugin.soap;

import org.smart4j.framework.helper.ConfigHelper;

/**
 * @author wangjianchun
 * @create 2018/1/26
 */
public class SoapConfig {

    public static boolean isLog(){
        return ConfigHelper.getBoolean(SoapConstant.LOG);
    }

}
