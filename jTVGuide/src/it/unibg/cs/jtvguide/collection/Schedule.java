package it.unibg.cs.jtvguide.collection;

import it.unibg.cs.jtvguide.data.Program;
import it.unibg.cs.jtvguide.xmltv.XMLTVGrabber;
import it.unibg.cs.jtvguide.xmltv.XMLTVParser;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Schedule {

	private List<Program> scheduleList;

	public Schedule() {
		scheduleList = new LinkedList<Program>();
	}

	public void add(Program p) {
		scheduleList.add(p);
	}

	private boolean isUpToDate() {
		List<Program> result = this.getOnAirPrograms(new Date());
		return !result.isEmpty();
		
	}

	public List<Program> getOnAirPrograms(Date now) {
		List<Program> programList = new LinkedList<Program>();
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			if (p.getStartDate().compareTo(now) <= 0
					&& p.getStopDate().compareTo(now) >= 0) {
				programList.add(p);
			}
		}
		return programList;
	}
	
	public void update() {
		XMLTVParser xmltvp = new XMLTVParser();
    	xmltvp.parse(this);
		if (!this.isUpToDate()) {
			XMLTVGrabber.grabSchedule();
			xmltvp.parse(this);
		}  	
	}
}
