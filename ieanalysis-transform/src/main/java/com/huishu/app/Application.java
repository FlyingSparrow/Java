package com.huishu.app;

import com.huishu.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangjianchun
 */
@Configuration
@Import({DynamicDataSourceRegister.class})
@ComponentScan("com.huishu")
@EntityScan("com.huishu.entity")
@EnableJpaRepositories(basePackages = "com.huishu.repository")
@EnableAutoConfiguration
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
