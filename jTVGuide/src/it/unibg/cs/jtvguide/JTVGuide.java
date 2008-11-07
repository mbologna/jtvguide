/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.log.PublicLogger;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.ScheduleByChannel;
import it.unibg.cs.jtvguide.xmltv.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;
import it.unibg.cs.jtvguide.xmltv.XMLTVScheduleInspector;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/**
 *
 * @author Michele
 */
public class JTVGuide implements Runnable {

	private static Thread thread;
	private final static String format = "%1$-75s | %2$-10s | %3$-80s\n";
	protected Schedule schedule;
	public final static Logger log = Logger.getLogger("JTVGuide");

	public JTVGuide() throws ParseException {
		PropertyConfigurator.configure("properties/log4j.properties");
		log.info("JTVGuide 2.0");	
		final XMLTVCommander xmltvc = new XMLTVCommander();
		final XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		int tries = 0;

		try {
			while (!UserPreferences.loadFromXMLFile()
					|| !UserPreferences.getXmltvConfigFile().exists()
					|| UserPreferences.getXmltvConfigFile().length() == 0) {
				log.info("Configuring jTVGuide and XMLTV...");
				xmltvc.configureXMLTV();
				UserPreferences.saveToXMLFile();
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		

		boolean parsed = false;
		while (!parsed && tries <= 3) {
			if (!new XMLTVScheduleInspector().isUpToDate()
					|| !MD5Checksum.checkMD5(UserPreferences
							.getXmltvConfigFile().toString(), MD5Checksum
							.readMD5FromFile())) {
				log.info("Updating schedule...");
				xmltvc.downloadSchedule();
			}
			if (tries >= 1) {
				log.info("Couldn't parsing. Downloading a new schedule.");
				UserPreferences.getXmltvOutputFile().delete();
				xmltvc.downloadSchedule();
			}
			if (tries == 4) {
				throw new ParseException(
				"Couldn't download or parse schedule", 0);
			}
			log.info("Trying to parse schedule...");
			parsed = xmltvParser.parse();
			tries++;
		}
		schedule = xmltvParser.getSchedule();
		log.info("Schedule parsed correctly.");
		thread = new Thread(this);
		thread.start();
	}


	public static void main(final String[] args) {
		JTVGuide jtv = null;	
		try {
			jtv = new JTVGuide();
		} catch (ParseException e) {
			PublicLogger.getLogger().error(e);
		}
		final Schedule s = jtv.schedule;
		PublicLogger.getLogger().debug("Tonight");
		final Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 21);
		c.set(Calendar.MINUTE,30);
		for (Program p: s.getPrograms(c.getTime())) {
			System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
		}
		PublicLogger.getLogger().debug("Searching for *caf*");
		for (Program p: s.getProgramsByName("caf")) {
			System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
		}
		PublicLogger.getLogger().debug("Printing sub-schedules by channel");
		final List<ScheduleByChannel> lsc = s.getSchedulesByChannel();
		for (Iterator<ScheduleByChannel> iterator = lsc.iterator(); iterator.hasNext();) {
			ScheduleByChannel scheduleByChannel = iterator
			.next();
			PublicLogger.getLogger().debug(scheduleByChannel.getChannelName());
			List<Program> lspbc = scheduleByChannel.getProgramsFromNowOn();
			for (Iterator<Program> iterator2 = lspbc.iterator(); iterator2.hasNext();) {
				Program p = iterator2.next();
				System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
			}
		}

		Date d = new Date();
		c.setTime(d);
		c.add(Calendar.MINUTE, 30);
		PublicLogger.getLogger().debug("Slicing programs from " + d + " to " + c.getTime());
		for (Program p:  s.getProgramsFromDateToDate(new Date(), c.getTime())) {
			System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
		}
	}

	@Override
	public void run() {
		while(true) {
			if(schedule != null) {
				PublicLogger.getLogger().debug( "\033[H\033[2J" );
				PublicLogger.getLogger().debug("-------------------");
				PublicLogger.getLogger().debug("Onair");
				for (Program p: schedule.getOnAirPrograms())
					System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
				PublicLogger.getLogger().debug("Upcoming");
				for (Program p: schedule.getUpcomingPrograms())
					System.out.format(format, p.toString(), p.getInfo(), p.getDesc() == null? "" : p.getDesc());
			}
			try {
				Thread.sleep(1000*10);
			} catch (InterruptedException e) {
				PublicLogger.getLogger().error(e);
			}
		}
	}
}