package xmltv;


import java.io.File;
import java.io.IOException;

import util.OSType;
import util.RunExternalCommand;

public class XMLTVGrabber /*implements XMLTVHelper*/ {

	//private XMLTVParser mXMLTVParser;

	public XMLTVGrabber () {
	}

	public static boolean grabSchedule () {
		/* devo richiamare il grabber di xmltv, il comando dipende
		 * dall'OS su cui mi trovo */
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


