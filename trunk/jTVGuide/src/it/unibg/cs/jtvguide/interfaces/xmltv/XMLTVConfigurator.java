package it.unibg.cs.jtvguide.interfaces.xmltv;

import it.unibg.cs.jtvguide.UserPreferences;

public interface XMLTVConfigurator {
	public String CONFIG_SWITCH = "--configure --config-file \"" + UserPreferences.getXmltvConfigFile() + "\"";
	
	public int configureXMLTV();
}
