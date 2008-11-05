package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.interfaces.XMLTVGrabbersByCountry;
import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class UserPreferencesTest extends TestCase {
	public static void main(String args[]) {
		junit.textui.TestRunner.run(UserPreferencesTest.class);
	}
	
	public void setUp() {
		File f = new File(SystemProperties.getHomeDirectory()+"/.jtvguide.xml");
		if (f.exists()) {
			UserPreferences.resetXMLFile();
			f.renameTo(new File(SystemProperties.getHomeDirectory()+"/.jtvguide.xml.old"));
		}
	}

	public void tearDown() {
		UserPreferences.setQuiet(false);
		UserPreferences.setWithCache(false);
		UserPreferences.setDays(6);
		File f = new File(SystemProperties.getHomeDirectory()+"/.jtvguide.xml.old");
		if (f.exists()) f.delete();
	}
	
	public void testOptions() {
		assertEquals(false, UserPreferences.isQuiet());
		assertEquals(false, UserPreferences.isWithCache());
		assertEquals(6, UserPreferences.getDays());
		assertEquals(new File("tv_grab.conf"), UserPreferences.getXmltvConfigFile());
		assertEquals(new File("tv_grab.xml"), UserPreferences.getXmltvOutputFile());
		assertEquals(XMLTVGrabbersByCountry.getXMLGrabbersByCountry(SystemProperties.getSystemLanguage()), UserPreferences.getXMLTVCountry());
		UserPreferences.setQuiet(true);
		UserPreferences.setWithCache(true);
		UserPreferences.setDays(100);
		assertEquals(true, UserPreferences.isQuiet());
		assertEquals(true, UserPreferences.isWithCache());
		assertEquals(100, UserPreferences.getDays());
	}
	
	public void testIO() throws IOException {
		assertEquals(false, UserPreferences.loadFromXMLFile());
		assertEquals(true, UserPreferences.saveToXMLFile());
		File f = new File(SystemProperties.getHomeDirectory()+"/.jtvguide.xml.old");
		if (f.exists()) 
			f.renameTo(new File(SystemProperties.getHomeDirectory()+"/.jtvguide.xml"));
		assertEquals(true, UserPreferences.loadFromXMLFile());
	}
}
