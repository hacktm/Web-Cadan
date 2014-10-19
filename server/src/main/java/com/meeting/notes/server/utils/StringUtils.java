package com.meeting.notes.server.utils;

public class StringUtils {
    public static String extractUsernameFromEmail(String email) {
        if (null != email && !email.isEmpty()) {
            String[] values = email.split("@");
            if (values.length>0) {
                return values[0];
            }
        }
        return "";
    }
}
