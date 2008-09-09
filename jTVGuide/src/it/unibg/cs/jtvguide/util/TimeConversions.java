package it.unibg.cs.jtvguide.util;

import java.util.Calendar;

public class TimeConversions {
	public static void main(String args[]) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.set(2008, c1.get(Calendar.MONTH), 8, c1.get(Calendar.HOUR_OF_DAY),
				50, 00);
		System.out.println(c1.getTime());
		System.out.println(c2.getTime());
		long time = c2.getTimeInMillis() - c1.getTimeInMillis();
		System.out.println((int) (Math
				.round(((time % (1000 * 60 * 60.0)) / (1000 * 60)))));
		System.out.println((int) (Math
				.ceil(((time % (1000 * 60 * 60.0)) / (1000 * 60)))));
	}

	public static String millisecs2Time(long time) {
		int minutes = (int) (Math
				.ceil(((time % (1000 * 60 * 60.0)) / (1000 * 60))));
		int hours = (int) ((time / (1000 * 60 * 60)));
		// String secondsStr = (seconds<10 ? "0" : "")+seconds;
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + "h:" + minutesStr + "m");
	}
}
