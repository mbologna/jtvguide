/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
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
    	UserPreferences.setXmltvConfigFile("tv_grab_it.conf");
    	/*
    	 * First run or file config not found
    	 */
    	if (!UserPreferences.loadFromXMLFile() || !UserPreferences.getXmltvConfigFile().exists()) {
    		new XMLTVCommander().configureXMLTV();
    	}
    	
    	/*
    	 * Does the schedule contains today data?
    	 */
    	if (!new XMLTVScheduleInspector().isUpToDate()) {
    		new XMLTVCommander().downloadSchedule();
    	}
    	
    	/*
    	 * Parse the downloaded schedule
    	 */
    	Schedule s = new XMLTVParserImpl().parse();
    	List<Program> lk = s.getOnAirPrograms(new Date());
    	List<Program> lp = s.getUpcomingPrograms(new Date());
    	for (Iterator<Program> iterator = lk.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			System.out.println(p);
		}
    	for (Iterator<Program> iterator = lp.iterator(); iterator.hasNext();) {
			Program program = (Program) iterator.next();
			System.out.println(program);
			
		}
    }

}
