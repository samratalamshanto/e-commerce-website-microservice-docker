package com.samratalam.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "inventory_details")
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
