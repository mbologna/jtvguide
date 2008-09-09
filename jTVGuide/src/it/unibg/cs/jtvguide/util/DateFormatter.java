package it.unibg.cs.jtvguide.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	private static SimpleDateFormat dateParser = null;

	public static String formatDate(Date d) {
		dateParser = new SimpleDateFormat("yyyyMMdd");
		return dateParser.format(d);
	}

	public static String formatDate2Time(Date date) {
		dateParser = new SimpleDateFormat("[HH:mm]");
		return dateParser.format(date);
	}

	public static String formatDate2TimeWithDay(Date date) {
		dateParser = new SimpleDateFormat("dd/MM [HH:mm]");
		return dateParser.format(date);
	}

	public static Date formatString(String date) {
		dateParser = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return dateParser.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
