package com.spring.rabbit.mq.producer.configuration;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitConfiguration {
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	//metodo onde definimos o ouvinte do container da fabrica de conexão
	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jacksonConverter());
		return factory;
	}
	
	//metodo onde é aplicado a forma que queremos converter a mensagem
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jacksonConverter());
		return rabbitTemplate;
	}
	
	//metodo onde definimos o padrão de conversão
	@Bean
	Jackson2JsonMessageConverter jacksonConverter() {
		final ObjectMapper mapper =  Jackson2ObjectMapperBuilder
				.json()
				.modules(new JavaTimeModule())
				.dateFormat(new StdDateFormat())
				.build();
		return new Jackson2JsonMessageConverter(mapper);
	}

}