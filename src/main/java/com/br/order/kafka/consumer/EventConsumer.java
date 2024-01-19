package com.br.order.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.br.order.utils.JsonUtil;

@Component
public class EventConsumer {

	private Logger logger = LoggerFactory.getLogger(EventConsumer.class);

	@Autowired
	private JsonUtil jsonUtil;
	
	@KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.notity-ending}")
	public void consumeNotifyEndingEvent(String payload) {
		this.logger.info("Receiving ending notification event {} from notify ending topic", payload);
		
		var event = jsonUtil.toEvent(payload);
		
		this.logger.info(event.toString());
	}

}
