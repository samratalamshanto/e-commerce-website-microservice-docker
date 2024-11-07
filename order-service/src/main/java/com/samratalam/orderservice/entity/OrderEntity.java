package com.samratalam.orderservice.entity;

import com.samratalam.orderservice.utility.Utility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_details")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String transactionNumber;
    private String status;
    private String orderCode;
    private Double totalPrice;
    private Integer quantity;
    private LocalDateTime orderDateTime = Utility.getCurDateTime();
    private String remarks;
}
