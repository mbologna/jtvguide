package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.ChannelMap;

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
    	try {
    		Channel c = new Channel("www.canale5.it", "Canale5");
        	String id = "www.canale5.it";
            channelMapTest.add(id, c);
            assertTrue(channelMapTest.contains(id));
            assertFalse(channelMapTest.contains("Rete4"));
        }
    	catch (ParseException e) {
    		fail("Exception");
        }

    }

    /**
     * Test congiunto dei metodi add(URI, Channel) e contains(URI) della classe ChannelMap
     */
    public void testAddAndContainsURIChannel() {
    	try {
    		Channel c = new Channel("www.italia1.it", "Italia1");
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
    	catch (ParseException e1) {
    		fail("Exception");
        }
    }

    /**
     * Test del metodo get(String) della classe ChannelMap
     */
    public void testGetString() {
    	try {
    		Channel c = new Channel("www.italia1.it", "Italia1");
    		String id = "www.italia1.it";
            channelMapTest.add(id, c);
            assertTrue((channelMapTest.get("www.italia1.it").toString()).equals("Italia1"));

    	}
    	catch (ParseException e) {
    		fail("Exception");
    	}
    }

    /**
     * Test del metodo get(URI) della classe ChannelMap
     */
    public void testGetURI() {
    	try {
    		Channel c = new Channel("www.italia1.it", "Italia1");
    		try{
    			URI uri = new URI("http://www.italia1.it");
                channelMapTest.add(uri, c);
                assertTrue((channelMapTest.get(uri).toString()).equals("Italia1"));
    		}
    		catch (URISyntaxException e) {
                fail("Exception");
    		}
        }
    	catch (ParseException e1) {
    		fail("Exception");
        }
    }
}

