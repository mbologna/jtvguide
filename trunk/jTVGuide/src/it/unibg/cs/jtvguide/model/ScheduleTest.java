package it.unibg.cs.jtvguide.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

/**
 * Classe di test per la classe Schedule
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class ScheduleTest extends TestCase {

	private Schedule scheduleTest;
	private Schedule scheduleTest2;

	/**
	 * Metodo main, si occupa di eseguire la classe
	 * @param args
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(ScheduleTest.class);
	}

	/**
	 * Si istanziano due nuovi Schedule utilizzati nei successivi metodi
	 */
	protected void setUp() throws Exception {
		ChannelMap channelMap = new ChannelMap();
		scheduleTest = new Schedule(channelMap);
		List<Program> scheduleList = new ArrayList<Program>();
		scheduleTest2 = new Schedule(scheduleList);

	}

	/**
	 * Rimozione degli Schedule istanziati
	 */
	protected void tearDown() throws Exception {
		scheduleTest = null;
		scheduleTest2 = null;
	}

	/**
	 * Test del metodo add della classe Schedule
	 */
	public void testAdd() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar tomorrow = Calendar.getInstance();
			tomorrow.add(Calendar.DATE, 1);
			Channel channel = new Channel("www.canale5.it", "Canale5");
			Program programTest = new Program(today.getTime(), tomorrow.getTime(), channel, "Striscia");
			scheduleTest.add(programTest);
			scheduleTest2.add(programTest);
			assertTrue(scheduleTest.scheduleList.contains(programTest));
			assertTrue(scheduleTest2.scheduleList.contains(programTest));
		}
		catch (ParseException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo getOnAirPrograms della classe Schedule
	 */
	public void testGetOnAirPrograms() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar tomorrow = Calendar.getInstance();
			Calendar future = Calendar.getInstance();
			tomorrow.add(Calendar.DATE, 1);
			future.add(Calendar.DATE, 2);
			Channel channel = new Channel("www.italia1.it", "Italia1");
			Program programTest = new Program(today.getTime(), tomorrow.getTime(), channel, "Controcampo");
			Program programTest2 = new Program(future.getTime(), future.getTime(), channel, "Champions");
			scheduleTest.add(programTest);
			scheduleTest.add(programTest2);
			assertTrue(scheduleTest.getOnAirPrograms().contains(programTest));
			assertFalse(scheduleTest.getOnAirPrograms().contains(programTest2));
		}
		catch (ParseException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo getProgramsByName della classe Schedule
	 */
	public void testGetProgramsByName() {
		try {
			Calendar aDay = Calendar.getInstance();
			Channel channel = new Channel("www.rete4.it", "Rete4");
			Program programTest = new Program(aDay.getTime(), aDay.getTime(), channel, "Studio Aperto");
			scheduleTest.add(programTest);
			assertTrue(scheduleTest.getProgramsByName("Studio").contains(programTest));
			assertFalse(scheduleTest.getProgramsByName("Formula").contains(programTest));
		}
		catch (ParseException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo getProgramsFromDateToDate della classe Schedule
	 */
	public void testGetProgramsFromDateToDate() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar tomorrow = Calendar.getInstance();
			Calendar dayAfterTomorrow = Calendar.getInstance();
			tomorrow.add(Calendar.DATE, 1);
			dayAfterTomorrow.add(Calendar.DATE, 2);
			Channel channel = new Channel("www.canale5.it", "Canale5");
			Program programTest = new Program(today.getTime(), tomorrow.getTime(), channel, "Striscia");
			Program programTest2 = new Program(dayAfterTomorrow.getTime(), dayAfterTomorrow.getTime(), channel, "Zelig");
			scheduleTest.add(programTest);
			scheduleTest.add(programTest2);
			assertTrue(scheduleTest.getProgramsFromDateToDate(today.getTime(), tomorrow.getTime()).contains(programTest));
			assertFalse(scheduleTest.getProgramsFromDateToDate(today.getTime(), tomorrow.getTime()).contains(programTest2));
		}
		catch (ParseException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo getSchedulesByChannel della classe Schedule
	 */
	public void testGetSchedulesByChannel() {
		try {
			Calendar scheduleDay = Calendar.getInstance();
			Channel channel = new Channel("www.rai1.it", "Rai1");
			Program programTest = new Program(scheduleDay.getTime(), scheduleDay.getTime(), channel, "La Domenica Sportiva");
			scheduleDay.add(Calendar.HOUR, 1);
			Program programTest2 = new Program(scheduleDay.getTime(), scheduleDay.getTime(), channel, "Uno Mattina");
			scheduleTest.add(programTest);
			scheduleTest.add(programTest2);
			scheduleTest.channelMap.add("www.rai1.it", channel);
			ScheduleByChannel scheduleByChannelTest = new ScheduleByChannel(scheduleTest.scheduleList, channel);
			assertEquals(scheduleTest.getSchedulesByChannel().get(0).getChannelName(), scheduleByChannelTest.getChannelName());
			assertEquals(scheduleTest.getSchedulesByChannel().get(0).scheduleList, scheduleByChannelTest.scheduleList);
		}
		catch (ParseException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo getUpcomingPrograms della classe Schedule
	 */
	public void testGetUpcomingPrograms() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar todayFiveMinutesLater = Calendar.getInstance();
			Calendar todayTenMinutesLater = Calendar.getInstance();
			todayFiveMinutesLater.add(Calendar.MINUTE, 5);
			todayTenMinutesLater.add(Calendar.MINUTE, 10);
			Channel channel = new Channel("www.rai1.it", "Rai1");
			Program programTest = new Program(today.getTime(), todayFiveMinutesLater.getTime(), channel, "La Domenica Sportiva");
			today.add(Calendar.HOUR, 1);
			Program programTest2 = new Program(todayFiveMinutesLater.getTime(), todayTenMinutesLater.getTime(), channel, "Uno Mattina");
			scheduleTest.add(programTest);
			scheduleTest.add(programTest2);
			assertTrue(scheduleTest.getUpcomingPrograms().contains(programTest2));
			assertFalse(scheduleTest.getUpcomingPrograms().contains(programTest));
		}
		catch (ParseException e) {
			fail("Exception");
		}
	}
}
