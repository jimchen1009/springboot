package com.ximuyi.demo.jsp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/***
 * JSP 与 thymeleaf 暂时没有弄懂怎么配置共存，注释掉
 * @return
 */
@Configuration
public class WebJspConfiguration {

	@Bean
	public InternalResourceViewResolver jspViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/webjsp/");
		resolver.setSuffix(".jsp");
		resolver.setCache(false);
		resolver.setOrder(5);
		return resolver;

	}

}
