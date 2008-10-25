package it.unibg.cs.jtvguide.model;

import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.TestCase;

/**
 * Classe di test per la classe ChannelMap
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class ChannelMapTest extends TestCase {

	private ChannelMap channelMap;

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
		channelMap = new ChannelMap();
	}

	/**
	 * Rimozione del Channel istanziato
	 */
	public void tearDown() throws Exception {
		channelMap = null;
	}

	/**
	 * Test congiunto dei metodi add(String, Channel) e contains(String) della classe ChannelMap
	 */
	public void testAddAndContainsStringChannel() {
		Channel c = new Channel("www.canale5.it", "Canale5");
		String id = "www.canale5.it";
		channelMap.add(id, c);
		assertTrue(channelMap.contains(id));
		assertFalse(channelMap.contains("Rete4"));
	}

	/**
	 * Test congiunto dei metodi add(URI, Channel) e contains(URI) della classe ChannelMap
	 */
	public void testAddAndContainsURIChannel() {
		Channel c = new Channel("www.italia1.it", "Italia1");
		try{
			URI uri = new URI("http://www.italia1.it");
			channelMap.add(uri, c);
			assertTrue(channelMap.contains(uri));
			assertFalse(channelMap.contains(new URI("http://www.rete4.it")));
		}
		catch (URISyntaxException e) {
			fail("Exception");
		}
	}

	/**
	 * Test del metodo get(String) della classe ChannelMap
	 */
	public void testGetString() {
		Channel c = new Channel("www.italia1.it", "Italia1");
		String id = "www.italia1.it";
		channelMap.add(id, c);
		assertTrue((channelMap.get("www.italia1.it").toString()).equals("Italia1"));
	}

	/**
	 * Test del metodo get(URI) della classe ChannelMap
	 */
	public void testGetURI() {
		Channel c = new Channel("www.italia1.it", "Italia1");
		try{
			URI uri = new URI("http://www.italia1.it");
			channelMap.add(uri, c);
			assertTrue((channelMap.get(uri).toString()).equals("Italia1"));
		}
		catch (URISyntaxException e) {
			fail("Exception");
		}
	}
}
