package com.project.broccoli.pot.util;

import java.text.SimpleDateFormat;

public class Utils {

    private static final String DATE_FORMAT = "yyyyMMddhhmmss";

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    /**
     * return pram이 null , "null" , "" 이면 "" 리턴, 아니면 param 을 그대로 return
     */
    public static String NUllCheck(final String str) {
        String value = "";
        if (str != null && !"null".equals(str) && str.length() > 0) {
            return value =str;
        }
        return value;
    }

}
