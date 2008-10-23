package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVDownloader;
import it.unibg.cs.jtvguide.util.RunExternalCommand;
import it.unibg.cs.jtvguide.util.SystemProperties;

public class XMLTVCommander implements XMLTVConfigurator, XMLTVDownloader{
	
	@Override
	public int configureXMLTV() {
		UserPreferences.getXmltvConfigFile().delete();
		UserPreferences.getXmltvOutputFile().delete();
		return RunExternalCommand.runCommand(getXMLTVCommand() + " " + CONFIG_SWITCH);
	}

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
