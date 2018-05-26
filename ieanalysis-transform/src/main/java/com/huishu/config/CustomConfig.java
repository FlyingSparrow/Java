package com.huishu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类，存储 application.properties 文件中所有的自定义配置项信息
 *
 * @author wangjianchun
 */
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {


}
