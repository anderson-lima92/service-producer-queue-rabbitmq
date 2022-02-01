package com.spring.rabbit.mq.producer.amqp;

public interface AmqpProducer<T> {
	void producer(T t);

}
