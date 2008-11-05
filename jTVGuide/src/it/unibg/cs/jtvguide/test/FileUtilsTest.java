package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.FileUtils;

import java.io.File;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class FileUtilsTest extends TestCase{
	
	public static void main(String args[]) {
		junit.textui.TestRunner.run(FileUtilsTest.class);
	}
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testUncommentedLinesCount() {
		assertEquals(2, FileUtils.uncommentedLinesCount(new File("examples/tv_grab.conf")));
		assertEquals(0, FileUtils.uncommentedLinesCount(new File("examples/tv_grab2.conf")));
		assertEquals(-1, FileUtils.uncommentedLinesCount(new File("examples/NON_EXIST")));
	}
	
	public void testGrep() {
		assertEquals(true, FileUtils.grep(new File("examples/tv_grab.conf"), Pattern.compile("canale")));
		assertEquals(false, FileUtils.grep(new File("examples/tv_grab.conf"), Pattern.compile("NON_EXIST")));
		assertEquals(false, FileUtils.grep(new File("examples/NON_EXIST"), Pattern.compile("canale")));
	}
}
