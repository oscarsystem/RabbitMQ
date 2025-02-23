package com.demo.rabbit.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabitMQConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.queue.json.name}")
	private String queue_json;
	
	@Value("${rabbitmq.exchanges.name}")
	private String exchanges;
	
	@Value("${rabbitmq.routing.key}")
	private String routing_key;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routing_json_key;
	
	//spring bean for rabbit queue	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	//spring bean for queue (store json messages)
	@Bean
	public Queue jsonQueue() {
		return new Queue(queue_json);
	}
	
	//spring bean for exchanges
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchanges);
	}
	
	//binding between queue and exchanges using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(exchange()).with(routing_key);
	}
	
	@Bean
	public Binding binding_json() {
		return BindingBuilder.bind(jsonQueue())
				.to(exchange()).with(routing_json_key);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate ampTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	
	/**Spring configura estos beans por defecto, no tentmos que configurarlos con @bean */
	//ConnectionFactory
	//RabbitTemplate
	//RabbitAdmin

}
