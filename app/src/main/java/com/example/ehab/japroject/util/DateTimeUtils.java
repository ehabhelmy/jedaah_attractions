package com.example.ehab.japroject.util;

import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ehab on 12/1/17.
 */

public class DateTimeUtils {

    public static final long MILLISECS_PER_DAY = TimeUnit.DAYS.toMillis(1);
    private static final SimpleDateFormat JaFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date convertJSONDateToDate(String jsonDate) {
        try {
            return JaFormat.parse(jsonDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String monthName = new SimpleDateFormat("MMMM").format(calendar.getTime());
        return monthName;
    }

    public static String getDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String day = new SimpleDateFormat("dd").format(calendar.getTime());
        return day;
    }

    public static String getDaysRemaining(Date date){
        Date date1 = new Date();
        long diff = date.getTime() - date1.getTime();
        int day = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return day+"";
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day) {
        Date aDate = day;
        Calendar cal = Calendar.getInstance();
        if (aDate == null) aDate = new Date();
        cal.setTime(aDate);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfDay(Date day) {
        Date aDate = day;
        Calendar cal = Calendar.getInstance();
        if (aDate == null) aDate = new Date();
        cal.setTime(aDate);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }


    public static TimeOfDay calculateTimeOfDay() {
        Time currentTime = new Time();
        currentTime.setToNow();

        int hour = currentTime.hour;

        if (hour >= 2 && hour < 12) {
            return TimeOfDay.MORNING;
        } else if (hour >= 12 && hour < 16) {
            return TimeOfDay.AFTERNOON;
        } else {
            return TimeOfDay.EVENING;
        }
    }

    public enum TimeOfDay {
        MORNING,
        AFTERNOON,
        EVENING
    }

    public static long getDateInMilliSeconds(String dateString) {
        long timeInMilliseconds = 0;
        if (dateString != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date mDate = sdf.parse(dateString);
                timeInMilliseconds = mDate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return timeInMilliseconds;
    }

    public static String getFormattedDate(String dateString) {
        String formattedDate;
        try {
            Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            formattedDate = new SimpleDateFormat("d MMM yyyy").format(parsedDate);

        } catch (Exception e) {
            e.printStackTrace();
            formattedDate = dateString;
        }

        return formattedDate;
    }

    private static Calendar getCalenderForDay(int daysBefor) {
        Calendar now = Calendar.getInstance();

        Calendar dayBefor = (Calendar) now.clone();
        dayBefor.add(Calendar.DAY_OF_YEAR, -daysBefor);

        return dayBefor;
    }

}
