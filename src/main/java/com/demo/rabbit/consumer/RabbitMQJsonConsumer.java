package com.demo.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.demo.rabbit.dto.UserDTO;

@Service
public class RabbitMQJsonConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	@RabbitListener(queues = "${rabbitmq.queue.json.name}")
	public void consume(UserDTO userDto) {
		LOGGER.info(String.format("Recived message -> %s", userDto.toString()));
	}

}
