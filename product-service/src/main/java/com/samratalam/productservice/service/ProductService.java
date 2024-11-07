package com.samratalam.productservice.service;

import com.samratalam.productservice.dto.CommonResponse;
import com.samratalam.productservice.dto.ProductRequest;
import com.samratalam.productservice.dto.ProductResponse;
import com.samratalam.productservice.model.Product;
import com.samratalam.productservice.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.samratalam.productservice.utility.Utility.getProductCode;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public CommonResponse addProduct(List<ProductRequest> productReqList) {
        List<Product> productList = new ArrayList<>();

        for (ProductRequest productReq : productReqList) {
            Product product = Product.builder()
                    .name(productReq.name())
                    .productType(productReq.productType())
                    .price(productReq.price())
                    .productCode(getProductCode())
                    .description(productReq.description())
                    .price(productReq.price())
                    .build();

            productList.add(product);
        }
        productList = productRepo.saveAll(productList);

        List<ProductResponse> productRepList = productList.stream()
                .map(product -> new ProductResponse(product.getId(), product.getProductType(), product.getProductCode(), product.getName(),
                        product.getDescription(), product.getPrice())
                ).toList();
        return new CommonResponse(200, true, "Successfully save the product.", productRepList);
    }

    public CommonResponse getAllProducts() {
        List<ProductResponse> productList = productRepo.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getProductType(), product.getProductCode(), product.getName(),
                        product.getDescription(), product.getPrice())
                ).toList();
        return new CommonResponse(200, true, "Successfully get all products.", productList);
    }
}
