package it.unibg.cs.jtvguide.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {	
	private static SimpleDateFormat dateParser = null;
	
	public static Date formatString(String date){
		dateParser = new SimpleDateFormat("yyyyMMddHHmmss ZZZZZ");
		try {
			return dateParser.parse(date);
		} catch (ParseException e) {
			System.err.println("Couldn't format date " + date);
		}
		return null;
	}
	public static String formatDate(Date date) {
		dateParser = new SimpleDateFormat("[HH:mm]");
		return dateParser.format(date);
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