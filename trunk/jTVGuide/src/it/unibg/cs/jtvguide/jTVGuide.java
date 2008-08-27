/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.collection.Schedule;
import it.unibg.cs.jtvguide.data.Program;
import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVConfigurator;
import it.unibg.cs.jtvguide.xmltv.XMLTVGrabbersByCountry;
import it.unibg.cs.jtvguide.xmltv.XMLTVScheduleDownloader;

import java.io.File;
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

	/**
     * @param args the command line arguments
	 * @throws MalformedURLException
	 * @throws URISyntaxException
     */

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
    	System.out.println("jTVGuide version: $Rev$");
    	System.out.println("Using locale for " + SystemProperties.getSystemLanguage());
    	Schedule s = new Schedule();
    	s.update();
    	UserPreferences.setDays(1);
    	UserPreferences.setCountry(SystemProperties.getSystemLanguage());
    	UserPreferences.setXmltvConfigFile(new File("tv_grab_it.conf"));
    	XMLTVConfigurator.chargeVectors();
    	if((!s.isAdequate(XMLTVConfigurator.getSelectedChannelNameVector(), UserPreferences.getDays()))||(!s.isUpToDate())){
            XMLTVScheduleDownloader.grabSchedule();
            s.update();
        }
    	List<Program> lk = s.getOnAirPrograms(new Date());
    	for (Iterator<Program> iterator = lk.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			System.out.println(p);
		}
    }

}
