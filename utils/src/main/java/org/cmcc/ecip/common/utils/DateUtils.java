package org.cmcc.ecip.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
	
	final static  String DATE_FORMAT="yyyyMMdd";
	final static  String DATE_TIME_FORMAT="yyyyMMddHHmmssSSS";
	
	final static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	final static SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
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
}
