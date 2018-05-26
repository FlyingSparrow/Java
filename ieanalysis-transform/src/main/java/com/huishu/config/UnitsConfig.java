package com.huishu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * WordCloud 路径配置
 * 线程数配置
 *
 * @author wangjianchun
 */
@Component
@PropertySource("classpath:units.properties")
public class UnitsConfig {

    @Value("${types}")
    private String types;
    @Value("${suffix}")
    private String suffix;
    @Value("${china.cny}")
    private String chinaUnits;

    public String getTypes() {
        return types;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getChinaUnits() {
        return chinaUnits;
    }
}
