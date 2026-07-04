package com.demo.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.inventory.dto.InventoryResponse;
import com.demo.inventory.entity.InventoryItem;
import com.demo.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl {

    private final InventoryRepository inventoryRepository;

    /**
     * Check if a single SKU is in stock with at least the requested quantity.
     * 
     */
    public InventoryResponse checkStock(String skuCode, int requestedQty) {
        log.info("Checking stock for SKU: {} | Requested: {}", skuCode, requestedQty);

        return inventoryRepository.findBySkuCode(skuCode)
            .map(item -> {
                boolean inStock = item.getQuantity() >= requestedQty;
                log.info("SKU {} | Available: {} | In Stock: {}", skuCode, item.getQuantity(), inStock);
                return new InventoryResponse(
                    item.getSkuCode(),
                    item.getProductName(),
                    inStock,
                    item.getQuantity()
                );
            })
            .orElse(new InventoryResponse(skuCode, "Unknown Product", false, 0));
    }

    /**
     * Bulk stock check — more efficient when an order has multiple line items.
     * Returns one InventoryResponse per SKU.
     */
    public List<InventoryResponse> checkStockBulk(List<String> skuCodes) {
        log.info("Bulk stock check for SKUs: {}", skuCodes);

        List<InventoryItem> items = inventoryRepository.findBySkuCodeIn(skuCodes);

        return items.stream()
            .map(item -> new InventoryResponse(
                item.getSkuCode(),
                item.getProductName(),
                item.getQuantity() > 0,
                item.getQuantity()
            ))
            .collect(Collectors.toList());
    }

    /**
     * Reduce stock when an order is confirmed.
     * In a real system this would be in a distributed transaction / saga.
     */
    public void deductStock(String skuCode, int qty) {
        InventoryItem item = inventoryRepository.findBySkuCode(skuCode)
            .orElseThrow(() -> new RuntimeException("SKU not found: " + skuCode));

        if (item.getQuantity() < qty) {
            throw new RuntimeException("Insufficient stock for SKU: " + skuCode);
        }

        item.setQuantity(item.getQuantity() - qty);
        inventoryRepository.save(item);
        log.info("Deducted {} units from SKU {}. Remaining: {}", qty, skuCode, item.getQuantity());
    }
}
