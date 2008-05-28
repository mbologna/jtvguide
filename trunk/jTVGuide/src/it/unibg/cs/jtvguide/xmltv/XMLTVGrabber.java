package it.unibg.cs.jtvguide.xmltv;


import it.unibg.cs.jtvguide.util.OSType;
import it.unibg.cs.jtvguide.util.RunExternalCommand;

import java.io.File;
import java.io.IOException;


public class XMLTVGrabber /*implements XMLTVHelper*/ {

	//private XMLTVParser mXMLTVParser;

	public XMLTVGrabber () {
	}
	
	/*
	 * Il metodo richiama il comando di xmltv appropriato (in base 
	 * alla piattaforma) per effettuare il download dei palinsesti
	 * secondo le opzioni specificate dall'utente.
	 */

	public static boolean grabSchedule () {
		
		String command;
		if (OSType.getOS().equals("windows")) {
			command = "xmltv.exe tv_grab_it";
		}
		else {
			command = "tv_grab_it";
		}

		UserPreferences.setDays(3);
		UserPreferences.setXmltvConfigFile(new File("tv_grab_it.conf"));
		UserPreferences.setXmltvOutputFile(new File("tv_grab_it.xml"));
		String parameters = UserPreferences.getOptions();
		try {
			RunExternalCommand.runCommand(command + " " + parameters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//mXMLTVParser.parse(/*xmltvOutput*/);
		return true;
		
	}

	/*
	 * public XMLTVParser getXMLTVParser () { return mXMLTVParser; }
	 * 
	 * public void setXMLTVParser (XMLTVParser val) { this.mXMLTVParser = val; }
	 */

}


