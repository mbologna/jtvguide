package it.unibg.cs.jtvguide.test;

import it.unibg.cs.jtvguide.util.FileUtils;
import it.unibg.cs.jtvguide.xmltv.DefaultPrefs;
import it.unibg.cs.jtvguide.xmltv.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.UserPreferences;

import java.io.File;

import junit.framework.TestCase;

/**
 * A testclass for md5 functions
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class MD5ChecksumTest extends TestCase{
	
	public void setUp() throws Exception {
		if (DefaultPrefs.CONFIG_FILE_MD5.exists()) {
			DefaultPrefs.CONFIG_FILE_MD5.renameTo(new File(DefaultPrefs.CONFIG_FILE_MD5.toString()+".old"));
		}
		FileUtils.copy(new File("examples/tv_grab.conf.md5"),DefaultPrefs.CONFIG_FILE_MD5);
	}

	public void tearDown() throws Exception {
		File f = new File(DefaultPrefs.CONFIG_FILE_MD5.toString()+".old");
		if (f.exists()) {
			f.renameTo(DefaultPrefs.CONFIG_FILE_MD5);
		}
	}
	
	/**
	 * Calculate the md5 of the file specified as first argument and compare it to the second argument
	 */
	public void testCheckMD5() {
		assertEquals(false, MD5Checksum.checkMD5("examples/tv_grab.conf.md5", "foo"));
		assertEquals(true, MD5Checksum.checkMD5("examples/tv_grab.conf", "69fdca9d7184eb0fa9c229ca8a911940"));
		assertEquals(false, MD5Checksum.checkMD5("NON_EXIST", "7d5d3c1f65a4662db21723bc2c34892a"));
	}
	
	/**
	 * Calculate the md5sum of a file
	 */
	public void testGetMD5Checksum() {
		UserPreferences.setXmltvConfigFile("examples/tv_grab.conf");
		assertEquals("69fdca9d7184eb0fa9c229ca8a911940", MD5Checksum.getMD5Checksum("examples/tv_grab.conf"));
		assertEquals("69fdca9d7184eb0fa9c229ca8a911940", MD5Checksum.readMD5FromFile());
	}
	
	/**
	 * Calculate an md5 and store it in a file
	 */
	public void testWriteMD5ToFile() {
		UserPreferences.setXmltvConfigFile("examples/tv_grab.conf");
		assertEquals(true, MD5Checksum.writeMD5ToFile(new File("examples/tv_grab.conf")));
	}
	
}
