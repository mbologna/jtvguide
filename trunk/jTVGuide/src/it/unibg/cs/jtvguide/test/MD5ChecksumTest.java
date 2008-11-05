package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.xmltv.MD5Checksum;

import java.io.File;

import junit.framework.TestCase;

/**
 * A testclass for md5 functions
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class MD5ChecksumTest extends TestCase{
	public static void main(String args[]) {
		junit.textui.TestRunner.run(FileUtilsTest.class);
	}
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	/**
	 * Calculate the md5 of the file specified as first argument and compare it to the second argument
	 */
	public void testCheckMD5() {
		assertEquals(false, MD5Checksum.checkMD5("examples/tv_grab.conf.md5", "foo"));
		assertEquals(true, MD5Checksum.checkMD5("examples/tv_grab.conf", "7d5d3c1f65a4662db21723bc2c34892a"));
		assertEquals(false, MD5Checksum.checkMD5("NON_EXIST", "7d5d3c1f65a4662db21723bc2c34892a"));
	}
	
	/**
	 * Calculate the md5sum of a file
	 */
	public void testGetMD5Checksum() {
		assertEquals("7d5d3c1f65a4662db21723bc2c34892a", MD5Checksum.getMD5Checksum("examples/tv_grab.conf"));
	}
	
	/**
	 * Read a md5 stored in a file
	 */
	public void testReadMD5FromFile() {
		assertEquals("7d5d3c1f65a4662db21723bc2c34892a", MD5Checksum.readMD5FromFile());
	}
	
	/**
	 * Calculate an md5 and store it in a file
	 */
	public void testWriteMD5ToFile() {
		assertEquals(true, MD5Checksum.writeMD5ToFile(new File("examples/tv_grab.conf")));
	}
	
}
