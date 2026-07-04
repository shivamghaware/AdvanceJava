package com.demo.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order.dto.OrderRequest;
import com.demo.order.entity.Order;
import com.demo.order.entity.OrderStatus;
import com.demo.order.service.OrderServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    /**
     * POST /orders
     *
     
     *
     * Internally will calls Inventory Service  to verify stock
     * before persisting the order.
     */
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody @Valid OrderRequest request) {
        Order order = orderService.placeOrder(request);
        // 201 if confirmed, 200 if out-of-stock (so client can see the reason)
        HttpStatus status = order.getStatus() == OrderStatus.CONFIRMED
            ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(order);
    }

    /**
     * GET /orders — list all orders
     * 
     */
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * GET /orders/{id}
     * 
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
