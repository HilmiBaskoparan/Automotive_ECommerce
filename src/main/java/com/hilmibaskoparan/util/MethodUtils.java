package com.hilmibaskoparan.util;

import java.sql.Timestamp;
import java.util.Date;

public class MethodUtils {

    public static Date getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
