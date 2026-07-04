package com.demo.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventory.entity.InventoryItem;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findBySkuCode(String skuCode);

    // Used when Order Service checks multiple SKUs at once
    List<InventoryItem> findBySkuCodeIn(List<String> skuCodes);
}
