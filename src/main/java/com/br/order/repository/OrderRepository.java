package com.br.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.order.document.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
