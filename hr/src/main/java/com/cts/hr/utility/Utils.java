package com.cts.hr.utility;

public class Utils {

    public static String generateCorrelationId() {
        long timestamp = System.currentTimeMillis();
        int randomInt = (int) (Math.random() * 100000);
        return timestamp + "-" + randomInt;
    }
}
