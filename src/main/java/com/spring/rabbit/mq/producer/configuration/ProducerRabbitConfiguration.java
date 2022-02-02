package com.spring.rabbit.mq.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
/*
 *  Argumentos passados no queue() 
 *  	TRUE= quero que a fila seja duravel
 * 		FALSE= não quero que seja feita a exclusão 
 * 		FALSE= não que seja feita o alto completed 
 * 		ARGS= passando os argumentos criados para associar a queue a deadletter 
 */

@Configuration
public class ProducerRabbitConfiguration {

	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	
	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;
	
	@Value("${spring.rabbitmq.request.dead-letter.producer}")
	private String deadLetter;
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	Queue deadLetter() {
		return new Queue(deadLetter);
	}
	
	@Bean
	Queue queue() {
		Map<String, Object> args= new HashMap<>();
		args.put("x-dead-letter-exchange", exchange);
		args.put("x-dead-letter-routing-key", deadLetter);
		return new Queue(queue, true, false, false, args); 
	}
	
	
	//Configuração para que quando o Spring inicie ele crie as Queues de forma automatica  
	@Bean
	public Binding bindingQueue() {
		return BindingBuilder.bind(queue())
				.to(exchange()).with(queue);
	}
	
	@Bean
	public Binding bindingDeadLetter() {
		return BindingBuilder.bind(deadLetter())
				.to(exchange()).with(deadLetter);
	}
}
