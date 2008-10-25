package it.unibg.cs.jtvguide.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestAll extends TestSuite {

	public static Test suite(){

		TestSuite suite = new TestSuite();

		suite.addTestSuite(ChannelTest.class);

		return suite;
	}

	public static void main(String args[])
	{
		suite();
	}

}
