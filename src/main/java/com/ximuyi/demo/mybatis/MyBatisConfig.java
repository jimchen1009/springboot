package com.ximuyi.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ximuyi.demo.mybatis.mapper")
public class MyBatisConfig {
}
