package com.spring.rabbit.mq.producer.amqp.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spring.rabbit.mq.producer.amqp.AmqpProducer;
import com.spring.rabbit.mq.producer.dto.Message;

/*
 *  @throws AmqpRejectAndDontRequeueException exceção lançada quando ocorre qualquer tipo de erro, e ela envia para a DeadLetter.
 */

@Component
public class ProducerRabbitMQ implements AmqpProducer<Message> {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	
	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;

	@Override
	public void producer(Message message) {
		
		try {
			rabbitTemplate.convertAndSend(exchange, queue, message);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
}
