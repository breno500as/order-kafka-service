package com.br.order.dto;

import java.util.List;

import com.br.order.document.OrderProducts;

public class OrderRequest {

	private List<OrderProducts> products;

	public OrderRequest() {

	}

	public OrderRequest(List<OrderProducts> products) {
		this.products = products;
	}

	public List<OrderProducts> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProducts> products) {
		this.products = products;
	}

}
