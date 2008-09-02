package it.unibg.cs.jtvguide.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class Schedule {

	private List<Program> scheduleList;

	public Schedule() {
		scheduleList = new ArrayList<Program>();
	}

	public void add(Program p) {
		scheduleList.add(p);
	}

	public List<Program> getOnAirPrograms(Date d) {
		List<Program> programList = new ArrayList<Program>();
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			if (p.getStartDate().compareTo(d) <= 0
					&& p.getStopDate().compareTo(d) >= 0) {
				programList.add(p);
			}
		}
		return programList;
	}
	
	public List<Program> getUpcomingPrograms(Date d) {
		List<Program> onAirPrograms = getOnAirPrograms(d);
		List<Program> upComingPrograms = new ArrayList<Program>();
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Program program = (Program) iterator.next();
			for (Iterator<Program> iterator2 = onAirPrograms.iterator(); iterator2
					.hasNext();) {
				Program onAirProgram = (Program) iterator2.next();
				if (onAirProgram.getChannel() == program.getChannel() &&
						onAirProgram.getStopDate().compareTo(program.getStartDate()) == 0) {
					upComingPrograms.add(program);
				}
				
			}
		}
		return upComingPrograms;
	}
	
	public List<Program> getProgramsByChannel(Channel c) {
		List <Program> programList = new ArrayList<Program>();
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			if (p.getChannel() == c) {
				programList.add(p);
			}
		}
		return programList;
	}
	

	public boolean isAdequate(Vector<String> channelName, int days){
		Boolean adequate = false;
		HashSet<String> channelInSchedule = new HashSet<String>();
		GregorianCalendar gc = new GregorianCalendar();
		GregorianCalendar now = new GregorianCalendar();
		now.setTime(new Date());
		now.add(GregorianCalendar.DAY_OF_MONTH, days-1);
		//non devo sommare anche il giorno corrente
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			channelInSchedule.add(p.getChannel().getDisplayName());
			gc.setTime(p.getStopDate());
			if (gc.compareTo(now) >= 0){
				adequate = true;
			}
		}
		for(int i=0;i<channelName.size();i++){
			if(!channelInSchedule.contains(channelName.get(i)))
				adequate = false;
		}

		return adequate;
	}
}

