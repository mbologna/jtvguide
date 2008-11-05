package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.util.RunExternalCommand;
import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.xmltv.interfaces.XMLTVDownloader;

/**
 * A class to command XMLTV to configure and download schedules
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class XMLTVCommander implements XMLTVConfigurator, XMLTVDownloader{
	
	/**
	 * Configure XMLTV 
	 */
	@Override
	public int configureXMLTV() {
		UserPreferences.getXmltvConfigFile().delete();
		UserPreferences.getXmltvOutputFile().delete();
		return RunExternalCommand.runCommand(getXMLTVCommand() + " " + CONFIG_SWITCH);
	}

	/**
	 * Download XMLTV schedule
	 */
	@Override
	public int downloadSchedule() {
		String parameters = UserPreferences.getOptions();
		MD5Checksum.writeMD5ToFile(UserPreferences.getXmltvConfigFile());
		return RunExternalCommand.runCommand(getXMLTVCommand() + " " + parameters);
	}

	private String getXMLTVCommand() {
		if (SystemProperties.detectOS().equals("windows")) {
			return "xmltv.exe " + UserPreferences.getXMLTVCommandByCountry();
		}
		else {
			return UserPreferences.getXMLTVCommandByCountry();
		}
	}
}
