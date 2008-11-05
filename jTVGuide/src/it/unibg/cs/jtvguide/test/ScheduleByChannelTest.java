package it.unibg.cs.jtvguide.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.ScheduleByChannel;
import junit.framework.TestCase;

/**
 * Classe di test per la classe ScheduleByChannel
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class ScheduleByChannelTest extends TestCase {

	private ScheduleByChannel scheduleByChannelTest;
	private Channel channel;
	/**
	 * Metodo main, si occupa di eseguire la classe
	 * @param args
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(ChannelTest.class);
	}

	/**
	 * Si istanzia un nuovo ScheduleByChannel utilizzato nei successivi metodi
	 */
	protected void setUp() throws Exception {
		Calendar aDay = Calendar.getInstance();
		channel = new Channel("www.rai2.it", "Rai2");
		Program programTest = new Program(aDay.getTime(), aDay.getTime(), channel, "Pubblicità");
		aDay.add(Calendar.HOUR, 1);
		Program programTest2 = new Program(aDay.getTime(), aDay.getTime(), channel, "Dottori");
		List<Program> scheduleList = new ArrayList<Program>();
		scheduleList.add(programTest);
		scheduleList.add(programTest2);
		scheduleByChannelTest = new ScheduleByChannel(scheduleList, channel);
	}

	/**
	 * Rimozione dello ScheduleByChannel istanziato
	 */
	protected void tearDown() throws Exception {
		scheduleByChannelTest = null;
	}

	/**
	 * Test del metodo add della classe ScheduleByChannel
	 */
	public void testAdd() {
		Calendar genericDay = Calendar.getInstance();
		genericDay.add(Calendar.DATE, 13);
		Program programTest = new Program(genericDay.getTime(), genericDay.getTime(), channel, "Ammalati");
		scheduleByChannelTest.add(programTest);
		assertTrue(scheduleByChannelTest.getProgramsByName("Ammalati").get(0).equals(programTest));
	}

	/**
	 * Test del metodo add della classe ScheduleByChannel
	 */
	public void testGetChannelName() {
		assertTrue(scheduleByChannelTest.getChannelName().equals("Rai2"));
		assertFalse(scheduleByChannelTest.getChannelName().equals("Rai3"));
	}

}
