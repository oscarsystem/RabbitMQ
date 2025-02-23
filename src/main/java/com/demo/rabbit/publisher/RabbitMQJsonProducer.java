package com.demo.rabbit.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.rabbit.dto.UserDTO;

@Service
public class RabbitMQJsonProducer {
	
	@Value("${rabbitmq.exchanges.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routing_json_key;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendJson(UserDTO userDto) {		
		LOGGER.info(String.format("Json message -> %s", userDto.toString()));
		rabbitTemplate.convertAndSend(exchange, routing_json_key, userDto);		
	}

}
