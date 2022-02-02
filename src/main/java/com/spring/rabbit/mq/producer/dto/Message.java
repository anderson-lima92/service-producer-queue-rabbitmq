package com.spring.rabbit.mq.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	
	public Message() {
	}

	private String text;

}
