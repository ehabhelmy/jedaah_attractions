package com.spade.ja.util;

import android.text.format.Time;

import com.spade.ja.datalayer.pojo.response.eventinner.EventDay;
import com.spade.ja.datalayer.pojo.response.eventinner.TicketDate;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

    public static boolean isOpenNow(String day) {
        if (day.equalsIgnoreCase(getWeekDay())){
            return true;
        }else {
            return false;
        }
    }

    public static String getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String monthName = new SimpleDateFormat("MMM").format(calendar.getTime());
        return monthName.toUpperCase();
    }

    public static String getDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String day = new SimpleDateFormat("dd").format(calendar.getTime());
        return day;
    }

    public static String getWeekDay(){
        String date = new SimpleDateFormat("EEEE").format(new Date());
        return date;
    }


    public static String getDaysRemaining(Date date){
        Date date1 = new Date();
        long diff = date.getTime() - date1.getTime();
        int day = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        NumberFormat numberFormat = NumberFormat.getInstance();
        String daysRemaining = numberFormat.format(day);
        if (daysRemaining.equals("0")){
            return "";
        }
        if (Locale.getDefault().getLanguage().equals("ar")){
            return  daysRemaining+ "+ يوم";
        }else{
            return daysRemaining + "+ DAYS";
        }
    }

    public static String getEventDateTitle(String startDate,String endDate) {
        Date sDate = convertJSONDateToDate(startDate);
        Date eDate = convertJSONDateToDate(endDate);
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        Calendar eCalendar = Calendar.getInstance();
        eCalendar.setTime(eDate);
        String sDay = new SimpleDateFormat("dd").format(sCalendar.getTime());
        String sMonth = new SimpleDateFormat("MMMM").format(sCalendar.getTime());
        String eMonth = new SimpleDateFormat("MMMM").format(eCalendar.getTime());
        String eDay = new SimpleDateFormat("dd").format(eCalendar.getTime());
        return sMonth + " " + sDay + " - " + eMonth + " " + eDay;
    }

    public static String getEventDays(EventDay eventDay) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = format1.parse(eventDay.getStartDate() + " " + eventDay.getStartTime());
            eDate = format1.parse(eventDay.getStartDate() + " " + eventDay.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sDate != null && eDate !=null) {
            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sDate);
            Calendar eCalendar = Calendar.getInstance();
            eCalendar.setTime(eDate);
            String date = new SimpleDateFormat("EEEE, MMMM dd").format(sCalendar.getTime());
            String startTime = new SimpleDateFormat("hh:mm aaa").format(sCalendar.getTime());
            String endTime = new SimpleDateFormat("hh:mm aaa").format(eCalendar.getTime());
            return date + " at " + startTime + " - " + endTime;
        }else {
            return "";
        }
    }

    public static String getEventDateOrder(TicketDate ticketDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date sDate = null;
        try {
            sDate = format1.parse(ticketDate.getDate() + " " + ticketDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sDate != null) {
            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sDate);
            String date = new SimpleDateFormat("EEE").format(sCalendar.getTime());
            String startTime = new SimpleDateFormat("hh:mm aaa").format(sCalendar.getTime());
            return date + " " + startTime;
        }else {
            return "";
        }
    }

    public static String getEventDateHistory(com.spade.ja.datalayer.pojo.response.history.upcoming.TicketDate ticketDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date sDate = null;
        try {
            sDate = format1.parse(ticketDate.getDate() + " " + ticketDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sDate != null) {
            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sDate);
            String date = new SimpleDateFormat("EEE").format(sCalendar.getTime());
            String startTime = new SimpleDateFormat("hh:mm aaa").format(sCalendar.getTime());
            return date + " " + startTime;
        }else {
            return "";
        }
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