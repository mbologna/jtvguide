package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.RunExternalCommand;
import junit.framework.TestCase;

public class RunExternalCommandTest extends TestCase {
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testCommand() {
		assertEquals(0, RunExternalCommand.runCommand("java -version"));
	}
}
