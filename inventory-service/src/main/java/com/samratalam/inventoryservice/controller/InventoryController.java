package com.samratalam.inventoryservice.controller;

import com.samratalam.inventoryservice.dto.CommonResponse;
import com.samratalam.inventoryservice.dto.InventoryRequest;
import com.samratalam.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/")
    public CommonResponse getAllInventory() {
        return inventoryService.getAllInventoryList();
    }


    @GetMapping("/is-available")
    public boolean isProductAvailable(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity) {
        log.info("ProductId={}, Quantity={}", productId, quantity);
        return inventoryService.isProductAvailable(productId, quantity);
    }

    @PostMapping("/is-all-available")
    public CommonResponse isProductAvailable(@RequestParam Map<String, Integer> map) {
        return inventoryService.isAllProductAvailable(map);
    }

    @PostMapping("/add-inventory")
    public CommonResponse addInventory(@RequestBody List<InventoryRequest> inventoryRequestList) {
        return inventoryService.addInventory(inventoryRequestList);
    }
}
