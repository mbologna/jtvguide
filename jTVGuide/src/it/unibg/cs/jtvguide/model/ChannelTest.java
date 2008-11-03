package it.unibg.cs.jtvguide.model;

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

	private Channel test;

	/**
	 * Metodo main, si occupa di eseguire la classe
	 * @param args
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(ChannelTest.class);
	}

	/**
	 * Si istanzia un nuovo Channel utilizzato nei successivi metodi
	 */
	public void setUp() throws Exception {
		test = new Channel("www.canale5.it", "Canale5");
	}

	/**
	 * Rimozione del Channel istanziato
	 */
	public void tearDown() throws Exception {
		test = null;
	}

	/**
	 * Test del metodo compareTo della classe Channel
	 */
	public void testCompareTo() {
		try {
			assertEquals(0,test.compareTo(new Channel("www.canale5.it", "Canale5")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test del metodo equals della classe Channel
	 */
	public void testEquals() {
		Object o = test;
		Object obj = null;
		try {
			obj = new Channel("www.italia1.it", "Italia1");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(test.equals(o));
		assertFalse(test.equals(obj));
	}

	/**
	 * Test del metodo getDisplayName della classe Channel
	 */
	public void testGetDisplayName() {
		assertEquals("Canale5", test.getDisplayName());
	}

	/**
	 * Test del metodo getId della classe Channel
	 */
	public void testGetId() {
		assertEquals("http://www.canale5.it", test.getId().toString());
	}

	/**
	 * Test del metodo setDisplayName della classe Channel
	 */
	public void testSetDisplayName() {
		test.setDisplayName("Italia1");
		assertEquals("Italia1",test.getDisplayName());
	}

	/**
	 * Test del metodo setId della classe Channel
	 */
	public void testSetId() {
		try{
			test.setId(new URI("http://www.italia1.it"));
			assertEquals("http://www.italia1.it", test.getId().toString());
		}
		catch(URISyntaxException e){
			fail("Exception");
		}
	}

	/**
	 * Test del metodo toString della classe Channel
	 */
	public void testToString() {
		assertEquals("Canale5", test.toString());
	}

}
