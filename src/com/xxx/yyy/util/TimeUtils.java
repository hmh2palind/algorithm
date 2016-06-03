package com.xxx.yyy.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class TimeUtils {
	private static long fixedCurrentTimeMillis = Long.MIN_VALUE;
	
	private TimeUtils() {
		
	}
	
    /**
     * Set current time to a fixed value
     * @param t Fixed current time in milliseconds
     */
    public static void setFixedCurrentTime(long t) {
        fixedCurrentTimeMillis = t;
    }
    
    /**
     * Set current time to a fixed value
     * @param year
     * @param month (1-12)
     * @param day   (1-31)
     * @param hour  (0-23)
     * @param minute (0-59)
     */
    public static void setFixedCurrentTime(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, day, hour, minute);
        fixedCurrentTimeMillis = cal.getTimeInMillis();
    }
    
    /**
     * Set current time to a fixed value
     * @param year
     * @param month (1-12)
     * @param day   (1-31)
     * @param hour  (0-23)
     * @param minute (0-59)
     * @param second (0-59)
     */
    public static void setFixedCurrentTime(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, day, hour, minute, second);
        fixedCurrentTimeMillis = cal.getTimeInMillis();
    }
    
    /**
     * Get internal fixed current time in milliseconds
     * @return
     */
    public static long getFixedCurrentTimeMillis() {
        return fixedCurrentTimeMillis;
    }
    
    /**
     * Get current time in milliseconds
     * @return
     */
    public static long getCurrentTimeMillis() {
        if (fixedCurrentTimeMillis != Long.MIN_VALUE) {
            return fixedCurrentTimeMillis;
        }
        return System.currentTimeMillis();
    }
    
    /**
     * Get current time as Date object
     * @return
     */
    public static Date getCurrentTimeDate() {
        return new Date(getCurrentTimeMillis());
    }
    
    /**
     * Get current time as Calendar object
     * @return
     */
    public static Calendar getCurrentTimeCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getCurrentTimeMillis());
        return cal;
    }
    
    public static boolean isBetween(Date date, Date startDate, Date endDate) {
        return date.after(startDate) && date.before(endDate);
    }
    
	
	public static String getDateFormat(){
		Calendar calendar = Calendar.getInstance();
		String format = new SimpleDateFormat("-yyyyMMdd_HHmmss_").format(calendar.getTime());
		return format;
	}
}
