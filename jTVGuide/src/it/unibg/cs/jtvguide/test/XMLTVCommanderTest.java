package it.unibg.cs.jtvguide.test;

import java.io.File;

import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import junit.framework.TestCase;

/**
 * A test class to test xmltv command
 * You must have XMLTV properly installed to successfully pass this test
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class XMLTVCommanderTest extends TestCase {
	XMLTVCommander xmltvc;
	
	/**
	 * prepare xmltv parameters (small test run, save time)
	 */
	public void setUp() throws Exception {
		xmltvc = new XMLTVCommander();
		UserPreferences.setDays(1);
		UserPreferences.setXmltvConfigFile(File.createTempFile("jtvguide", "test.conf").toString());
		UserPreferences.setXmltvOutputFile(File.createTempFile("jtvguide", "test.xml").toString());
	}

	/**
	 * restore xmltv parameters
	 */
	public void tearDown() throws Exception {
		UserPreferences.setXmltvConfigFile(new File("tv_grab.conf").getAbsolutePath());
		UserPreferences.setXmltvOutputFile(new File("tv_grab.xml").getAbsolutePath());
		UserPreferences.setDays(6);
		xmltvc = null;
	}
	
	/**
	 * launch xmltv commands and tests their exit value
	 */
	public void testXMLTVCommands() {
		assertEquals(0,xmltvc.configureXMLTV());
		assertEquals(0, xmltvc.downloadSchedule());
	}
	

}
