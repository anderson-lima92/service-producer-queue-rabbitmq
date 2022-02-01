package com.spring.rabbit.mq.producer.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rabbit.mq.producer.amqp.AmqpProducer;
import com.spring.rabbit.mq.producer.dto.Message;
import com.spring.rabbit.mq.producer.service.AmqpService;

/*
 * Serviço que Produz menssagem 
 */

@Service
public class RabbitMQService implements AmqpService{

	@Autowired
	private AmqpProducer<Message> amqp;
	
	@Override
	public void senToConsumer(Message message) {
		amqp.producer(message);
	}

}
