/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.ScheduleByChannel;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.util.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVGrabbersByCountry;
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
	private final static String format = "%1$-75s | %2$-10s | %3$-80s\n";;

	public jTVGuide() {
		System.out.println("jTVGuide 2.0");
		XMLTVCommander xmltvc = new XMLTVCommander();
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		int tries = 0;

		while (!UserPreferences.loadFromXMLFile()
				|| !UserPreferences.getXmltvConfigFile().exists()
				|| UserPreferences.getXmltvConfigFile().length() == 0) {
			System.out.println("Configuring jTVGuide and XMLTV...");
			xmltvc.configureXMLTV();
			UserPreferences.saveToXMLFile();
		}

		boolean parsed = false;
		while (parsed == false && tries <= 3) {
			if (!new XMLTVScheduleInspector().isUpToDate()
					|| !MD5Checksum.checkMD5(UserPreferences
							.getXmltvConfigFile().toString(), MD5Checksum
							.readMD5FromFile())) {
				System.out.println("Updating schedule...");
				xmltvc.downloadSchedule();
			}
			if (tries >= 1) {
				System.out
				.println("Couldn't parsing. Downloading a new schedule.");
				UserPreferences.getXmltvOutputFile().delete();
				xmltvc.downloadSchedule();
			}
			if (tries == 4)
				throw new RuntimeException(
				"Couldn't download or parse schedule");
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
		for (Program p: s.getPrograms(c.getTime())) {
			System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
		}
		System.out.println("Searching for *caf*");
		for (Program p: s.getProgramsByName("caf")) {
			System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
		}
		System.out.println("Printing sub-schedules by channel");
		List<ScheduleByChannel> lsc = s.getSchedulesByChannel();
		for (Iterator<ScheduleByChannel> iterator = lsc.iterator(); iterator.hasNext();) {
			ScheduleByChannel scheduleByChannel = iterator
			.next();
			System.out.println(scheduleByChannel.getChannelName());
			List<Program> lspbc = scheduleByChannel.getProgramsFromNowOn();
			for (Iterator<Program> iterator2 = lspbc.iterator(); iterator2.hasNext();) {
				Program p = iterator2.next();
				System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
			}
		}

		Date d = new Date();
		c.setTime(d);
		c.add(Calendar.MINUTE, 30);
		System.out.println("Slicing programs from " + d + " to " + c.getTime());
		for (Program p:  s.getProgramsFromDateToDate(new Date(), c.getTime())) {
			System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
		}
	}

	@Override
	public void run() {
		while(true) {
			if(schedule != null) {
				System.out.print( "\033[H\033[2J" );
				System.out.println("-------------------");
				System.out.println("Onair");
				for (Program p: schedule.getOnAirPrograms())
					System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
				System.out.println("Upcoming");
				for (Program p: schedule.getUpcomingPrograms()) 
					System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
			}
			try {
				Thread.sleep(1000*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}