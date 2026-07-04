package com.demo.order.dto;

import lombok.Data;


@Data
public class InventoryResponse {
    private String skuCode;
    private String productName;
    private boolean inStock;
    private Integer availableQty;
}
