package com.ximuyi.demo.rabbitmq.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * http://localhost:15672/
 */
//@Component
public class RabbitMQMessageReceiver {

	static final String topicExchangeName = "spring-boot-exchange";

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQMessageReceiver.class);


	/**
	 * The exchange() method creates a topic exchange.
	 * @return
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	/**
	 * The binding() method binds these two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange.
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	public void receiveMessage(String message) {
		logger.info("Queue消费端接收到消息:", message);
	}
}
