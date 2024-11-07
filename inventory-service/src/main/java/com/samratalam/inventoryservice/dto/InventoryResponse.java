package com.samratalam.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String productName;
    private String productType;
    private String productCode;
    private String productId;
    private String productDescription;
    private int quantity;
    private int price;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
