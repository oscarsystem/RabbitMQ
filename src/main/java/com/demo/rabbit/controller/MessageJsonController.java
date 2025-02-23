package com.demo.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rabbit.dto.UserDTO;
import com.demo.rabbit.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("api/v1")
public class MessageJsonController {

	@Autowired
	private RabbitMQJsonProducer rabbitMQJsonProducer;
	
	@PostMapping("/publish")
	ResponseEntity<String> sendJsonMessage(@RequestBody UserDTO userDto){
		rabbitMQJsonProducer.sendJson(userDto);
		return ResponseEntity.ok("Json message send to Rabbit");
	}
	
}
