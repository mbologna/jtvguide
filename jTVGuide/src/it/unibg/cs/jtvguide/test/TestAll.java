package it.unibg.cs.jtvguide.test;

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
	 */
	public static Test suite(){

		TestSuite suite = new TestSuite();

		suite.addTestSuite(ChannelTest.class);

		suite.addTestSuite(ChannelMapTest.class);

		suite.addTestSuite(ProgramTest.class);

		return suite;
	}

	/**
	 * Il metodo si occupa di eseguire la suite di test
	 *
	 * @param args
	 */
	public static void main(String args[])
	{
		suite();
	}

}