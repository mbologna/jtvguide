/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtvguide;

import xmltv.XMLTVGrabber;
import xmltv.XMLTVParser;

/**
 *
 * @author Michele
 */
public class jTVGuide {

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//XMLTVGrabber.grabSchedule();
    	//System.out.println("jTVGuide v" + $LastChangedRevision$);
    	XMLTVParser.parse();
    }

}
