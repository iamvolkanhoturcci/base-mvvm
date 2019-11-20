package com.volkanhotur.basemvvm.android.utils;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {

    public static final String dateFormat1 = "dd-MM-yyyy HH:mm:ss";

    private static DateHelper instance;
    private static ThreadLocal<SimpleDateFormat> utcIsoDateFormatter = new ThreadLocal<>();

    private DateHelper() { }

    public static DateHelper getInstance(){

        if(instance == null){
            synchronized (DateHelper.class) {
                instance = new DateHelper();
            }
        }

        return instance;
    }

    public String findDate(String dateFormat){
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

    public boolean isToday(Date date) {
        return DateUtils.isToday(date.getTime());
    }

    private SimpleDateFormat utcIsoDateFormatter() {
        if (utcIsoDateFormatter.get() == null) {
            TimeZone utcTimezone = TimeZone.getTimeZone("GMT");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            format.setTimeZone(utcTimezone);
            utcIsoDateFormatter.set(format);
        }
        return utcIsoDateFormatter.get();
    }

    private Date withObject(Object value) {
        if (value == null) return null;
        if (value instanceof Date) return (Date)value;

        else {
            try {
                return utcIsoDateFormatter().parse(value.toString());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private boolean isDateComponentEqualToDate(Date date1, Date date2) {
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

    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
}
