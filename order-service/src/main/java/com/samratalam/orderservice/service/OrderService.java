package com.samratalam.orderservice.service;

import com.samratalam.orderservice.config.feign_client.InventoryClient;
import com.samratalam.orderservice.dto.CommonResponse;
import com.samratalam.orderservice.dto.OrderRequest;
import com.samratalam.orderservice.dto.OrderResponse;
import com.samratalam.orderservice.entity.OrderEntity;
import com.samratalam.orderservice.repository.OrderRepository;
import com.samratalam.orderservice.utility.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public CommonResponse addOrder(OrderRequest orderRequest) {

        boolean isAvailable = inventoryClient.isAvailable(orderRequest.productId(), orderRequest.quantity());
        if (isAvailable) {
            OrderEntity order = OrderEntity.builder()
                    .status("Completed")
                    .orderCode(Utility.getOrderCode())
                    .quantity(orderRequest.quantity())
                    .totalPrice(orderRequest.totalPrice())
                    .transactionNumber(Utility.getTransactionNumber())
                    .orderDateTime(Utility.getCurDateTime())
                    .build();
            order = orderRepository.save(order);
            OrderResponse orderResponse = new OrderResponse(order.getId(), order.getTransactionNumber(), order.getStatus(),
                    order.getOrderCode(), order.getTotalPrice(), order.getQuantity(),
                    order.getOrderDateTime(), order.getRemarks());
            return new CommonResponse(200, true, "Successfully place the order.", orderResponse);
        } else {
            return new CommonResponse(200, true, "Out of stock.", null);
        }
    }

    public CommonResponse getOderDetailsByOderNumber(String transactionNumber) {
        Optional<OrderEntity> order = orderRepository.findByTransactionNumber(transactionNumber);
        CommonResponse commonResponse = new CommonResponse(200, true, "Get the data successfully", null);
        if (order.isPresent()) {
            OrderResponse orderResponse = new OrderResponse(order.get().getId(), order.get().getTransactionNumber(), order.get().getStatus(),
                    order.get().getOrderCode(), order.get().getTotalPrice(), order.get().getQuantity(),
                    order.get().getOrderDateTime(), order.get().getRemarks());
            commonResponse.setData(orderResponse);
        } else {
            commonResponse.setSuccess(false);
            commonResponse.setMessage("No Order found");
        }
        return commonResponse;
    }

    public CommonResponse getAllOrders() {
        List<OrderResponse> orderResponses = orderRepository.findAll().stream()
                .map(order -> new OrderResponse(order.getId(), order.getTransactionNumber(), order.getStatus(),
                        order.getOrderCode(), order.getTotalPrice(), order.getQuantity(),
                        order.getOrderDateTime(), order.getRemarks()))
                .toList();
        return new CommonResponse(200, true, "Get all data successfully", orderResponses);
    }
}
