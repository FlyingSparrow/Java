package com.sparrow.opinion.task;

import com.jd.jmq.client.consumer.MessageConsumer;
import com.sparrow.opinion.config.CustomConfig;
import com.sparrow.opinion.jmq.DefaultMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title: JMQMessageConsumerTask</p>
 * <p>Description: 京东JMQ订阅消息主题定时器</p>
 * 注意：JMQPullMessageTask 和 JMQMessageConsumerTask 不可以同时运行，否则会报错
 * 简单地说，JMQPullMessageTask 是以拉取的方式消费JMQ中的消息，
 * JMQMessageConsumerTask 是以订阅的方式，或者说是服务器推送的方式消费JMQ中的消息，
 * 根据实际业务的需要，选择一种方式即可
 * @author Wangjianchun
 * @date 2017年6月29日
 */
@Component
public class JMQMessageConsumerTask implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MessageConsumer messageConsumer;
	@Autowired
	private DefaultMessageListener defaultMessageListener;
	//消费者topic。JMQ中的topic等同于MQ中的destination
	@Value("${custom.jmq.consumer.topic}")
	private String topic;
	@Autowired
	private CustomConfig customConfig;

	/**
	 * 项目启动后执行
	 * @param strings
	 * @throws Exception
	 */
	@Override
	public void run(String... strings) throws Exception {
		if(!customConfig.getSchedulerTaskEnable()){
			return;
		}

		try {
			logger.info("京东JMQ订阅消息主题开始");

			//启动消费者
			messageConsumer.start();
			messageConsumer.subscribe(topic, defaultMessageListener);
			logger.info("京东JMQ订阅消息主题结束");
		} catch (Exception e) {
			logger.error("京东JMQ出错，异常信息：{} ", e);
		}
	}
}
