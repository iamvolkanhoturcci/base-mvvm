package com.volkanhotur.basemvvm.android.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author volkanhotur
 */

public final class DateUtils {

    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private DateUtils() { }

    public static String findDate(String dateFormat){
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
            String today;
            Calendar calendar = new GregorianCalendar(Locale.US);
            today = sdf.format(calendar.getTime());

            return today;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isToday(Date date) {
        return android.text.format.DateUtils.isToday(date.getTime());
    }

    private static boolean isDateComponentEqualToDate(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        return year1 == year2 && month1 == month2 && day1 == day2;
    }
}
