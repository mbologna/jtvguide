package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.interfaces.XMLTVGrabbersByCountry;
import it.unibg.cs.jtvguide.util.FileUtils;
import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.xmltv.DefaultPrefs;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class UserPreferencesTest extends TestCase {
	
	public void setUp() {
		if (DefaultPrefs.PREFERENCES_FILE.exists()) {
			DefaultPrefs.PREFERENCES_FILE.renameTo(new File(DefaultPrefs.PREFERENCES_FILE.toString()+".old"));
		}
		FileUtils.copy(new File("examples/.jtvguide.xml"),DefaultPrefs.PREFERENCES_FILE);
	}

	public void tearDown() {
		File f = new File(DefaultPrefs.PREFERENCES_FILE.toString()+".old");
		if (f.exists()) {
			f.renameTo(DefaultPrefs.PREFERENCES_FILE);
		}
	}
	
	public void testOptions() {
		UserPreferences.setDays(6);
		UserPreferences.setWithCache(false);
		UserPreferences.setQuiet(false);
		assertEquals(false, UserPreferences.isQuiet());
		assertEquals(false, UserPreferences.isWithCache());
		assertEquals(6, UserPreferences.getDays());
		assertEquals(new File("tv_grab.conf").getAbsolutePath(), UserPreferences.getXmltvConfigFile().toString());
		assertEquals(new File("tv_grab.xml").getAbsolutePath(), UserPreferences.getXmltvOutputFile().toString());
		assertEquals(XMLTVGrabbersByCountry.getXMLGrabbersByCountry(SystemProperties.getSystemLanguage()), UserPreferences.getXMLTVCountry());
		UserPreferences.setQuiet(true);
		UserPreferences.setWithCache(true);
		UserPreferences.setDays(100);
		assertEquals(true, UserPreferences.isQuiet());
		assertEquals(true, UserPreferences.isWithCache());
		assertEquals(100, UserPreferences.getDays());
	}
	
	public void testIO() throws IOException {
		assertEquals(true, UserPreferences.loadFromXMLFile());
		assertEquals(true, UserPreferences.saveToXMLFile());
		DefaultPrefs.PREFERENCES_FILE.delete();
		assertEquals(false, UserPreferences.loadFromXMLFile());
	}
}
