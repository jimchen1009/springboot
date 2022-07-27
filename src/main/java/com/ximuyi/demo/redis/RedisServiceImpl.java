package com.ximuyi.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private StringRedisTemplate template;

	@Override
	public void set(String key, String value) {
		template.opsForValue().set(key, value);
	}

	@Override
	public String get(String key) {
		return template.opsForValue().get(key);
	}

	@Override
	public boolean expire(String key, long expire) {
		return template.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public void remove(String key) {
		template.delete(key);
	}

	@Override
	public Long increment(String key, long delta) {
		return template.opsForValue().increment(key,delta);
	}
}
