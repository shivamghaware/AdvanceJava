package com.demo.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.service.InventoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    /**
     * GET /inventory/check?skuCode=SKU-001&quantity=2
     *
     * 
     * Returns stock availability for a single SKU.
     */
    @GetMapping("/check")
    public ResponseEntity<?> checkStock(
            @RequestParam String skuCode,
            @RequestParam(defaultValue = "1") int quantity) {

        return ResponseEntity.ok(inventoryService.checkStock(skuCode, quantity));
    }

    /**
     * POST /inventory/check-bulk
     * Body: ["SKU-001", "SKU-002", "SKU-003"]
     *
     * Bulk check — more efficient for multi-item orders.
     */
    @PostMapping("/check-bulk")
    public ResponseEntity<?> checkStockBulk(
            @RequestBody List<String> skuCodes) {

        return ResponseEntity.ok(inventoryService.checkStockBulk(skuCodes));
    }

    /**
     * PATCH /inventory/deduct?skuCode=SKU-001&quantity=2
     *
     * Reduce stock after order is confirmed.
     * Called by Order Service after a successful order placement.
     */
    @PatchMapping("/deduct")
    public ResponseEntity<String> deductStock(
            @RequestParam String skuCode,
            @RequestParam int quantity) {

        inventoryService.deductStock(skuCode, quantity);
        return ResponseEntity.ok("Stock updated for SKU: " + skuCode);
    }
}
