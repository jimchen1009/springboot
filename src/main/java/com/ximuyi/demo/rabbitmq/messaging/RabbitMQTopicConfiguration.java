package com.ximuyi.demo.rabbitmq.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//@Configuration
//@Component
public class RabbitMQTopicConfiguration {

	static final String ExchangeName = "spring-boot-topic";

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQTopicConfiguration.class);

	@Bean(name= "message")
	public Queue queueMessage() {
		return new Queue("topic.message");
	}

	@Bean(name= "messages")
	public Queue queueMessages() {
		return new Queue("topic.messages");
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(ExchangeName);
	}

	@Bean
	Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	@Bean
	Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
	}

	@RabbitListener(queues = "topic.message")
	public void queuesProcess1(String message) {
		logger.info("{} receive ：{}", "topic.message", message);
	}


	@RabbitListener(queues = "topic.messages")
	public void process2(String message) {
		logger.info("{} receive ：{}", "topic.messages", message);
	}

	@Bean("rabbitMQTopicRunner")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
			template.convertAndSend(ExchangeName, "topic.message", "topic.message Hello from RabbitMQ Topic!");
			template.convertAndSend(ExchangeName, "topic.AAAAAAA", "topic.AAAAAAA Hello from RabbitMQ Topic!");
		};
	}
}
