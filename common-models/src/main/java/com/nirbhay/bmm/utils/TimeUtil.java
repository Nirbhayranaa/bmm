package com.nirbhay.bmm.utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Nirbhay Rana
 */
public class TimeUtil {

    public static LocalTime convertToLocalTime(Time time) {
        if (time == null) {
            return null;
        }
        return time.toLocalTime();
    }

    public static LocalDate convertToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }
}
