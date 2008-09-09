package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.util.DateFormatter;
import it.unibg.cs.jtvguide.util.TimeConversions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Program implements Comparable<Program> {

	private String title;
	private Date startDate;
	private Date stopDate;
	private Channel mChannel;

	public Program(Date startDate, Date stopDate, Channel c, String title) {
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.mChannel = c;
		this.title = title;
	}

	@Override
	public int compareTo(Program o) {
		Date startDateComparing = o.getStartDate();
		return startDate.compareTo(startDateComparing);
	}

	public Channel getChannel() {
		return mChannel;
	}

	public int getCompletionPercentile() {
		Calendar start = new GregorianCalendar();
		Calendar stop = new GregorianCalendar();
		Calendar now = Calendar.getInstance();
		stop.setTime(stopDate);
		start.setTime(startDate);
		if (stop.getTimeInMillis() == start.getTimeInMillis())
			return 0;
		else
			return (int) Math.ceil(((now.getTimeInMillis() - start
					.getTimeInMillis()) * 100 / (stop.getTimeInMillis() - start
					.getTimeInMillis())));

	}

	public String getInfo() {
		switch (getState()) {
		case ONAIR:
			return ("On Air: (" + getCompletionPercentile() + "%)");
		case UPCOMING:
			return ("Starting in " + TimeConversions
					.millisecs2Time(getStartingTime()));
		case FINISHED:
			return ("Finished");
		case UNKNOWN:
			return ("Unknown");
		}
		return null;
	}

	public Date getStartDate() {
		return startDate;
	}

	public long getStartingTime() {
		Calendar start = new GregorianCalendar();
		start.setTime(startDate);
		Calendar now = Calendar.getInstance();
		return start.getTimeInMillis() - now.getTimeInMillis();

	}

	public ProgramState getState() {
		if (stopDate == null)
			return ProgramState.UNKNOWN;
		if (getStartingTime() > 0)
			return ProgramState.UPCOMING;
		if (getCompletionPercentile() >= 0 && getCompletionPercentile() <= 100)
			return ProgramState.ONAIR;
		else
			return ProgramState.FINISHED;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public String getTitle() {
		return title;
	}

	public void setChannel(Channel val) {
		this.mChannel = val;
	}

	public void setStartDate(Date val) {
		this.startDate = val;
	}

	public void setStopDate(Date val) {
		this.stopDate = val;
	}

	public void setTitle(String val) {
		this.title = val;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormatter.formatDate2TimeWithDay(startDate) + "-");
		if (stopDate != null)
			sb.append(DateFormatter.formatDate2Time(stopDate));
		sb.append("   " + title + "   (");
		sb.append(mChannel + ")");
		return sb.toString();
	}
}
