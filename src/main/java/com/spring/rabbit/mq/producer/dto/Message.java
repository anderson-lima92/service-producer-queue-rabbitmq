package com.spring.rabbit.mq.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
	
	public Message() {
	}

	private String text;

}
