package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.log.PublicLogger;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import junit.framework.TestCase;

/**
 * Classe di test per la classe ChannelMap
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class ChannelMapTest extends TestCase {

	private ChannelMap channelMapTest;

	/**
	 * Metodo main, si occupa di eseguire la classe
	 * @param args
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(ChannelMapTest.class);
	}

	/**
	 * Si istanzia un nuovo ChannelMap utilizzato nei successivi metodi
	 */
	public void setUp() throws Exception {
		channelMapTest = new ChannelMap();
	}

	/**
	 * Rimozione del Channel istanziato
	 */
	public void tearDown() throws Exception {
		channelMapTest = null;
	}

	/**
	 * Test congiunto dei metodi add(String, Channel) e contains(String) della classe ChannelMap
	 */
	public void testAddAndContainsStringChannel() {
		Channel c = null;
		try {
			c = new Channel("www.canale5.it", "Canale5");
		} catch (ParseException e) {
			PublicLogger.getLogger().error(e);
		}
		String id = "www.canale5.it";
		channelMapTest.add(id, c);
		assertTrue(channelMapTest.contains(id));
		assertFalse(channelMapTest.contains("Rete4"));
	}

	/**
	 * Test congiunto dei metodi add(URI, Channel) e contains(URI) della classe ChannelMap
	 */
	public void testAddAndContainsURIChannel() {
		Channel c = null;
		try {
			c = new Channel("www.italia1.it", "Italia1");
		} catch (ParseException e1) {
			PublicLogger.getLogger().error(e1);
		}
		try{
			URI uri = new URI("http://www.italia1.it");
			channelMapTest.add(uri, c);
			assertTrue(channelMapTest.contains(uri));
			assertFalse(channelMapTest.contains(new URI("http://www.rete4.it")));
		}
		catch (URISyntaxException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo get(String) della classe ChannelMap
	 */
	public void testGetString() {
		Channel c = null;
		try {
			c = new Channel("www.italia1.it", "Italia1");
		} catch (ParseException e) {
			PublicLogger.getLogger().error(e);
		}
		String id = "www.italia1.it";
		channelMapTest.add(id, c);
		assertTrue((channelMapTest.get("www.italia1.it").toString()).equals("Italia1"));
	}

	/**
	 * Test del metodo get(URI) della classe ChannelMap
	 */
	public void testGetURI() {
		Channel c = null;
		try {
			c = new Channel("www.italia1.it", "Italia1");
		} catch (ParseException e1) {
			PublicLogger.getLogger().error(e1);
		}
		try{
			URI uri = new URI("http://www.italia1.it");
			channelMapTest.add(uri, c);
			assertTrue((channelMapTest.get(uri).toString()).equals("Italia1"));
		}
		catch (URISyntaxException e) {
			fail("Exception");
		}
	}
}
