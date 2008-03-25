/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtvguide;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import xmltv.XMLTVParser;
import collection.Schedule;
import data.Program;


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
    	Schedule s = new Schedule();
    	//XMLTVGrabber.grabSchedule();
    	/*
    	 * Chi deve controllare se lo schedule non è aggiornato?
    	 * Parser o schedule?
    	 */
    	XMLTVParser xmltvp = new XMLTVParser();
    	xmltvp.parse();
    	s = xmltvp.getMSchedule();
    	List<Program> lk = s.getPrograms(new Date()); /* programmi in onda ora */
    	for (Iterator<Program> iterator = lk.iterator(); iterator.hasNext();) {
			Program p = (Program) iterator.next();
			System.out.println(p);
		}
    }

}
