/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.ChannelMap;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



/**
 *
 * @author Michele
 */
public class jTVGuide {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
    	System.out.println("jTVGuide version: $Rev$");
    	/*
    	 * First run or file config not found
    	 */
    	
    	boolean loaded = UserPreferences.loadFromXMLFile();
	
    	// we have to avoid short-circuit evaluation, OR = |
    	if (!loaded | !UserPreferences.getXmltvConfigFile().exists() | UserPreferences.getXmltvConfigFile().length() == 0) {
    		new XMLTVCommander().configureXMLTV();
        	UserPreferences.setXmltvConfigFile("tv_grab_it.conf");
        	UserPreferences.setXmltvOutputFile("tv_grab_it.xml");
        	UserPreferences.setDays(2);
        	UserPreferences.saveToXMLFile();
    	}
    	/*
    	 * Does the schedule contains enough data? (as the user specifies how many days)
    	 */
    	if (!new XMLTVScheduleInspector().isUpToDate()) {
    		XMLTVCommander xmltvc = new XMLTVCommander();
    		xmltvc.downloadSchedule();   		
    	}
    	
    	/*
    	 * Parse the downloaded schedule
    	 */
    	XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
    	Schedule s = xmltvParser.parse();
    	List<Program> lk = s.getOnAirPrograms();
    	Collections.sort(lk);
    	List<Program> lp = s.getUpcomingPrograms(new Date());
    	Collections.sort(lp);
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
    	System.out.println("Tonight");
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.HOUR_OF_DAY, 21);
    	c.set(Calendar.MINUTE,30);
    	List<Program> lss = s.getOnAirPrograms();
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
    	ChannelMap cm = xmltvParser.getChannelMap();
    	for (Iterator<URI> iterator = cm.iterator(); iterator.hasNext();) {
    		System.out.println("*****************");
			Channel chan = cm.get(iterator.next());
			System.out.println(chan.getDisplayName());
			List<Program> lchan = s.getProgramsByChannel(chan);
			Collections.sort(lchan);
			for (Iterator<Program> iterator2 = lchan.iterator(); iterator2.hasNext();) {
				Program program = iterator2.next();
				//System.out.println(program);
			}
			System.out.println("-----------------");
		}
    	System.out.println("Slicing");
    	Date d = new Date();
    	c.setTime(d);
    	c.add(Calendar.MINUTE, 30);
    	System.out.println(d);
    	System.out.println(c.getTime());
    	List<Program> sfkjd = s.getProgramsFromDateToDate(new Date(), c.getTime() );
    	Collections.sort(sfkjd);
    	for (Iterator iterator = sfkjd.iterator(); iterator.hasNext();) {
			Program program = (Program) iterator.next();
			System.out.println(program);
		}
    }

}
