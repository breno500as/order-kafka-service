package com.br.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.order.config.exception.ValidationException;
import com.br.order.document.Event;
import com.br.order.dto.EventFilters;
import com.br.order.repository.EventRepository;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class EventService {

	private Logger logger = LoggerFactory.getLogger(EventService.class);

	@Autowired
	private EventRepository eventRepository;

	public Event save(Event event) {
		return this.eventRepository.save(event);
	}

	public void notifyEnding(Event event) {
		event.setOrderId(event.getOrderId());
		event.setCreatedAt(LocalDateTime.now());
		this.save(event);
		this.logger.info("Order {} with saga notified! Transaction id: {}", event.getOrderId(),
				event.getTransactionId());

	}

	private void validateEmptyFilters(EventFilters filters) {
		if (isEmpty(filters.getOrderId()) && isEmpty(filters.getTransactionId())) {
			throw new ValidationException("OrderID or TransactionID must be informed.");
		}
	}

	public List<Event> findAll() {
		return eventRepository.findAllByOrderByCreatedAtDesc();
	}

	public Event findByFilters(EventFilters filters) {
		validateEmptyFilters(filters);
		if (!isEmpty(filters.getOrderId())) {
			return findByOrderId(filters.getOrderId());
		} else {
			return findByTransactionId(filters.getTransactionId());
		}
	}

	private Event findByTransactionId(String transactionId) {
		return this.eventRepository.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId)
				.orElseThrow(() -> new ValidationException("Event not found by transactionId."));
	}

	private Event findByOrderId(String orderId) {
		return this.eventRepository.findTop1ByOrderIdOrderByCreatedAtDesc(orderId)
				.orElseThrow(() -> new ValidationException("Event not found by orderID."));
	}
}
