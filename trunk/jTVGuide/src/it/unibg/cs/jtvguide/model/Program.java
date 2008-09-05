package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.util.DateFormatter;

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

	public Channel getChannel() {
		return mChannel;
	}

	public void setChannel(Channel val) {
		this.mChannel = val;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date val) {
		this.startDate = val;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date val) {
		this.stopDate = val;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String val) {
		this.title = val;
	}

	public int getCompletionPercentile() {
		Calendar start = new GregorianCalendar();
		Calendar stop = new GregorianCalendar();
		Calendar now = Calendar.getInstance();
		stop.setTime(stopDate);
		start.setTime(startDate);
		return (int) ((now.getTimeInMillis() - start.getTimeInMillis()) * 100 / (stop
				.getTimeInMillis() - start.getTimeInMillis()));
	}

	public long getStartingTime() {
		Calendar start = new GregorianCalendar();
		start.setTime(startDate);
		Calendar now = Calendar.getInstance();
		return start.getTimeInMillis() - now.getTimeInMillis();
	}
	
	public ProgramState getState() {
		if (getCompletionPercentile() >= 0 && getCompletionPercentile() <= 100)
			return ProgramState.ONAIR;
		else if (getStartingTime() >= 0) 
			return ProgramState.UPCOMING;
		else
			return ProgramState.FINISHED;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormatter.formatDate2TimeWithDay(startDate) + "-");
		sb.append(DateFormatter.formatDate2Time(stopDate));
		switch (getState()) {
		case ONAIR: sb.append("   (On Air: " + getCompletionPercentile() + "%)"); break; 
		case UPCOMING: sb.append("   (Starting in " + millisecs2Time(getStartingTime())+ ")"); break;
		case FINISHED: sb.append("   (Finished)"); break;
		}
		sb.append("   " + title + "   (");
		sb.append(mChannel.getDisplayName() + ")");
		return sb.toString();
	}

	public static String millisecs2Time(long time)
	{
		int minutes = (int) ((time % (1000*60*60)) / (1000*60));
		int hours = (int) (time/(1000*60*60));
		String minutesStr = (minutes<10 ? "0" : "")+minutes;
		String hoursStr = (hours<10 ? "0" : "")+hours;
		return new String(hoursStr+"h:"+minutesStr+"m");
	}

	@Override
	public int compareTo(Program o) {
		Date startDateComparing = o.getStartDate();
		return startDate.compareTo(startDateComparing);
	}
}
