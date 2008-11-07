package it.unibg.cs.jtvguide.test;

import java.io.File;

import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import junit.framework.TestCase;

public class XMLTVCommanderTest extends TestCase {
	XMLTVCommander xmltvc;
	
	public void setUp() throws Exception {
		xmltvc = new XMLTVCommander();
		UserPreferences.setDays(1);
		UserPreferences.setXmltvConfigFile(File.createTempFile("jtvguide", "test.conf").toString());
		UserPreferences.setXmltvOutputFile(File.createTempFile("jtvguide", "test.xml").toString());
	}

	public void tearDown() throws Exception {
		UserPreferences.setXmltvConfigFile(new File("tv_grab.conf").getAbsolutePath());
		UserPreferences.setXmltvOutputFile(new File("tv_grab.xml").getAbsolutePath());
		UserPreferences.setDays(6);
		xmltvc = null;
	}
	
	public void testXMLTVCommands() {
		assertEquals(0,xmltvc.configureXMLTV());
		assertEquals(0, xmltvc.downloadSchedule());
	}
	

}
