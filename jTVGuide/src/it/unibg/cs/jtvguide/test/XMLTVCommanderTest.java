package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import junit.framework.TestCase;

public class XMLTVCommanderTest extends TestCase {
	XMLTVCommander xmltvc;
	
	public void setUp() throws Exception {
		xmltvc = new XMLTVCommander();
		UserPreferences.setDays(1);
	}

	public void tearDown() throws Exception {
		xmltvc = null;
	}
	
	public void testXMLTVCommands() {
		assertEquals(0,xmltvc.configureXMLTV());
		assertEquals(0, xmltvc.downloadSchedule());
	}
	

}
