package it.unibg.cs.jtvguide.interfaces.xmltv;

import it.unibg.cs.jtvguide.UserPreferences;

public interface XMLTVConfigurator {
	public String CONFIG_SWITCH = UserPreferences.getXmltvConfigFile().toString().indexOf(' ') == -1? 
			"--configure --gui --config-file " + UserPreferences.getXmltvConfigFile() 
			: "--configure --gui --config-file \"" + UserPreferences.getXmltvConfigFile() + "\""; 
	
	public int configureXMLTV();
}
