package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVParser;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;

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

	public List<Program> getOnAirPrograms(Date now) {
		List<Program> programList = new ArrayList<Program>();
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			if (p.getStartDate().compareTo(now) <= 0
					&& p.getStopDate().compareTo(now) >= 0) {
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

	public void update() {
		new XMLTVCommander().downloadSchedule();
	}

}

