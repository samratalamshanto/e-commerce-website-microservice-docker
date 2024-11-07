package com.samratalam.orderservice.controller;

import com.samratalam.orderservice.dto.CommonResponse;
import com.samratalam.orderservice.dto.OrderRequest;
import com.samratalam.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final Tracer tracer;

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
    //After call, if it reaches the timeout, throws TimeOutException. Slow Performance.
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<CommonResponse> addOrder(@RequestBody OrderRequest orderRequest) {
        log.info("TraceId={}, Order Request={}", Objects.requireNonNull(tracer.currentSpan()).context().traceId(), orderRequest);
        return CompletableFuture.supplyAsync(() -> orderService.addOrder(orderRequest));
    }

    public CompletableFuture<CommonResponse> fallBackInventoryService(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> new CommonResponse(500, false, "Error occurred while adding order. Please try again later.", null));
    }
}
