package it.unibg.cs.jtvguide.util;

import java.util.Calendar;

public class TimeConversions {

	public static String millisecs2Time(long time) {
		int minutes = (int) (Math
				.ceil(((time % (1000 * 60 * 60.0)) / (1000 * 60))));
		int hours = (int) ((time / (1000 * 60 * 60)));
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + "h:" + minutesStr + "m");
	}
}
