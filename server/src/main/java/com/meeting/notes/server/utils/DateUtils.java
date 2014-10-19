package com.meeting.notes.server.utils;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateUtils() {
    }

    public static String getFormattedDateFromTimestamp(Timestamp date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            return simpleDateFormat.format(new Date(date.getTime()));
        } catch (Exception ex) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to convert timestamp to string date: " + date + " * " + ex.getMessage());
            }
            return "";
        }
    }

    public static String getFormattedDateFromDate(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            return simpleDateFormat.format(date);
        } catch (Exception ex) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to convert date to string date: " + date + " * " + ex.getMessage());
            }
            return "";
        }
    }

    public static Timestamp getDateFromString(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date newDate = simpleDateFormat.parse(date);
            return new Timestamp(newDate.getTime());
        } catch (Exception ex) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to convert date string to timestamp: " + date + " * " + ex.getMessage());
            }
            return null;
        }
    }

    public static Timestamp getCurrentDateTime() {
        Date newDate = new Date();
        return new Timestamp(newDate.getTime());
    }

    public static String getFormattedTime(Long timeMillisecond) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%02d", timeMillisecond / (60 * 60 * 1000)));
            builder.append(":");
            builder.append(String.format("%02d", timeMillisecond / (60 * 1000) % 60));
            builder.append(":");
            builder.append(String.format("%02d", timeMillisecond / 1000 % 60));
            return builder.toString();
        } catch (Exception ex) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to convert timestamp to string: " + timeMillisecond + " * " + ex.getMessage());
            }
            return "";
        }
    }

    public static String getDateFormatted(String date) {
        if(date==null || date.isEmpty()) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return simpleDateFormat.format(simpleDateFormat.parse(date));
        } catch (ParseException ex) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Parse exception date " + date + " * " + ex.getMessage());
            }
            return "";
        }
    }
}
