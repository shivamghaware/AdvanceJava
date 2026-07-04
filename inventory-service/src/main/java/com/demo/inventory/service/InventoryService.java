package com.demo.inventory.service;

import java.util.List;

import com.demo.inventory.dto.InventoryResponse;

public interface InventoryService {
	InventoryResponse checkStock(String skuCode, int requestedQty);

	List<InventoryResponse> checkStockBulk(List<String> skuCodes);

	void deductStock(String skuCode, int qty);

}
