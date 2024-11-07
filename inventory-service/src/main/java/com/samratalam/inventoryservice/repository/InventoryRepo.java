package com.samratalam.inventoryservice.repository;

import com.samratalam.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductId(String productId);

    List<Inventory> findByProductCode(String productCode);


    Optional<Inventory> findByProductIdAndQuantityGreaterThanEqual(String productId, Integer quantity);

    List<Inventory> findAllByProductCodeIn(List<String> productCodes);
}
