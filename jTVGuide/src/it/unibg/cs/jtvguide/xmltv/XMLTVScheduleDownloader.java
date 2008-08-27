package it.unibg.cs.jtvguide.xmltv;


import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.util.RunExternalCommand;

import java.io.File;
import java.io.IOException;


public class XMLTVScheduleDownloader {

	//private XMLTVParser mXMLTVParser;

	public XMLTVScheduleDownloader () {
	}

	/*
	 * Il metodo richiama il comando di xmltv appropriato (in base
	 * alla piattaforma) per effettuare il download dei palinsesti
	 * secondo le opzioni specificate dall'utente.
	 */

	public static boolean grabSchedule () {

		String command;
		
		if (SystemProperties.detectOS().equals("windows")) {
			command = "xmltv.exe " + UserPreferences.getXMLTVCommandByCountry();
		}
		else {
			command = UserPreferences.getXMLTVCommandByCountry();
		}

		//UserPreferences.setDays(3);
		//UserPreferences.setXmltvConfigFile(new File("tv_grab_it.conf"));
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


