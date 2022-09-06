package com.ximuyi.demo.rabbitmq.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RabbitMQMessageConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQMessageConfiguration.class);

	static final String topicExchangeName = "spring-boot-exchange";

	static final String queueName = "spring-boot";

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		return template;
	}

	/**
	 * The queue() method creates an AMQP queue.
	 * @return
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean("rabbitMQMessageListenerContainer")
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter rabbitMQMessageListenerAdapter) {
		/***
		 * 这个看起来像是queue队列模式：随意只会接受到前面两行的代码：
		 * template.convertAndSend(queueName, "'' Hello from RabbitMQ!");
		 * template.convertAndSend(queueName, "'' Hi from RabbitMQ!");
		 * template.convertAndSend(topicExchangeName, queueName, "'spring-boot-exchange' Hello from RabbitMQ!");
		 * template.convertAndSend(topicExchangeName, queueName, "'spring-boot-exchange'H i from RabbitMQ!");
		 */
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		container.setMessageListener(rabbitMQMessageListenerAdapter);
		//container.setMessageListener(new ChannelAwareMessageListener() {
		//
		//	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
		//		logger.info("消费端接收到消息 : " + new String(message.getBody()));
		//		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		//	}
		//});
		return container;
	}

	@Bean("rabbitMQMessageListenerAdapter")
	MessageListenerAdapter listenerAdapter(RabbitMQMessageReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	/***
	 * 目前的疑问：
	 * 创建两个SimpleMessageListenerContainer为什么只有一个生效？
	 * @param connectionFactory
	 * @return
	 */
	//@Bean("rabbitMQMessageListenerContainerV2")
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(queue());
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> logger.info("Queue V2 消费端接收到消息 : " + new String(message.getBody())));
		return container;
	}


	@Bean("rabbitMQMessageRunner")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
			template.convertAndSend(queueName, "'' Hello from RabbitMQ!");
			template.convertAndSend(queueName, "'' Hi from RabbitMQ!");
			template.convertAndSend(topicExchangeName, queueName, "'spring-boot-exchange' Hello from RabbitMQ!");
			template.convertAndSend(topicExchangeName, queueName, "'spring-boot-exchange'H i from RabbitMQ!");
		};
	}
}
