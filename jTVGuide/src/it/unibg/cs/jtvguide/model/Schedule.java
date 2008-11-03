package it.unibg.cs.jtvguide.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * A class to represent the schedule information.
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class Schedule implements Iterable<Program> {

	protected List<Program> scheduleList;
	protected ChannelMap channelMap = null;

	/**
	 * Construct a new schedule
	 * @param cm the channelmap containing the channels
	 */
	public Schedule(ChannelMap cm) {
		scheduleList = new ArrayList<Program>();
		this.channelMap = cm;
	}
	
	/**
	 * Construct a new schedule
	 * @param sl a program list
	 */
	protected Schedule(List<Program> sl) {
		this.scheduleList = sl;
	}
	
	/**
	 * Add a new program to the schedule
	 * @param p program to insert
	 */
	public void add(Program p) {
		scheduleList.add(p);
		Collections.sort(scheduleList);
	}

	/**
	 * 
	 * @return a list containing on-air programs
	 */
	public List<Program> getOnAirPrograms() {
		return getProgramsFromDateToDate(new Date(), new Date());
	}
	/**
	 * Slice the schedule to match programs which intersect the date specified
	 * @param d the date
	 * @return a list containing matching programs, if any.
	 */
	public List<Program> getPrograms(Date d) {
		return getProgramsFromDateToDate(d, d);
	}
	
	/**
	 * Using a regexp to match ignore-case, we search the specified pattern in program's title
	 * @param pattern to search
	 * @return a list containing matching programs, if any.
	 */
	public List<Program> getProgramsByName(String pattern) {
		List<Program> matchPrograms = new ArrayList<Program>();
		for (Program p : scheduleList) {
			if (p.getState() == ProgramState.UNKNOWN)
				continue;
			/* if title contains the pattern specified, ignore case (regexp) */
			if (p.getTitle().matches("(?i).*" + pattern + ".*"))
				matchPrograms.add(p);
		}
		return matchPrograms;
	}
	
	/**
	 * Slice the schedule to match programs which intersect from date specified to the end of the schedule
	 * @param d the date 
	 * @return a list containing matching programs, if any.
	 */
	public List<Program> getProgramsFromDateOn(Date d) {
		List<Program> programList = new ArrayList<Program>();
		for (Program p : scheduleList) {
			if (p.getState() == ProgramState.UNKNOWN)
				continue;
			if (p.getStopDate().compareTo(d) >= 0) {
				programList.add(p);
			}
		}
		return programList;
	}
	/**
	 * Slice the schedule to match programs which intersect from the first date specified to the second date specified
	 * @param from starting date
	 * @param to ending date
	 * @return a list containing matching programs, if any.
	 */
	public List<Program> getProgramsFromDateToDate(Date from, Date to) {
		List<Program> programList = new ArrayList<Program>();
		for (Program p : scheduleList) {
			if (p.getState() == ProgramState.UNKNOWN)
				continue;
			if ((p.getStartDate().compareTo(from) >= 0 && p.getStopDate()
					.compareTo(to) <= 0)
					|| (p.getStartDate().compareTo(from) <= 0 && p
							.getStopDate().compareTo(from) >= 0)
					|| (p.getStartDate().compareTo(to) <= 0 && p.getStopDate()
							.compareTo(to) >= 0)) {
				programList.add(p);
			}
		}
		return programList;
	}

	/**
	 * 
	 * @return a list containing the programs that are on-air and upcoming
	 */
	public List<Program> getProgramsFromNowOn() {
		return getProgramsFromDateOn(new Date());
	}
	/**
	 * 
	 * @return a list containing programs grouped by channel
	 */
	public List<ScheduleByChannel> getSchedulesByChannel() {
		List<ScheduleByChannel> lsc = new ArrayList<ScheduleByChannel>();
		for (Iterator<URI> iterator = channelMap.iterator(); iterator.hasNext();) {
			Channel c = channelMap.get(iterator.next());
			lsc.add(getChannelSchedule(c));
		}
		return lsc;
	}
	
	/**
	 * 
	 * @return a list containing upcoming programs
	 */

	public List<Program> getUpcomingPrograms() {
		List<Program> onAirPrograms = getOnAirPrograms();
		List<Program> upComingPrograms = new ArrayList<Program>();
		for (Program p : scheduleList) {
			if (p.getState() == ProgramState.UNKNOWN)
				continue;
			for (Program onAirProgram : onAirPrograms) {
				if (onAirProgram.getChannel() == p.getChannel()
						&& onAirProgram.getStopDate().compareTo(
								p.getStartDate()) == 0) {
					upComingPrograms.add(p);
				}
			}
		}
		return upComingPrograms;
	}

	/**
	 * Construct a new schedule grouping programs that match the specified channel
	 * @param c the channel
	 * @return a list containing matching programs, if any.
	 */
	private ScheduleByChannel getChannelSchedule(Channel c) {
		List<Program> programList = new ArrayList<Program>();
		for (Program p : scheduleList) {
			if (p.getChannel().equals(c)) {
				programList.add(p);
			}
		}
		return new ScheduleByChannel(programList, c);
	}

	@Override
	public Iterator<Program> iterator() {
		return scheduleList.iterator();
	}
}
