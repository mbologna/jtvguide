/**
 * 
 */
package it.unibg.cs.jtvguide.util;

/**
 * @author Michele
 *
 */
public class OSType{
	
	/* magari il tipo ritornato è un Enum? */
	public static String getOS() {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("windows") > -1) {
			/* siamo in Windows */
			return "windows";
		}
		else {
			/* UNIX e derivati */
			return "other";
		}
	}
	
}
