package com.ximuyi.demo.nameconflict;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conflict1Configuration {

	private static final Logger logger = LoggerFactory.getLogger(Conflict1Configuration.class);

	public Conflict1Configuration() {
	}

	/**
	 * INFO存在日志：[源码只打印了INFO,没有WARN]
	 * Overriding bean definition for bean 'sameBean' with a different definition:
	 * replacing [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=conflict1Configuration; factoryMethodName=sameBean; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [com/ximuyi/demo/nameconflict/Conflict1Configuration.class]]
	 * with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=conflict2Configuration; factoryMethodName=sameBean; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [com/ximuyi/demo/nameconflict/Conflict2Configuration.class]]
	 *
	 * 调试结果发现相同Bean名字(如下)，已经不能被实例化了
	 * 全局仅仅只有一个Bean名字对应一个Object。 这个潜规则是真的嘛？不报错是真的合理吗？
	 * @return
	 */
	@Bean
	public String sameBean(){
		return "Bean A";
	}

	@Bean("sameNameARunner")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.info("{}", ctx.getBean("sameBean", String.class));
		};
	}
}
