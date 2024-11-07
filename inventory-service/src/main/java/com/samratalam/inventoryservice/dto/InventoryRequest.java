package com.samratalam.inventoryservice.dto;

public record InventoryRequest(String productName,
                               String productType,
                               String productCode,
                               String productId,
                               String productDescription,
                               int quantity,
                               int price) {
}
