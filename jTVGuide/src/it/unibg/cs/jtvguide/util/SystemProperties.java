/**
 * 
 */
package it.unibg.cs.jtvguide.util;

/**
 * @author Michele
 *
 */
public class SystemProperties{
	public static String detectOS() {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("windows") > -1) {
			/* we're in windows (unfortunately) */
			return "windows";
		}
		else {
			/* we're in UNIX */
			return "other";
		}
	}
	
	public static String getSystemLanguage() {
		return System.getProperty("user.language");
	}
	
}
