package com.demo.order.dto;

import com.demo.order.entity.OrderStatus;

import lombok.Data;

/*
 * Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
 */
@Data 
public class OrderResponse {
	private String orderNumber; // Human-readable reference like "ORD-1234"
	private String skuCode;
	private String productName;
	private String customerName;
	private int quantity;
	private double totalPrice;
	private OrderStatus status;
}
