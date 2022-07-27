package com.hibernate.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/* The data formatter
	 *  -dd: day in month(number)
	 *  -MM: month in year(number)
	 *  -yyyy: year
	 */
	
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	//read a date string and parse into date
	public static Date parseDate(String dateStr) throws ParseException{
		Date theDate  = formatter.parse(dateStr);
		return theDate;
	}
	
	//read a date and convert into string
	public static String formatDate(Date theDate) {
		String date = formatter.format(theDate);
		return date;
	}
	
}
