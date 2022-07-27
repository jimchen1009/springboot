package com.ximuyi.demo.redis.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

//@Configuration
public class RedisMessageConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(RedisMessageConfiguration.class);

	@Bean("redisMessageListenerContainer")
	public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter redisMessageListenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(redisMessageListenerAdapter, new PatternTopic("chat"));

		return container;
	}

	@Bean("redisMessageListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter(RedisMessageReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	/***
	 * param： xxx 这个形式的时候，会报错的喔~
	 * Parameter 0 of method redisMessageReceiver in com.ximuyi.demo.redis.messaging.RedisMessageConfiguration required a single bean, but 2 were found:
	 * 	- latch: defined by method 'latch' in class path resource [com/ximuyi/demo/redis/messaging/RedisMessageConfiguration.class]
	 * 	- redisCountDownLatch: defined by method 'redisCountDownLatch' in class path resource [com/ximuyi/demo/redis/messaging/RedisMessageConfiguration.class]
	 * @return
	 */
	@Bean
	public RedisMessageReceiver messageReceiver(@Qualifier("redisLatch0") RedisCountDownLatch latch) {
		return new RedisMessageReceiver(latch);
	}

	@Bean
	public RedisCountDownLatch redisLatch0() {
		return new RedisCountDownLatch(1, "redisLatch0");
	}

	@Bean
	public RedisCountDownLatch redisLatch1() {
		return new RedisCountDownLatch(1, "redisLatch1");
	}

	@Bean
	public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	@Bean("redisMessageRunner")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
			RedisCountDownLatch latch = ctx.getBean("redisLatch0", RedisCountDownLatch.class);

			logger.info("{} sending message...", latch.getName());
			template.convertAndSend("chat", "Hello from Redis!");

			latch.await();
		};
	}
}
