package com.sparrow.opinion.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.sparrow")
@EntityScan(basePackages = "com.sparrow.opinion.mysql.entity")
@EnableJpaRepositories(basePackages = "com.sparrow.opinion.mysql.repository")
@EnableMongoRepositories(basePackages = "com.sparrow.opinion.mongodb.repository")
@EnableElasticsearchRepositories(basePackages = "com.sparrow.opinion.es.repository")
@EnableAutoConfiguration
@EnableCaching
@EnableTransactionManagement
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
