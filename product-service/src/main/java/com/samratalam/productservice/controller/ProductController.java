package com.samratalam.productservice.controller;

import com.samratalam.productservice.dto.CommonResponse;
import com.samratalam.productservice.dto.ProductRequest;
import com.samratalam.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public CommonResponse addProduct(@RequestBody List<ProductRequest> productReqList) {
        return productService.addProduct(productReqList);
    }

    @GetMapping("/get-all/products")
    public CommonResponse getAllProducts() {
        return productService.getAllProducts();
    }
}
