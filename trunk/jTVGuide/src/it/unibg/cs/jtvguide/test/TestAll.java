package it.unibg.cs.jtvguide.test;

import java.io.IOException;

import it.unibg.cs.jtvguide.xmltv.UserPreferences;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Classe che si occupa di gestire la suite di test
 *
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class TestAll extends TestSuite {

	/**
	 * Il metodo si occupa di aggiungere le classi di test alla suite
	 *
	 * @return la suite di test
	 * @throws IOException 
	 */
	public static Test suite() throws IOException{

		TestSuite suite = new TestSuite();

		suite.addTestSuite(ChannelTest.class);

		suite.addTestSuite(ChannelMapTest.class);

		suite.addTestSuite(ProgramTest.class);

		suite.addTestSuite(DateFormatterTest.class);

		suite.addTestSuite(FileUtilsTest.class);
		
		UserPreferences.resetXMLFile();

		suite.addTestSuite(MD5ChecksumTest.class);
		
		UserPreferences.resetXMLFile();

		suite.addTestSuite(ScheduleTest.class);

		suite.addTestSuite(ScheduleByChannelTest.class);
		
		suite.addTestSuite(RunExternalCommandTest.class);
		
		suite.addTestSuite(SystemPropertiesTest.class);
		
		suite.addTestSuite(TimeConversionsTest.class);
		
		suite.addTestSuite(XMLTVCommanderTest.class);
		
		UserPreferences.resetXMLFile();
	
		suite.addTestSuite(UserPreferencesTest.class);
		
		UserPreferences.resetXMLFile();
		
		suite.addTestSuite(XMLTVScheduleInspectorTest.class);
		
		UserPreferences.resetXMLFile();
		
		suite.addTestSuite(XMLTVParserImplTest.class);
		
		UserPreferences.resetXMLFile();
	
		return suite;
	}

	/**
	 * Il metodo si occupa di eseguire la suite di test
	 *
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException
	{
		suite();
	}

}
