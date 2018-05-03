package com.manning.configuration;

import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjianchun
 * @create 2018/5/3
 */
@Configuration
public class ActuatorConfig {

    @Bean
    public InMemoryTraceRepository traceRepository(){
        InMemoryTraceRepository traceRepository = new InMemoryTraceRepository();
        traceRepository.setCapacity(1000);
        return traceRepository;
    }
}
