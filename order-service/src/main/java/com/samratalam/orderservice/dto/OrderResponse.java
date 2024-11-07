package com.samratalam.orderservice.dto;

import java.time.LocalDateTime;

public record OrderResponse(long id,
                            String transactionNumber,
                            String status,
                            String orderCode,
                            Double totalPrice,
                            Integer quantity,
                            LocalDateTime orderDateTime,
                            String remarks) {
}
