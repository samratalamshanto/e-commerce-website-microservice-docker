package com.samratalam.productservice.model;

import com.samratalam.productservice.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String id;
    private String productType;
    private String productCode;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime createdDT = Utility.getCurrentDateTime();
}
