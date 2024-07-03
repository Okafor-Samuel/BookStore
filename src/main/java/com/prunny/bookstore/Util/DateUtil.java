package com.prunny.bookstore.Util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static LocalDate getLocalDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

        return LocalDate.parse(date, formatter);


    }
    public static Date getUtilDate(String date, String pattern) {
        Date today = new Date();
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return today;
    }
    public static void main(String[] args) {

    }
}
