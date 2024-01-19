package com.br.order.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SagaProducer {

	private Logger logger = LoggerFactory.getLogger(SagaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.topic.start-saga}")
	private String startSagaTopic;

	public void sendEvent(String payload) {

		try {

			this.logger.info("Send event to topic {} with data {}", this.startSagaTopic, payload);
			this.kafkaTemplate.send(this.startSagaTopic, payload);

		} catch (Exception e) {
			this.logger.error("Error trying to send data {} to topic {}", this.startSagaTopic, payload, e);
		}

	}

}
