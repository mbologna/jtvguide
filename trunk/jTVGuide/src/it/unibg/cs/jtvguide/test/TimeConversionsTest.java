package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.TimeConversions;
import junit.framework.TestCase;

public class TimeConversionsTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(FileUtilsTest.class);
	}
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testMillisecs2Time() {
		assertEquals("00h:01m", TimeConversions.millisecs2Time(31337));
	}
}
