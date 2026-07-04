package com.demo.order.service;

import java.util.List;

import com.demo.order.dto.OrderRequest;
import com.demo.order.dto.OrderResponse;
import com.demo.order.entity.Order;

public interface OrderService {
	Order placeOrder(OrderRequest request);

	List<OrderResponse> getAllOrders();

	OrderResponse getOrderById(Long id);
}
