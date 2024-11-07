package com.samratalam.orderservice.controller;

import com.samratalam.orderservice.dto.CommonResponse;
import com.samratalam.orderservice.dto.OrderRequest;
import com.samratalam.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get-all-orders")
    public CommonResponse getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/get-order")
    public CommonResponse getOderDetailsByOderNumber(@RequestParam("transactionNumber") String transactionNumber) {
        return orderService.getOderDetailsByOderNumber(transactionNumber);
    }

    @PostMapping("/add-order")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackInventoryService")
    public CommonResponse addOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.addOrder(orderRequest);
    }

    public CommonResponse fallBackInventoryService(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException) {
        return new CommonResponse(500, false, "Error occurred while adding order. Please try again later.", null);
    }
}
