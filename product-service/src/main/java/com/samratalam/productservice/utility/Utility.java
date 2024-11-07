package com.samratalam.productservice.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Utility {

    public static String getProductCode() {
        long currentTime = System.currentTimeMillis();
        return "P-" + currentTime;
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
