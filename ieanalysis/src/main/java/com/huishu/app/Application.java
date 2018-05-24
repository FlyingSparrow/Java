package com.huishu.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangjianchun
 */
@Configuration
@ComponentScan("com.huishu")
@EntityScan("com.huishu.ieanalysis.mysql.entity")
@EnableJpaRepositories(basePackages = "com.huishu.ieanalysis.mysql.resposity")
@EnableElasticsearchRepositories(basePackages = "com.huishu.ieanalysis.es.repository")
@EnableAutoConfiguration
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
