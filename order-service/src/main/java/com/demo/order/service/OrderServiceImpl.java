package com.demo.order.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.order.dto.OrderRequest;
import com.demo.order.dto.OrderResponse;
import com.demo.order.entity.Order;
import com.demo.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final ModelMapper mapper;

	public Order placeOrder(OrderRequest request) {
		String orderNum = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		log.info("Processing order {} for SKU: {}", orderNum, request.getSkuCode());

		/*
		 * Check stock by making a call to Inventory Service 
		 * If available - Create new
		 * Order - with status - Confirmed & then deduct the stock 
		 * If un available -
		 * Create new Order - with status -Out Of Stock & add a warning - Order Rejected
		 */

		return null;
	}

	public List<OrderResponse> getAllOrders() {
		return orderRepository.findAll().stream().map(order -> mapper.map(order, OrderResponse.class)).toList();
	}

	public OrderResponse getOrderById(Long id) {

		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found: " + id));
		return mapper.map(order, OrderResponse.class);
	}
}
