package com.sparrow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title: CustomConfig</p>
 * <p>Description: 自定义配置类，存储 application.properties 文件中所有的自定义配置项信息</p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Component
public class CustomConfig {

    @Value("${custom.static.url}")
    private String staticUrl;
    @Value("${custom.file.profile-pictures.url}")
    private String profilePicturesUrl;
    @Value("${custom.file.background-pictures.url}")
    private String backgroundPicturesUrl;

    public String getStaticUrl() {
        return staticUrl;
    }

    public String getProfilePicturesUrl() {
        return profilePicturesUrl;
    }

    public String getBackgroundPicturesUrl() {
        return backgroundPicturesUrl;
    }
}
