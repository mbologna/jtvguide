package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.ChannelMap;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.ScheduleByChannel;

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

	/**
	 * Metodo main, si occupa di eseguire la classe
	 * @param args
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(ScheduleTest.class);
	}

	/**
	 * Si istanzia il nuovo Schedule utilizzato nei successivi metodi
	 */
	protected void setUp() throws Exception {
		ChannelMap channelMap = new ChannelMap();
		scheduleTest = new Schedule(channelMap);
	}

	/**
	 * Rimozione dello Schedule istanziato
	 */
	protected void tearDown() throws Exception {
		scheduleTest = null;
	}

	/**
	 * Test congiunto dei metodi add e getPrograms della classe Schedule
	 */
	public void testAdd() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar tomorrow = Calendar.getInstance();
			tomorrow.add(Calendar.DATE, 1);
			Channel channel = new Channel("www.canale5.it", "Canale5");
			Program programTest = new Program(today.getTime(), tomorrow.getTime(), channel, "Striscia");
			scheduleTest.add(programTest);
			assertTrue(scheduleTest.getPrograms(today.getTime()).contains(programTest));
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
			ChannelMap channelMapTest = new ChannelMap();
			Calendar scheduleDay = Calendar.getInstance();
			Channel channel = new Channel("www.rai1.it", "Rai1");
			channelMapTest.add("www.rai1.it", channel);
			scheduleTest = new Schedule(channelMapTest);
			Program programTest = new Program(scheduleDay.getTime(), scheduleDay.getTime(), channel, "La Domenica Sportiva");
			scheduleDay.add(Calendar.HOUR, 1);
			Program programTest2 = new Program(scheduleDay.getTime(), scheduleDay.getTime(), channel, "Uno Mattina");
			scheduleTest.add(programTest);
			scheduleTest.add(programTest2);
			List<Program> scheduleList = new ArrayList<Program>();
			scheduleList.add(programTest);
			scheduleList.add(programTest2);
			ScheduleByChannel scheduleByChannelTest = new ScheduleByChannel(scheduleList, channel);
            assertEquals(scheduleTest.getSchedulesByChannel().get(0).getChannelName(), scheduleByChannelTest.getChannelName());
            assertEquals(scheduleTest.getSchedulesByChannel().get(0).getProgramsFromNowOn(), scheduleByChannelTest.getProgramsFromNowOn());
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
