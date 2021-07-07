package org.cmcc.ecip.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils
{
	
	final static  String DATE_FORMAT="yyyyMMdd";
	final static  String DATE_TIME_FORMAT="yyyyMMddHHmmssSSS";
	
	final static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	final static SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
	public static final SimpleDateFormat secondDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * yyyyMMddHHmmss.SSS
	 */
	public static final SimpleDateFormat millisecondDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
	public static final SimpleDateFormat DBDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
	public static final SimpleDateFormat MysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static final String DAYS = "0";
	public static final String HOURS = "1";
	public static final String MINUTES = "2";
	public static final String SECONDS = "3";

	public static Date parse(SimpleDateFormat simpleDateFormat, String source) throws ParseException {
		synchronized (simpleDateFormat) {
			Date date = simpleDateFormat.parse(source);
			return date;
		}
	}

	public static String format(SimpleDateFormat simpleDateFormat, Date date) {
		synchronized (simpleDateFormat) {
			String string = simpleDateFormat.format(date);
			return string;
		}
	}

	public static String getSecondDateFormat(SimpleDateFormat simpleDateFormat) {
		synchronized (simpleDateFormat) {
			String string = simpleDateFormat.format(new Date());
			return string;
		}
	}

	public static String getCutoffday() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = Calendar.getInstance().getTime();
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (hour < 23) {
			return format(sdf, date);
		} else {
			return format(sdf, getNextDay(date, 1));
		}
	}

	public static String getSecondDate() {
		return format(secondDateFormat, Calendar.getInstance().getTime());
	}

	public static Date getNextDay(Date date, int nextDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, nextDay);
		date = calendar.getTime();
		return date;
	}

	public static TimeUnit toTimeUnit(String flag) {
		if (DAYS.equals(flag)) {
			return TimeUnit.DAYS;
		} else if (MINUTES.equals(flag)) {
			return TimeUnit.MINUTES;
		} else if (HOURS.equals(flag)) {
			return TimeUnit.HOURS;
		} else if (SECONDS.equals(flag)) {
			return TimeUnit.SECONDS;
		}
		return null;

	}
	
	public static String getLastDay(String date)throws Exception
	{
		return format(addDays(parseDate(date),-1));
	}
	public static Date parseDate(String dateValue)throws Exception
	{
		return dateFormat.parse(dateValue);
		
	}
	public static Date parseDateTime(String datetimeValue)throws Exception
	{
		return datetimeFormat.parse(datetimeValue);
		
	}
	public static String getCutrCutoffday()
	{
		Calendar cal = Calendar.getInstance();
	
		Date date=cal.getTime();
		
		String hour=DateUtils.format(date,"HH");
		System.out.println("hour:"+hour);
		if(Integer.parseInt(hour)<23)
		{
			return  DateUtils.format(date);
		}
		else
		{
			return DateUtils.format(DateUtils.addDays(date, 1));	
		}
	}
	public static String format(Date date,String ff)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(ff);
		return dateFormat.format(date);
	}
	
	public static String getNow()
	{
		return formatDateTime(Calendar.getInstance().getTime());
	}
	public static String format(Date date)
	{
		return dateFormat.format(date);
	}
	
	public static String formatDateTime(Date date)
	{
		return datetimeFormat.format(date);
	}
	
	public static Date addDays(Date date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, days);

		return cal.getTime();
	}
	public static long currentTimeSecound() {
		return  currentTimeMillis()/1000;
	}
	
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
}
