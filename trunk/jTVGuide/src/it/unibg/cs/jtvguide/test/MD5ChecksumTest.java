package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.xmltv.MD5Checksum;

import java.io.File;

import junit.framework.TestCase;

public class MD5ChecksumTest extends TestCase{
	public static void main(String args[]) {
		junit.textui.TestRunner.run(FileUtilsTest.class);
	}
	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testCheckMD5() {
		assertEquals(false, MD5Checksum.checkMD5("examples/tv_grab.conf.md5", "foo"));
		assertEquals(true, MD5Checksum.checkMD5("examples/tv_grab.conf", "7d5d3c1f65a4662db21723bc2c34892a"));
		assertEquals(false, MD5Checksum.checkMD5("NON_EXIST", "7d5d3c1f65a4662db21723bc2c34892a"));
	}
	
	public void testGetMD5Checksum() {
		System.out.println(MD5Checksum.getMD5Checksum("examples/tv_grab.conf"));
		assertEquals("7d5d3c1f65a4662db21723bc2c34892a", MD5Checksum.getMD5Checksum("examples/tv_grab.conf"));
	}
	
	public void testReadMD5FromFile() {
		assertEquals("7d5d3c1f65a4662db21723bc2c34892a", MD5Checksum.readMD5FromFile());
	}
	
	public void testWriteMD5ToFile() {
		assertEquals(true, MD5Checksum.writeMD5ToFile(new File("examples/tv_grab.conf")));
	}
	
}
