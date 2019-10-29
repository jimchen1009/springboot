package com.ximuyi.demo.tomcat;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatServletWebConfiguration {

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addContextCustomizers(new DocumentDirectoryCustomizer());
		return factory;
	}
}
