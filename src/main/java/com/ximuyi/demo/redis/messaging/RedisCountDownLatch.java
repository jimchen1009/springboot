package com.ximuyi.demo.redis.messaging;

import java.util.concurrent.CountDownLatch;

public class RedisCountDownLatch extends CountDownLatch {

	private final String name;

	public RedisCountDownLatch(int count, String name) {
		super(count);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
