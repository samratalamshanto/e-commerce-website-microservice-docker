package com.samratalam.notificationserivice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.samratalam.notificationserivice.dto.OrderResponse;
import com.samratalam.notificationserivice.entity.Notification;
import com.samratalam.notificationserivice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    @Value("${spring.kafka.template.notification-topic}")
    private String notificationTopic;

    @KafkaListener(topics = "notificationTopic", groupId = "orderGroup")
    public void listen(OrderResponse orderResponse) {
        try {
            log.info("Received order response: {}", orderResponse);
            Notification notification = new Notification();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(orderResponse);
            notification.setBody(json);
            notification.setStatus("Success");
            notification.setType("OrderSuccessNotification");
            notification.setType("OrderResponseNotification");

            notificationRepository.save(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
