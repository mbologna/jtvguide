package it.unibg.cs.jtvguide.util;

import it.unibg.cs.jtvguide.log.PublicLogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class to format dates
 * @author Michele Bologna, Sebastiano Rota
 *
 */

public final class DateFormatter {
	private DateFormatter() { }
	private static SimpleDateFormat dateParser = null;

	/**
	 * Format date in yyyyMMdd format
	 * @param d the date to be formatted
	 * @return date in yyyyMMdd format
	 */
	public static String formatDate(Date d) {
		dateParser = new SimpleDateFormat("yyyyMMdd");
		return dateParser.format(d);
	}

	/**
	 * Format time in HH:mm format
	 * @param date the date to be formatted
	 * @return time in HH:mm format
	 */
	public static String formatDate2Time(Date date) {
		dateParser = new SimpleDateFormat("[HH:mm]");
		return dateParser.format(date);
	}

	/**
	 * Format date in yyyyMMdd HH:mm format
	 * @param date the date to be formatted
	 * @return date in yyyyMMdd HH:mm format
	 */
	public static String formatDate2TimeWithDay(Date date) {
		dateParser = new SimpleDateFormat("dd/MM [HH:mm]");
		return dateParser.format(date);
	}

	/**
	 * Format a string matching a date into a Date object
	 * @param date string
	 * @return a new Date matching the string's date
	 */
	public static Date formatString(String date) {
		dateParser = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return dateParser.parse(date);
		} catch (ParseException e) {
			PublicLogger.getLogger().error(e);
			return null;
		}
	}
}
