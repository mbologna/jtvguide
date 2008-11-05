/**
 * 
 */
package it.unibg.cs.jtvguide.util;

/**
 * Detect OS property and version
 * @author Michele Bologna, Sebastiano Rota
 * 
 */
public final class SystemProperties {
	
	private SystemProperties() { }
	/**
	 * Detect the OS we're running in
	 * @return a string matching the OS
	 */
	public static String detectOS() {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("windows") > -1) {
			/* we're in windows (unfortunately) */
			return "windows";
		} else {
			/* we're in UNIX */
			return "other";
		}
	}

	/**
	 *
	 * @return the OS file separator (e.g. '\' in Windows, '/' in *UNIX)
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}
	
	/**
	 * 
	 * @return the user's home dir
	 */

	public static String getHomeDirectory() {
		return System.getProperty("user.home");
	}

	/**
	 * 
	 * @return the user's OS language
	 */
	public static String getSystemLanguage() {
		return System.getProperty("user.language");
	}

}
