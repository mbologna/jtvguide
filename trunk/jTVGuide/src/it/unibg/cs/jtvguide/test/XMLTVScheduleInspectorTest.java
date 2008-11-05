package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.DateFormatter;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVScheduleInspector;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class XMLTVScheduleInspectorTest extends TestCase {
	XMLTVScheduleInspector xmltvsi;
	
	public void setUp() throws Exception {
		xmltvsi = new XMLTVScheduleInspector();
		File f1 = new File("examples/tv_grab_update.xml");
		FileWriter fw = new FileWriter(f1);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		fw.write("<programme start=\"" + DateFormatter.formatDate(new Date()));
		fw.write("<programme start=\"" + DateFormatter.formatDate(c.getTime()));
		fw.close();
		UserPreferences.setXmltvConfigFile("examples/tv_grab.conf");
		UserPreferences.setXmltvOutputFile(f1.toString());	
	}

	public void tearDown() throws Exception {
		xmltvsi = null;
	}
	
	@SuppressWarnings("deprecation")
	public void testIsUpdate() {	
		assertEquals(true, xmltvsi.isUpToDate());
		assertEquals(false, xmltvsi.isUpToDate(new Date(111,9,19)));
		UserPreferences.setXmltvOutputFile("NON_EXIST");
		assertEquals(false, xmltvsi.isUpToDate());
		assertEquals(false, xmltvsi.isUpToDate(new Date(111,9,19)));
		UserPreferences.setXmltvOutputFile("examples/tv_grab.xml");
		assertEquals(false, xmltvsi.isUpToDate());
		assertEquals(false, xmltvsi.isUpToDate(new Date(111,9,19)));
	}
}
