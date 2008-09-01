package it.unibg.cs.jtvguide.xmltv;

import java.io.File;


/**
 * @author Michele
 *
 */
public class UserPreferences {
	private static int days = 1;
	private static boolean withCache = false;
	private static boolean withSlow = false;
	private static boolean withCacheSlow = false;
	private static boolean verbose = false;
	private static boolean quiet = false;
	private static File xmltvConfigFile = null;
	private static File xmltvOutputFile = null;
	private static String locale = null;

	
	public static void setCountry(String l) {
		for (XMLTVGrabbersByCountry element : XMLTVGrabbersByCountry.values()) {
			if (!element.getLOCALE().equals(l)) {
				continue;
			}
			else {
				locale = l;
			}
		}
	}
	
	public static String getXMLTVCommandByCountry() {
		for (XMLTVGrabbersByCountry element : XMLTVGrabbersByCountry.values()) {
			if(element.getLOCALE().equals(locale)) {
				return element.getCOMMAND();
			}
			else {
				continue;
			}
		}
		throw new RuntimeException("locale not found");
	}
	
	public static String getOptions() {
		String options = new String();
		options += "--days " + getDays();
		options += " --config-file " + getXmltvConfigFile();
		options += " --output " + getXmltvOutputFile();
		if (isWithCache()) {
			options += " --cache";
		}
		if (isWithSlow()) {
			options += " --slow";
		}
		if (isWithCacheSlow()) {
			options += " --cache-slow";
		}
		if (isVerbose()) {
			options += " --verbose";
		}
		if (isQuiet()) {
			options += " --quiet";
		}
		return options;
	}

	/**
	 * @return the days
	 */
	public static int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public static void setDays(int d) {
		days = d;
	}
	/**
	 * @return the withCache
	 */
	public static boolean isWithCache() {
		return withCache;
	}
	/**
	 * @param withCache the withCache to set
	 */
	public static void setWithCache(boolean wC) {
		withCache = wC;
	}
	/**
	 * @return the withSlow
	 */
	public static boolean isWithSlow() {
		return withSlow;
	}
	/**
	 * @param withSlow the withSlow to set
	 */
	public static void setWithSlow(boolean wS) {
		withSlow = wS;
	}
	/**
	 * @return the withCacheSlow
	 */
	public static boolean isWithCacheSlow() {
		return withCacheSlow;
	}
	/**
	 * @param withCacheSlow the withCacheSlow to set
	 */
	public static void setWithCacheSlow(boolean wCS) {
		withCacheSlow = wCS;
	}
	/**
	 * @return the verbose
	 */
	public static boolean isVerbose() {
		return verbose;
	}
	/**
	 * @param verbose the verbose to set
	 */
	public static void setVerbose(boolean v) {
		verbose = v;
	}
	/**
	 * @return the quiet
	 */
	public static boolean isQuiet() {
		return quiet;
	}
	/**
	 * @param quiet the quiet to set
	 */
	public static void setQuiet(boolean q) {
		quiet = q;
	}

	/**
	 * @return the xmltvConfigFile
	 */
	public static File getXmltvConfigFile() {
		return xmltvConfigFile;
	}
	/**
	 * @param xmltvConfigFile the xmltvConfigFile to set
	 */
	public static void setXmltvConfigFile(File xmltvCF) {
		xmltvConfigFile = xmltvCF;
	}
	/**
	 * @return the xmltvOutputFile
	 */
	public static File getXmltvOutputFile() {
		return xmltvOutputFile;
	}
	/**
	 * @param xmltvOutputFile the xmltvOutputFile to set
	 */
	public static void setXmltvOutputFile(File xmltvOF) {
		xmltvOutputFile = xmltvOF;
	}

}
