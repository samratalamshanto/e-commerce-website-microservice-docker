package com.samratalam.orderservice.dto;

public record OrderRequest(Double totalPrice, Integer quantity,
                           String productCode, String productName,
                           String productId) {
}
