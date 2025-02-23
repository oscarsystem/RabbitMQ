package com.demo.rabbit.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
	
	@Value("${rabbitmq.exchanges.name}")
	private String exchanges;
	
	@Value("${rabbitmq.routing.key}")
	private String routing_key;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(exchanges, routing_key, message);
	}
	 

}
