package com.samratalam.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    long id;
    String transactionNumber;
    String status;
    String orderCode;
    Double totalPrice;
    Integer quantity;
    LocalDateTime orderDateTime;
    String remarks;
}
