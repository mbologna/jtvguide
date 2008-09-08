/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.ScheduleByChannel;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



/**
 *
 * @author Michele
 */
public class jTVGuide implements Runnable {

	private static Thread thread;
	Schedule schedule;

	public jTVGuide() {
		System.out.println("jTVGuide 2.0");
		XMLTVCommander xmltvc = new XMLTVCommander();
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		int tries = 0;

		boolean loaded = UserPreferences.loadFromXMLFile();
		while (!loaded || !UserPreferences.getXmltvConfigFile().exists() || UserPreferences.getXmltvConfigFile().length() == 0) {
			System.out.println("Configuring jTVGuide and XMLTV...");
			xmltvc.configureXMLTV();
			UserPreferences.saveToXMLFile();
			loaded = UserPreferences.loadFromXMLFile();
		}

		boolean parsed = false;
		while(parsed == false && tries <= 3) {
			if (!new XMLTVScheduleInspector().isUpToDate()) {
				System.out.println("Updating schedule...");
				xmltvc.downloadSchedule();   		
			}
			if (tries >= 1) {
				System.out.println("Couldn't parsing. Downloading a new schedule.");
				UserPreferences.getXmltvOutputFile().delete();
				xmltvc.downloadSchedule(); 
			}
			if (tries == 4) throw new RuntimeException("Couldn't download or parse schedule");
			System.out.println("Trying to parse schedule...");
			parsed = xmltvParser.parse();
			tries++;
		}
		schedule = xmltvParser.getSchedule();
		System.out.println("Schedule parsed correctly.");
		thread = new Thread(this);
		thread.start();
	}


	public static void main(String[] args) {
		jTVGuide jtv = new jTVGuide();
		Schedule s = jtv.schedule;

		System.out.println("Tonight");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 21);
		c.set(Calendar.MINUTE,30);
		for (Program program: s.getPrograms(c.getTime())) {
			System.out.println(program);
		}
		System.out.println("Searching for *caf*");
		for (Program program: s.getProgramsByName("caf")) {
			System.out.println(program);
		}
		System.out.println("Printing sub-schedules by channel");
		List<ScheduleByChannel> lsc = s.getSchedulesByChannel();
		for (Iterator<ScheduleByChannel> iterator = lsc.iterator(); iterator.hasNext();) {
			ScheduleByChannel scheduleByChannel = iterator
			.next();
			System.out.println(scheduleByChannel.getChannelName());
			List<Program> lspbc = scheduleByChannel.getProgramsFromNowOn();
			for (Iterator<Program> iterator2 = lspbc.iterator(); iterator2.hasNext();) {
				Program program = iterator2.next();
				System.out.println(program);
			}
		}

		Date d = new Date();
		c.setTime(d);
		c.add(Calendar.MINUTE, 30);
		System.out.println("Slicing programs from " + d + " to " + c.getTime());
		for (Program program:  s.getProgramsFromDateToDate(new Date(), c.getTime())) {
			System.out.println(program);
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(schedule != null) {
				System.out.print( "\033[H\033[2J" );
				System.out.println("-------------------");
				System.out.println("Onair");
				for (Program p: schedule.getOnAirPrograms())
					System.out.println(p);
			}
			System.out.println("Upcoming");
			for (Program p: schedule.getUpcomingPrograms()) {
				System.out.println(p);
			}
		}
	}
}
