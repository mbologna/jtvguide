package it.unibg.cs.jtvguide.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Schedule implements Iterable<Program> {

	protected List<Program> scheduleList;
	protected ChannelMap channelMap = null;

	public Schedule(ChannelMap cm) {
		scheduleList = new ArrayList<Program>();
		this.channelMap = cm;
	}

	protected Schedule(List<Program> sl) {
		this.scheduleList = sl;
	}

	public void add(Program p) {
		scheduleList.add(p);
		Collections.sort(scheduleList);
	}

	public List<Program> getOnAirPrograms() {
		return getProgramsFromDateToDate(new Date(), new Date());
	}

	public List<Program> getPrograms(Date d) {
		return getProgramsFromDateToDate(d, d);
	}

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

	public List<Program> getProgramsFromNowOn() {
		return getProgramsFromDateOn(new Date());
	}

	public List<ScheduleByChannel> getSchedulesByChannel() {
		List<ScheduleByChannel> lsc = new ArrayList<ScheduleByChannel>();
		for (Iterator<URI> iterator = channelMap.iterator(); iterator.hasNext();) {
			Channel c = channelMap.get(iterator.next());
			lsc.add(getChannelSchedule(c));
		}
		return lsc;
	}

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
