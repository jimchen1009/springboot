package com.ximuyi.demo.nameconflict;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conflict2Configuration {

	private static final Logger logger = LoggerFactory.getLogger(Conflict2Configuration.class);

	@Autowired
	private String sameBean;

	public Conflict2Configuration() {
	}

	/**
	 * 调试结果发现相同Bean名字(如下)，已经不能被实例化了
	 * 全局仅仅只有一个Bean名字对应一个Object。 这个潜规则是真的嘛？不报错是真的合理吗？
	 * 存在配置的~ 在2.0版本配置暂时没找到~
	 * @return
	 */
	@Bean
	public String sameBean(){
		return "Bean B";
	}

	@Bean("sameNameBRunner")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.info("{} {}", sameBean, ctx.getBean("sameBean", String.class));
		};
	}
}
