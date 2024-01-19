package com.br.order.document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection  = "order")
public class Order {

	@Id
	private String id;

	private List<OrderProducts> product;

	private LocalDateTime createdAt;

	private String transactionId;

	private double totalAmount;

	private int totalItens;

	public Order() {

	}

	public Order(String id, List<OrderProducts> product, LocalDateTime createdAt, String transactionId,
			double totalAmount, int totalItens) {
		super();
		this.id = id;
		this.product = product;
		this.createdAt = createdAt;
		this.transactionId = transactionId;
		this.totalAmount = totalAmount;
		this.totalItens = totalItens;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OrderProducts> getProduct() {
		return product;
	}

	public void setProduct(List<OrderProducts> product) {
		this.product = product;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(int totalItens) {
		this.totalItens = totalItens;
	}

}
