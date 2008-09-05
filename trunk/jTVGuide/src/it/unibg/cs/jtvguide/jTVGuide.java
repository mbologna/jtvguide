/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.ChannelMap;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.ScheduleByChannel;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



/**
 *
 * @author Michele
 */
public class jTVGuide implements Runnable {
	
	private Thread thread;
	Schedule schedule;
	ChannelMap cm;

	public jTVGuide() {
		System.out.println("jTVGuide version: $Rev$");
	   	boolean loaded = UserPreferences.loadFromXMLFile();
    	while (!loaded && (!UserPreferences.getXmltvConfigFile().exists() || UserPreferences.getXmltvConfigFile().length() == 0)) {
    		System.out.println("Configuring jTVGuide and XMLTV...");
    		new XMLTVCommander().configureXMLTV();
        	UserPreferences.saveToXMLFile();
        	loaded = UserPreferences.loadFromXMLFile();
    	}
	   	int tries = 0;
    	XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
    	XMLTVCommander xmltvc = new XMLTVCommander();
    	while(schedule == null && tries <= 3) {
    		if (tries >= 1) {
    			System.out.println("Couldn't parsing. Re-downloading schedule.");
    			UserPreferences.getXmltvOutputFile().delete();
    			xmltvc.downloadSchedule(); 
    		}
    		if (!new XMLTVScheduleInspector().isUpToDate()) {
    			System.out.println("Downloading schedule...");
    			xmltvc.downloadSchedule();   		
    		}
    		System.out.println("Trying to parse schedule...");
    		schedule = xmltvParser.parse();
    		cm = xmltvParser.getChannelMap();
    		tries++;
    	}
    	if (tries == 4) throw new RuntimeException("Couldn't download or parse schedule");
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
    	List<Program> lss = s.getPrograms(c.getTime());
    	for (Iterator<Program> iterator = lss.iterator(); iterator.hasNext();) {
			Program program = iterator.next();
			System.out.println(program);
		}
    	System.out.println("Searching for *caf*");
    	List<Program> ls = s.getProgramsByName("caf");
    	for (Iterator<Program> iterator = ls.iterator(); iterator.hasNext();) {
			Program program = iterator.next();
			System.out.println(program);
		}
    	ChannelMap cm = jtv.cm;
    	for (Iterator<URI> iterator = cm.iterator(); iterator.hasNext();) {
    		Channel chan = cm.get(iterator.next());
			System.out.println(chan.getDisplayName());
			ScheduleByChannel sbc = s.getChannelSchedule(chan);
			List<Program> sbcl = sbc.getProgramsFromNowOn();
			for (Iterator<Program> iterator2 = sbcl.iterator(); iterator2.hasNext();) {
				Program program = iterator2.next();
				System.out.println(program);
			}
		}
    	System.out.println("Slicing");
    	Date d = new Date();
    	c.setTime(d);
    	c.add(Calendar.MINUTE, 30);
    	System.out.println("Programs from " + d);
    	System.out.println("Programs to " + c.getTime());
    	List<Program> sfkjd = s.getProgramsFromDateToDate(new Date(), c.getTime() );
    	for (Iterator<Program> iterator = sfkjd.iterator(); iterator.hasNext();) {
			Program program = iterator.next();
			System.out.println(program);
		}
    }

	@Override
	public void run() {
		while(true) {
			if(schedule != null) {
				System.out.println("-------------------");
		    	List<Program> lk = schedule.getOnAirPrograms();
		    	List<Program> lp = schedule.getUpcomingPrograms();
		    	System.out.println("Onair");
		    	for (Iterator<Program> iterator = lk.iterator(); iterator.hasNext();) {
					Program p = iterator.next();
					System.out.println(p);
				}
		    	System.out.println("Upcoming");
		    	for (Iterator<Program> iterator = lp.iterator(); iterator.hasNext();) {
					Program program =  iterator.next();
					System.out.println(program);
				}
		    	try {
					Thread.sleep(1000*60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
