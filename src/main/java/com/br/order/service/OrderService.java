package com.br.order.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.order.document.Event;
import com.br.order.document.Order;
import com.br.order.dto.OrderRequest;
import com.br.order.kafka.producer.SagaProducer;
import com.br.order.repository.OrderRepository;
import com.br.order.utils.JsonUtil;

@Service
public class OrderService {

	private static final String TRANSACTION_ID_PATTERN = "%s_%s";

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JsonUtil jsonUtil;

	@Autowired
	private SagaProducer sagaProducer;

	@Autowired
	private EventService eventService;

	public Order save(OrderRequest orderRequest) {

		var order = new Order();
		order.setProducts(orderRequest.getProducts());
		order.setCreatedAt(LocalDateTime.now());
		order.setTransactionId(String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID()));

		var orderSaved = this.orderRepository.save(order);
		
		this.sagaProducer.sendEvent(this.jsonUtil.toJson(this.createPayload(orderSaved)));
		

		return orderSaved;
	}

	private Event createPayload(Order order) {
		
		var event = new Event();
		
		event.setOrderId(order.getId());
		event.setTransactionId(order.getTransactionId());
		event.setPayload(order);
		event.setCreatedAt(LocalDateTime.now());
		
		return this.eventService.save(event);

	}

}
