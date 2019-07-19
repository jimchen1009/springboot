package com.ximuyi.demo.redis.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisMessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(RedisMessageReceiver.class);

	private RedisCountDownLatch latch;

	public RedisMessageReceiver(RedisCountDownLatch latch) {
		this.latch = latch;
	}

	public void receiveMessage(String message) {
		logger.info("{} received <{}>", latch.getName(), message);
		latch.countDown();
	}
}
