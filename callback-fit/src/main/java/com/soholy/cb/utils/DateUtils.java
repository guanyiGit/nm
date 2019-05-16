package com.soholy.cb.utils;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }

        return null;
    }


    public static Date stringToDate(String date, String parttern) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ((parttern != null) && (parttern != "")) {
            ft = new SimpleDateFormat(parttern);
        }
        Date dt = null;
        try {
            dt = ft.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static void main(String[] Args) {
        Long timeBefore = getTimeBefore(stringToDate("2018-10-21 13:14:15", null));
        System.out.println(timeBefore);
    }


    public static Date DateToPatterDate(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            String stringDate = df.format(date);
            SimpleDateFormat st = new SimpleDateFormat(pattern);
            Date currentDate = null;
            try {
                currentDate = st.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return currentDate;
        }
        return null;
    }


    public static Long getTimeBefore(Date date) {
        Date now = new Date();

        long l = date.getTime() - now.getTime();
        long day = l / 86400000L;
        long hour = l / 3600000L - day * 24L;
        long min = l / 60000L - day * 24L * 60L - hour * 60L;
        long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        String r = "";
        if (day > 0L) {
            r = r + day;
        } else if (hour <= 0L) {
            if (min <= 0L) {
                if (s <= 0L) {
                }
            }
        }

        return Long.valueOf(day);
    }


    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / 86400000L;
        long hour = l / 3600000L - day * 24L;
        long min = l / 60000L - day * 24L * 60L - hour * 60L;
        long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        String r = "";
        if (day > 0L) {
            r = r + day + "天";
        }
        if (hour > 0L) {
            r = r + hour + "小时";
        }
        if (min > 0L) {
            r = r + min + "分";
        }
        if (s > 0L) {
            r = r + s + "秒";
        }
        r = r + "前";
        return r;
    }


    public static Date addMonth(Date newDate, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        calendar.add(2, month);
        Date time = calendar.getTime();
        return time;
    }
}


