package com.br.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.order.document.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

	List<Event> findAllByOrderByCreatedAtDesc();

	Optional<Event> findTop1ByOrderIdOrderByCreatedAtDesc(String orderId);

	Optional<Event> findTop1ByTransactionIdOrderByCreatedAtDesc(String transactionId);

}
