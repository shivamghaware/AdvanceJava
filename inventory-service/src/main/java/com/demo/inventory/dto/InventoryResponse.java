package com.demo.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response DTO returned to the caller 
 * Tells the caller if the requested quantity is available.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryResponse {
    private String skuCode;
    private String productName;
    private boolean inStock;       // true if available quantity >= requested
    private Integer availableQty;  // actual stock level (informational)
}
