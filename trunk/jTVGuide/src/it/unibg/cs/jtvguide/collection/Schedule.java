package it.unibg.cs.jtvguide.collection;

import it.unibg.cs.jtvguide.data.Program;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Schedule {

	private List<Program> scheduleList;

	// private XMLTVGrabber mXMLTVGrabber;

	public Schedule() {
		scheduleList = new LinkedList<Program>();
	}

	public void add(Program p) {
		scheduleList.add(p);
	}

	/*public boolean isUpToDate() {
		Program p;
		*//*****************************************//*
		 Non c'è un modo migliore per farlo? 
		int todayDay, todayMonth, todayYear;
		int startDay, startMonth, startYear;
		Calendar day = new GregorianCalendar();
		todayDay = day.get(Calendar.DAY_OF_MONTH);
		todayMonth = day.get(Calendar.MONTH);
		todayYear = day.get(Calendar.YEAR);
		*//*********************************************//*
		
		for (Iterator<Program> iterator = scheduleList.iterator(); iterator.hasNext();) {
			p = (Program) iterator.next();
			day.setTime(p.getStartDate());
			startDay = day.get(Calendar.DAY_OF_MONTH);
			startMonth = day.get(Calendar.MONTH);
			startYear = day.get(Calendar.YEAR);
			if (todayDay == startDay && todayMonth == startMonth && todayYear == startYear) {
				return true;
			}
		}
		return false;
	}*/

	public boolean update() {
		/* richiamare il grabber */
		return true;
	}

	public List<Program> getPrograms(Date now) {
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

	/*
	 * public XMLTVGrabber getXMLTVGrabber () { return mXMLTVGrabber; }
	 * 
	 * public void setXMLTVGrabber (XMLTVGrabber val) { this.mXMLTVGrabber =
	 * val; }
	 */

}
