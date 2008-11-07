package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;
import junit.framework.TestCase;

/**
 * A testclass to parse xmltv files
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class XMLTVParserImplTest extends TestCase {
XMLTVParserImpl xmltvpi;
	
	/**
	 * set various input for the parser
	 */
	public void setUp() throws Exception {
		UserPreferences.setXmltvOutputFile("examples/tv_grab.xml");
		xmltvpi = new XMLTVParserImpl();
	}

	public void tearDown() throws Exception {
		
	}
	
	/**
	 * try to parse various file, either well-formed, non existant or non xml-valid
	 */
	public void testParsing() {
		assertEquals(true, xmltvpi.parse());
		UserPreferences.setXmltvOutputFile("NON_EXISTS");
		assertEquals(false,xmltvpi.parse());
		UserPreferences.setXmltvOutputFile("examples/tv_grab-NON-VALID-XML.xml");
		assertEquals(false,xmltvpi.parse());
	}
}
