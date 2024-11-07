package com.samratalam.inventoryservice.service;

import com.samratalam.inventoryservice.dto.CommonResponse;
import com.samratalam.inventoryservice.dto.InventoryRequest;
import com.samratalam.inventoryservice.dto.InventoryResponse;
import com.samratalam.inventoryservice.entity.Inventory;
import com.samratalam.inventoryservice.repository.InventoryRepo;
import com.samratalam.inventoryservice.utility.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    ModelMapper mapper = new ModelMapper();

    public boolean isProductAvailable(String productId, int quantity) {
        return inventoryRepo.findByProductIdAndQuantityGreaterThanEqual(productId, quantity).isPresent();
    }

    public CommonResponse addInventory(List<InventoryRequest> inventoryRequestList) {
        List<Inventory> list = new ArrayList<>();
        for (InventoryRequest inventoryRequest : inventoryRequestList) {
            Inventory inventory = Inventory.builder().price(inventoryRequest.price())
                    .quantity(inventoryRequest.quantity())
                    .productCode(inventoryRequest.productCode())
                    .productDescription(inventoryRequest.productDescription())
                    .productName(inventoryRequest.productName())
                    .productId(inventoryRequest.productId())
                    .productType(inventoryRequest.productType())
                    .createdDateTime(Utility.getLocalDateTime())
                    .updatedDateTime(Utility.getLocalDateTime())
                    .build();
            list.add(inventory);
        }
        list = inventoryRepo.saveAll(list);
        return new CommonResponse(200, true, "Successfully add all inventory data.", list);
    }

    public CommonResponse isAllProductAvailable(Map<String, Integer> map) {
        List<String> productCodes = new ArrayList<>(map.keySet());
        List<Inventory> inventoryList = inventoryRepo.findAllByProductCodeIn(productCodes);
        Map<String, Boolean> productAvailable = new HashMap<>();
        if (!inventoryList.isEmpty()) {
            for (Inventory inventory : inventoryList) {
                int reqQuantity = map.get(inventory.getProductCode());
                if (reqQuantity >= inventory.getQuantity()) {
                    productAvailable.put(inventory.getProductCode(), true);
                } else {
                    productAvailable.put(inventory.getProductCode(), false);
                }
            }
        }
        return new CommonResponse(200, true, "Successfully get all inventory available status.", productAvailable);
    }

    public CommonResponse getAllInventoryList() {
        List<InventoryResponse> list = new ArrayList<>();

        inventoryRepo.findAll().forEach(single -> {
            InventoryResponse response = new InventoryResponse();
            mapper.map(single, response);
            list.add(response);
        });

        return new CommonResponse(200, true, "Successfully get all inventory list.", list);
    }
}
