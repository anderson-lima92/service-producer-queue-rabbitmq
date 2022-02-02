package com.spring.rabbit.mq.producer.service;

import com.spring.rabbit.mq.producer.model.dto.Message;

public interface AmqpService {

	void senToConsumer(Message message);
	
}
