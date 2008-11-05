package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.TimeConversions;
import junit.framework.TestCase;

/**
 * A test class for time conversions
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class TimeConversionsTest extends TestCase {
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(FileUtilsTest.class);
	}
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	/**
	 * Test the conversion from milliseconds to hh:mm string
	 */
	public void testMillisecs2Time() {
		assertEquals("00h:01m", TimeConversions.millisecs2Time(31337));
	}
}
