package com.spring.rabbit.mq.producer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rabbit.mq.producer.dto.Message;
import com.spring.rabbit.mq.producer.service.AmqpService;

@RestController
public class AmqpAPI {

	@Autowired
	AmqpService amqpService;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/send")
	public void sendToConsumer(@RequestBody Message message) {
		amqpService.senToConsumer(message);
	}

}
