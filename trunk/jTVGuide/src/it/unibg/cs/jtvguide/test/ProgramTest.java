package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.ProgramState;

import java.text.ParseException;
import java.util.Calendar;

import junit.framework.TestCase;

/**
 * Classe di test per la classe Program
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class ProgramTest extends TestCase {

	private Program programTest;

	/**
	 * Metodo main, si occupa di eseguire la classe
	 * @param args
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(ProgramTest.class);
	}

	/**
	 * Si istanzia un nuovo Program utilizzato nei successivi metodi
	 */
	protected void setUp() throws Exception {
		Calendar today = Calendar.getInstance();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);
		Channel channel = new Channel("www.canale5.it", "Canale5");
		programTest = new Program(today.getTime(), tomorrow.getTime(), channel, "Striscia");
	}

	/**
	 * Rimozione del Program istanziato
	 */
	protected void tearDown() throws Exception {
		programTest = null;
	}

	/**
	 * Test congiunto dei metodi getDesc() e setDesc(String) della classe Program
	 */
	public void testGetAndSetDesc() {
		programTest.setDesc("Descrizione");
		assertEquals("Descrizione", programTest.getDesc());
		assertFalse("Desc".equals(programTest.getDesc()));
	}

	/**
	 * Test del metodo compareTo della classe Program
	 */
	public void testCompareTo() {
		assertEquals(0,programTest.compareTo(programTest));
	}

	/**
	 * Test del metodo getChannel della classe Program
	 */
	public void testGetChannel() {
		assertEquals("Canale5",programTest.getChannel().getDisplayName());
		assertEquals("http://www.canale5.it",programTest.getChannel().getId().toString());
		assertFalse("Italia1".equals(programTest.getChannel().getDisplayName()));
	}

	/**
	 * Test del metodo getCompletionPercentile della classe Program
	 */
	public void testGetCompletionPercentile() {
		Calendar past = Calendar.getInstance();
		past.add(Calendar.MINUTE, -150);
		programTest.setStartDate(past.getTime());
		assertTrue(programTest.getCompletionPercentile()!=0);
		past.add(Calendar.MINUTE, 150);
		programTest.setStartDate(past.getTime());
		assertTrue(programTest.getCompletionPercentile()==0);
	}

	/**
	 * Test del metodo getInfo della classe Program
	 */
	public void testGetInfo() {
		assertEquals("On Air: (0%)",programTest.getInfo());
		Calendar past = Calendar.getInstance();
		past.add(Calendar.HOUR, -10);
		programTest.setStopDate(past.getTime());
		past.add(Calendar.HOUR, -1);
		programTest.setStartDate(past.getTime());
		assertEquals("Finished",programTest.getInfo());
	}

	/**
	 * Test del metodo getStartDate della classe Program
	 */
	public void testGetStartDate() {
		Calendar today = Calendar.getInstance();
		assertEquals(today.getTime(), programTest.getStartDate());
	}

	/**
	 * Test del metodo getStartingTime della classe Program
	 */
	public void testGetStartingTime() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MILLISECOND, 100);
		programTest.setStartDate(today.getTime());
		assertEquals(100,programTest.getStartingTime());
	}

	/**
	 * Test del metodo getState della classe Program
	 */
	public void testGetState() {
		Calendar future = Calendar.getInstance();
		Calendar past = Calendar.getInstance();
		assertEquals(ProgramState.ONAIR, programTest.getState());
		future.add(Calendar.HOUR, 10);
		programTest.setStartDate(future.getTime());
		assertEquals(ProgramState.UPCOMING, programTest.getState());
		past.add(Calendar.HOUR, -10);
		programTest.setStopDate(past.getTime());
		past.add(Calendar.HOUR, -1);
		programTest.setStartDate(past.getTime());
		assertEquals(ProgramState.FINISHED, programTest.getState());
		programTest.setStopDate(null);
		assertEquals(ProgramState.UNKNOWN, programTest.getState());
	}

	/**
	 * Test del metodo getStopDate della classe Program
	 */
	public void testGetStopDate() {
		Calendar today = Calendar.getInstance();
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);
		assertEquals(tomorrow.getTime(), programTest.getStopDate());
	}

	/**
	 * Test del metodo getTitle della classe Program
	 */
	public void testGetTitle() {
		assertEquals("Striscia",programTest.getTitle());
	}

	/**
	 * Test del metodo setChannel della classe Program
	 */
	public void testSetChannel() {
		try {
			Channel channel = new Channel("www.italia1.it", "Italia1");
			programTest.setChannel(channel);
			assertEquals(channel,programTest.getChannel());

		}
		catch (ParseException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo setStartDate della classe Program
	 */
	public void testSetStartDate() {
		Calendar future = Calendar.getInstance();
		future.add(Calendar.DATE, 2);
		programTest.setStartDate(future.getTime());
		assertEquals(future.getTime(), programTest.getStartDate());
	}

	/**
	 * Test del metodo setStopDate della classe Program
	 */
	public void testSetStopDate() {
		Calendar future = Calendar.getInstance();
		future.add(Calendar.DATE, 5);
		programTest.setStopDate(future.getTime());
		assertEquals(future.getTime(), programTest.getStopDate());
	}

	/**
	 * Test del metodo setTitle della classe Program
	 */
	public void testSetTitle() {
		String title = "Telegiornale";
		programTest.setTitle(title);
		assertEquals(title, programTest.getTitle());
	}
}
