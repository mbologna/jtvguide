package it.unibg.cs.jtvguide.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Schedule {

	protected List<Program> scheduleList;
	protected ChannelMap channelMap = null;
	
	protected Schedule(List<Program> sl) {
		this.scheduleList = sl;
	}

	public Schedule(ChannelMap cm) {
		scheduleList = new ArrayList<Program>();
		this.channelMap = cm;
	}

	public void add(Program p) {
		scheduleList.add(p);
	}

	public List<Program> getOnAirPrograms() {
		return getProgramsFromDateToDate(new Date(), new Date());
	}
	
	public List<Program> getPrograms(Date d) {
		return getProgramsFromDateToDate(d,d);
	}
	
	public List<Program> getProgramsFromNowOn() {
		return getProgramsFromDateOn(new Date());
	}
	
	public List<Program> getProgramsFromDateOn(Date d) {
		List<Program> programList = new ArrayList<Program>();
		for (Program p: scheduleList) {
			if (p.getStopDate().compareTo(d) >= 0){
				programList.add(p);
			}
		}
		Collections.sort(programList);
		return programList;
	}
	
	public List<Program> getUpcomingPrograms() {
		List<Program> onAirPrograms = getOnAirPrograms();
		List<Program> upComingPrograms = new ArrayList<Program>();
		for (Program program: scheduleList) {
			for (Program onAirProgram: onAirPrograms) {
				if (onAirProgram.getChannel() == program.getChannel() &&
						onAirProgram.getStopDate().compareTo(program.getStartDate()) == 0) {
					upComingPrograms.add(program);
				}
				
			}
		}
		Collections.sort(upComingPrograms);
		return upComingPrograms;
	}
	
	public List<Program> getProgramsFromDateToDate(Date from, Date to) {
		List<Program> programList = new ArrayList<Program>();
		for (Program p: scheduleList) {
			if (
					(p.getStartDate().compareTo(from) >= 0 && p.getStopDate().compareTo(to) <= 0 ) ||
					(p.getStartDate().compareTo(from) <= 0 && p.getStopDate().compareTo(from) >= 0) ||
					(p.getStartDate().compareTo(to) <= 0 && p.getStopDate().compareTo(to) >= 0) 
				){
				programList.add(p);
			}
		}
		Collections.sort(programList);
		return programList;	
	}
	
	public List<Program> getProgramsByName(String pattern) {
		List <Program> matchPrograms = new ArrayList<Program>();
		for (Program program: scheduleList) {
			/* if title contains the pattern specified, ignore case (regexp) */
			if (program.getTitle().matches("(?i).*"+pattern+".*")) //&& d.compareTo(program.getStartDate()) <= 0)
				matchPrograms.add(program);
		}
		Collections.sort(matchPrograms);
		return matchPrograms;
	}
	
	public List<ScheduleByChannel> getSchedulesByChannel() {
		List<ScheduleByChannel> lsc = new ArrayList<ScheduleByChannel>();
		for (Iterator<URI> iterator = channelMap.iterator(); iterator.hasNext();) {
			Channel c = channelMap.get(iterator.next());
			lsc.add(getChannelSchedule(c));
		}
		return lsc;
	}
	
	private ScheduleByChannel getChannelSchedule(Channel c) {
		List <Program> programList = new ArrayList<Program>();
		for (Program p: scheduleList) {
			if (p.getChannel() == c) {
				programList.add(p);
			}
		}
		Collections.sort(programList);
		return new ScheduleByChannel(programList,c);
	}
	
	
	
}

