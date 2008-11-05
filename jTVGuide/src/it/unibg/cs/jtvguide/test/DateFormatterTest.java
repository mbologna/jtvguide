package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.DateFormatter;

import java.util.Date;

import junit.framework.TestCase;

/**
 * A class to test the date converter
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class DateFormatterTest extends TestCase{
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(FileUtilsTest.class);
	}
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Tests date-to-string method
	 */
	public void testFormatDate() {
		assertEquals("20080918", DateFormatter.formatDate(new Date(108,8,18)));
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Tests time-to-string method
	 */
	public void testFormatDate2Time() {
		assertEquals("[15:00]", DateFormatter.formatDate2Time(new Date(108,8,18,15,00)));
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Tests date-to-string (date+time) method
	 */
	public void testFormatDate2TimeWithDay() {
		assertEquals("18/09 [15:00]", DateFormatter.formatDate2TimeWithDay(new Date(108,8,18,15,00)));
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Tests string-to-date method
	 */
	public void testFormatString() {
		assertEquals(new Date(108,8,18,15,00,00), DateFormatter.formatString("200809181500"));
	}
}
