package it.unibg.cs.jtvguide.util;


/**
 * A class to calculate time conversions
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public final class TimeConversions {
	
	private TimeConversions() { }
	/**
	 * Convert millisec to hour, minutes
	 * @param time in millisec
	 * @return hours, minutes
	 */
	public static String millisecs2Time(long time) {
		int minutes = (int) (Math
				.ceil(((time % (1000 * 60 * 60.0)) / (1000 * 60))));
		int hours = (int) ((time / (1000 * 60 * 60)));
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + "h:" + minutesStr + "m");
	}
}
