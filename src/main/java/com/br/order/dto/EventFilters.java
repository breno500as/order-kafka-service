package com.br.order.dto;

public class EventFilters {

	private String orderId;

	private String transactionId;

	public EventFilters() {

	}

	public EventFilters(String orderId, String transactionId) {
		this.orderId = orderId;
		this.transactionId = transactionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
