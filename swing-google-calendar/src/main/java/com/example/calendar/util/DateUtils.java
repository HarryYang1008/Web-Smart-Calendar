package com.example.calendar.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类，统一解析和格式化逻辑。
 */
public class DateUtils {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalDate parseDate(String text) {
        return LocalDate.parse(text, DATE_FORMATTER);
    }

    public static LocalTime parseTime(String text) {
        return LocalTime.parse(text, TIME_FORMATTER);
    }
}
