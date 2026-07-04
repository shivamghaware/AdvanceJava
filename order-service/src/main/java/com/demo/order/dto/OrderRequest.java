package com.demo.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Request DTO for placing an order.
 * Client sends this JSON body to POST /orders.
 */
@Data
public class OrderRequest {

    @NotBlank(message = "SKU code is required")
    private String skuCode;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
   
}
