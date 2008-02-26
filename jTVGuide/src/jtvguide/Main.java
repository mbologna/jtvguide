/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtvguide;

import java.io.File;

import xmltv.UserPreferences;
import xmltv.XMLTVGrabber;

/**
 *
 * @author Michele
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
    	XMLTVGrabber xmltvg = new XMLTVGrabber();
    	xmltvg.grabSchedule();
    }

}
