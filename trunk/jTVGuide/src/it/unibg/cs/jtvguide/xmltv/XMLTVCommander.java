package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVConfigurator;
import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVDownloader;
import it.unibg.cs.jtvguide.util.RunExternalCommand;
import it.unibg.cs.jtvguide.util.SystemProperties;

public class XMLTVCommander implements XMLTVConfigurator, XMLTVDownloader{
	
	protected String getXMLTVCommand() {
		if (SystemProperties.detectOS().equals("windows")) {
			return "xmltv.exe " + UserPreferences.getXMLTVCommandByCountry();
		}
		else {
			return UserPreferences.getXMLTVCommandByCountry();
		}
	}

	@Override
	public int downloadSchedule() {
		String parameters = UserPreferences.getOptions();
		return RunExternalCommand.runCommand(getXMLTVCommand() + " " + parameters);
	}

	@Override
	public int configureXMLTV() {
		return RunExternalCommand.runCommand(getXMLTVCommand() + CONFIG_SWITCH);
	}
	
	
	
	
}
