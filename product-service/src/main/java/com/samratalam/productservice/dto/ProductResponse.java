package com.samratalam.productservice.dto;

public record ProductResponse(String id,
                              String productType,
                              String productCode,
                              String name,
                              String description,
                              Double price) {
}
