package com.sparrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>Title: StockgardenApplication</p>
 * <p>Description: 项目启动类</p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableCaching
public class StockgardenApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StockgardenApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StockgardenApplication.class);
	}
}
