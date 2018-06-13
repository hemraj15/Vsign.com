package com.vsign.tech.rest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;

public class DateTimeUtils {

	private static final int	MILLISECONDS_IN_DAY	= 1000 * 60 * 60 * 24;

	public static int daysBetween(Date begin, Date end) {
		Calendar start = DateUtils.toCalendar(begin);
		start.set(Calendar.MILLISECOND, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.HOUR_OF_DAY, 0);
		Calendar finish = DateUtils.toCalendar(end);
		finish.set(Calendar.MILLISECOND, 999);
		finish.set(Calendar.SECOND, 59);
		finish.set(Calendar.MINUTE, 59);
		finish.set(Calendar.HOUR_OF_DAY, 23);
		return (int) Math.ceil((double) (finish.getTimeInMillis() - start.getTimeInMillis())
				/ MILLISECONDS_IN_DAY);
	}

	public static Date getNextDate(Date from, int noOfDay) {
		return DateUtils.addDays(from, noOfDay);
	}

	public static Boolean isCurrentDate(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		Calendar date1 = DateUtils.toCalendar(date);

		if ((calendar.get(Calendar.DAY_OF_MONTH) == date1.get(Calendar.DAY_OF_MONTH))
				&& (calendar.get(Calendar.MONTH) == date1.get(Calendar.MONTH))
				&& (calendar.get(Calendar.YEAR) == date1.get(Calendar.YEAR))) {
			return true;
		}
		return false;
	}

	/*
	 * Logic to check if given date is a Weekend
	 */
	public static Boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2013, 8, 5);

		// System.out.println(new Date());
		// System.out.println(calendar.getTime());

		// System.out.println(isCurrentDate(new Date()));

		Date startDate = getNextDate(calendar.getTime(), 0);

		System.out.println(isWeekend(startDate));

		System.out.println(daysBetween(calendar.getTime(), new Date()));
		System.out.println(daysBetween(new Date(), new Date()));
		// System.out.println(getNextDate(new Date(), -1));
		
		System.out.println(" testing dates");
		System.out.println("");
		
		//System.out.println("date :"+getDateFromString(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", true));

	}

	public static Date getDateFromString(String dateStr, String format, boolean trimZeros)
			throws ParseException {
		if (trimZeros) {
			if (dateStr != null && dateStr.trim().endsWith("000")) {
				dateStr = dateStr.substring(0, dateStr.lastIndexOf("."));
			}
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = dateStr != null ? simpleDateFormat.parse(dateStr) : null;
		return date;
	}

	public static String getStringFromDate(Date date, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}
	
}
