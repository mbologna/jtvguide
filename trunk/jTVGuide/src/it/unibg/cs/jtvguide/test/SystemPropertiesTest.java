package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.SystemProperties;
import junit.framework.TestCase;


public class SystemPropertiesTest extends TestCase{
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testDetect() {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("windows") != -1) {
			assertTrue(SystemProperties.detectOS() == "windows");
		}
		else {
			assertTrue(SystemProperties.detectOS() == "other");
		}
	}
}
