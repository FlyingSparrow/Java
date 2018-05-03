package com.manning.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangjianchun
 * @create 2018/5/3
 */
@Component
public class AmazonHealth implements HealthIndicator {

    @Override
    public Health health() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("https://www.amazon.cn/", String.class);
            return Health.up().build();
        } catch (RestClientException e) {
            return Health.down().withDetail("reason", e.getMessage()).build();
        }
    }
}
