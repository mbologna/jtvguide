package it.unibg.cs.jtvguide.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {	
	private static SimpleDateFormat dateParser = null;
	
	public static Date formatString(String date){
		dateParser = new SimpleDateFormat("yyyyMMddHHmm ZZZZZ");
		if (date == null) return null;
		try {
			return dateParser.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	public static String formatDate2Time(Date date) {
		dateParser = new SimpleDateFormat("[HH:mm]");
		return dateParser.format(date);
	}
	
	public static String formatDate2TimeWithDay(Date date) {
		dateParser = new SimpleDateFormat("dd/MM [HH:mm]");
		return dateParser.format(date);
	}
	
	public static String formatDate(Date d) {
		dateParser = new SimpleDateFormat("yyyyMMdd");
		return dateParser.format(d);
	}
	
	public static Date convertDate(Date date) {
		dateParser = new SimpleDateFormat("yyyyMMddHHmmss ZZZZZ");
		try {
			return dateParser.parse(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
