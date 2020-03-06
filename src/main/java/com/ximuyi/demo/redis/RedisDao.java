package com.ximuyi.demo.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

	@Autowired
	private StringRedisTemplate template;


	public void set(String key, String value){
		ValueOperations<String, String> ops = template.opsForValue();
		ops.set(key, value, 10, TimeUnit.MINUTES);//1分钟过期
	}

	public String get(String key) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		return ops.get(key);
	}
}
