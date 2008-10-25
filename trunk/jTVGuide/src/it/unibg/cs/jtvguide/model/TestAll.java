package it.unibg.cs.jtvguide.model;

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
	 * @return
	 */
	public static Test suite(){

		TestSuite suite = new TestSuite();

		suite.addTestSuite(ChannelTest.class);

		suite.addTestSuite(ChannelMapTest.class);

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
