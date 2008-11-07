package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.RunExternalCommand;
import junit.framework.TestCase;

/**
 * A testclass to execute external program
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class RunExternalCommandTest extends TestCase {
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	/**
	 * Launch an external program and check its exit value
	 */
	public void testCommand() {
		assertEquals(0, RunExternalCommand.runCommand("java -version"));
	}
}
