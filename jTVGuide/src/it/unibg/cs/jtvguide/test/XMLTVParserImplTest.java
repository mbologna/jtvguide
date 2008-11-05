package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;
import junit.framework.TestCase;

public class XMLTVParserImplTest extends TestCase {
XMLTVParserImpl xmltvpi;
	
	public void setUp() throws Exception {
		UserPreferences.setXmltvOutputFile("examples/tv_grab.xml");
		xmltvpi = new XMLTVParserImpl();
	}

	public void tearDown() throws Exception {
		
	}
	
	public void testParsing() {
		assertEquals(true, xmltvpi.parse());
		UserPreferences.setXmltvOutputFile("NON_EXISTS");
		assertEquals(false,xmltvpi.parse());
		UserPreferences.setXmltvOutputFile("examples/tv_grab-NON-VALID-XML.xml");
		assertEquals(false,xmltvpi.parse());
	}
}
