package com.sparrow.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjianchun
 * @create 2018/7/5
 */
@Configuration
public class IBEConfig {

    @Value("${custom.ibe.account.username}")
    private String username;
    @Value("${custom.ibe.account.password}")
    private String password;
    @Value("${custom.ibe.uat.dir}")
    private String uatDir;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUatDir() {
        return uatDir;
    }
}
