package com.ximuyi.demo.resttemple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/resttemple")
@RestController
public class RestTempleController {

	private static final Logger logger = LoggerFactory.getLogger(RestTempleController.class);

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}

	@Bean
	public RestTemplateBuilder getRestTemplateBuilder (){
		RestTemplateBuilder builder = new RestTemplateBuilder();
		builder.setConnectTimeout(3000);
		builder.setReadTimeout(3000);
		return builder;
	}


	@RequestMapping(value = "/quote", method = RequestMethod.GET)
	public Quote getQuote(){
		Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		logger.info("{}", quote);
		return quote;
	}
}
