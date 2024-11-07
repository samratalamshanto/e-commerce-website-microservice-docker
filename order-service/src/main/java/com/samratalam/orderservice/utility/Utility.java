package com.samratalam.orderservice.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class Utility {
    public static String getTransactionNumber() {
        return "O-" + UUID.randomUUID().toString();
    }

    public static String getOrderCode() {
        return "OC-" + System.currentTimeMillis();
    }

    public static LocalDate getCurDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getCurDateTime() {
        return LocalDateTime.now();
    }
}
