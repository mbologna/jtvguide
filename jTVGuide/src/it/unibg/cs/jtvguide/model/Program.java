package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.interfaces.ChannelInterface;
import it.unibg.cs.jtvguide.interfaces.ProgramInterface;
import it.unibg.cs.jtvguide.util.DateFormatter;
import it.unibg.cs.jtvguide.util.TimeConversions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A class to contain tv program data
 * @author Michele Bologna, Sebastiano Rota
 *
 */

public class Program implements ProgramInterface {

	private String title;
	private Date startDate;
	private Date stopDate;
	private Channel mChannel;
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * Construct a new program object
	 * @param startDate program's starting date
	 * @param stopDate program's ending date
	 * @param c program's airing channel
	 * @param title program's title
	 */
	public Program(Date startDate, Date stopDate, Channel c, String title) {
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.mChannel = c;
		this.title = title;
	}

	@Override
	public int compareTo(ProgramInterface o) {
		Date startDateComparing = o.getStartDate();
		return startDate.compareTo(startDateComparing);
	}

	public Channel getChannel() {
		return mChannel;
	}
	/**
	 * 
	 * @return the program completion %
	 */
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
					.getTimeInMillis()) * 100.0 / (stop.getTimeInMillis() - start
					.getTimeInMillis())));

	}
	/**
	 * 
	 * @return the state of the program [ONAIR, UPCOMING, FINISHED, UNKNOWN]
	 */
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

	/**
	 * 
	 * @return how much time we have to wait before the program starts
	 */
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

	public void setChannel(ChannelInterface val) {
		this.mChannel = (Channel) val;
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
