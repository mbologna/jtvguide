package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.log.PublicLogger;
import it.unibg.cs.jtvguide.model.Channel;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import junit.framework.TestCase;

/**
 * Classe di test per la classe Channel
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class ChannelTest extends TestCase {

	private Channel channelTest;

	/**
	 * Si istanzia un nuovo Channel utilizzato nei successivi metodi
	 */
	public void setUp() throws Exception {
		channelTest = new Channel("www.canale5.it", "Canale5");
	}

	/**
	 * Rimozione del Channel istanziato
	 */
	public void tearDown() throws Exception {
		channelTest = null;
	}

	/**
	 * Test del metodo compareTo della classe Channel
	 */
	public void testCompareTo() {
		try {
			assertEquals(0,channelTest.compareTo(new Channel("www.canale5.it", "Canale5")));
		} catch (ParseException e) {
			PublicLogger.getLogger().error(e);
		}
	}

	/**
	 * Test del metodo equals della classe Channel
	 */
	public void testEquals() {
		Object o = channelTest;
		Object obj = null;
		try {
			obj = new Channel("www.italia1.it", "Italia1");
		} catch (ParseException e) {
			PublicLogger.getLogger().error(e);
		}
		assertTrue(channelTest.equals(o));
		assertFalse(channelTest.equals(obj));
	}

	/**
	 * Test del metodo getDisplayName della classe Channel
	 */
	public void testGetDisplayName() {
		assertEquals("Canale5", channelTest.getDisplayName());
	}

	/**
	 * Test del metodo getId della classe Channel
	 */
	public void testGetId() {
		assertEquals("http://www.canale5.it", channelTest.getId().toString());
	}

	/**
	 * Test del metodo setDisplayName della classe Channel
	 */
	public void testSetDisplayName() {
		channelTest.setDisplayName("Italia1");
		assertEquals("Italia1",channelTest.getDisplayName());
	}

	/**
	 * Test del metodo setId della classe Channel
	 */
	public void testSetId() {
		try{
			channelTest.setId(new URI("http://www.italia1.it"));
			assertEquals("http://www.italia1.it", channelTest.getId().toString());
		}
		catch(URISyntaxException e){
			fail("Exception");
		}
	}

	/**
	 * Test del metodo toString della classe Channel
	 */
	public void testToString() {
		assertEquals("Canale5", channelTest.toString());
	}

}
