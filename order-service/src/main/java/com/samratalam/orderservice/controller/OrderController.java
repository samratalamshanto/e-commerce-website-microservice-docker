package com.samratalam.orderservice.controller;

import com.samratalam.orderservice.dto.CommonResponse;
import com.samratalam.orderservice.dto.OrderRequest;
import com.samratalam.orderservice.service.OrderService;
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
    public CommonResponse addOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.addOrder(orderRequest);
    }
}
