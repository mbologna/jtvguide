package xmltv;


import java.io.File;
import java.io.IOException;

import util.RunExternalCommand;

public class XMLTVGrabber implements XMLTVHelper {

	private XMLTVParser mXMLTVParser;

	public XMLTVGrabber () {
	}

	public boolean grabSchedule () {
		/* devo richiamare il grabber di xmltv */
		String OS = System.getProperty("os.name").toLowerCase();
		String command;
		if (OS.indexOf("windows") > -1) {
			/* siamo in Windows */
			command = "xmltv.exe tv_grab_it" /*
			 * +
			 * mXMLTVGrabbers.getGrabber(mUserPreferences.getLocation())
			 */;
		}
		else {
			/* UNIX e derivati */
			command = "tv_grab_it"/* mXMLTVGrabbers.getGrabber(mUserPreferences.getLocation()) */;
		}

		UserPreferences mUserPreferences = new UserPreferences();
		mUserPreferences.setDays(3);
		mUserPreferences.setXmltvConfigFile(new File("tv_grab_it.conf"));
		mUserPreferences.setXmltvOutputFile(new File("tv_grab_it.xml"));
		String parameters = mUserPreferences.getOptions();
		RunExternalCommand rec = new RunExternalCommand();
		try {
			rec.runCommand(command + " " + parameters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mXMLTVParser.parse(/*xmltvOutput*/);
		return true;
		
	}

	/*
	 * public XMLTVParser getXMLTVParser () { return mXMLTVParser; }
	 * 
	 * public void setXMLTVParser (XMLTVParser val) { this.mXMLTVParser = val; }
	 */

}


