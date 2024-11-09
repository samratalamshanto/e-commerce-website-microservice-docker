package com.samratalam.notificationserivice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "notification_details")
public class Notification {
    @Id
    private String id;
    private String title;
    private String body;
    private String type;
    private String status;
    private LocalDateTime createdDT = LocalDateTime.now();
}
