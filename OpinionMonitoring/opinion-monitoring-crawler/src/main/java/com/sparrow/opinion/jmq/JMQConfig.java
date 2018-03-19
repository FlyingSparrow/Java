package com.sparrow.opinion.jmq;

import com.jd.jmq.client.connection.ClusterTransportManager;
import com.jd.jmq.client.connection.TransportConfig;
import com.jd.jmq.client.connection.TransportManager;
import com.jd.jmq.client.consumer.ConsumerConfig;
import com.jd.jmq.client.consumer.MessageConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JMQConfig
 */
@Configuration
public class JMQConfig {

    //消费者app。 JMQ中的app等同于MQ中的systemId
    @Value("${custom.jmq.consumer.app}")
    private String app;
    //消费者topic。JMQ中的topic等同于MQ中的destination
    @Value("${custom.jmq.consumer.topic}")
    private String topic;
    @Value("${custom.jmq.address}")
    private String address;
    @Value("${custom.jmq.user}")
    private String user;
    @Value("${custom.jmq.password}")
    private String password;

    @Bean
    public TransportManager transportManagerBean() {
        TransportConfig config = new TransportConfig();
        config.setApp(app);
        //设置broker地址
        config.setAddress(address);
        //设置用户名
        config.setUser(user);
        //设置密码
        config.setPassword(password);
        //设置发送超时
        config.setSendTimeout(5000);

        //设置是否使用epoll模式，windows环境下设置为false，linux环境下设置为true
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            config.setEpoll(false);
        } else {
            config.setEpoll(true);
        }

        return new ClusterTransportManager(config);
    }

    @Bean
    public MessageConsumer messageConsumerBean() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setLongPull(10000);
        consumerConfig.setPullTimeout(11000);
        consumerConfig.setAutoStart(true);
        consumerConfig.setMaxConcurrent(10);
        consumerConfig.setMinConcurrent(5);
        TransportManager manager = transportManagerBean();
        return new MessageConsumer(consumerConfig, manager, null);
    }

}
