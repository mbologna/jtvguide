package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.interfaces.JTVGuidePrefs;
import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.xmltv.XMLTVGrabbersByCountry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


/**
 * @author Michele
 *
 */
public class UserPreferences implements JTVGuidePrefs {

	/*
	 * Defaults
	 */
	static int days = 1;
	static boolean withCache = false;
	static boolean withSlow = false;
	static boolean withCacheSlow = false;
	static boolean verbose = false;
	static boolean quiet = false;
	
	/*
	 * XMLTV-related defaults
	 */
	static File xmltvConfigFile = new File("tv_grab.conf");
	static File xmltvOutputFile = new File("tv_grab.xml");
	static String locale = SystemProperties.getSystemLanguage();
	
	/*
	 * JTVGuide defaults
	 */
	
	public static boolean loadFromXMLFile() {
		return false;
	}

	public static boolean saveToXMLFile() {
		if (PREFERENCES_FILE.exists()) PREFERENCES_FILE.delete();
		if (!PREFERENCES_FILE.canWrite()) throw new RuntimeException("can't write on preferences file");
		Element root = new Element("preferences");
		Document mydoc = new Document(root);
		Calendar c = Calendar.getInstance();
		root.setAttribute(new Attribute("date-generated",c.toString()));
		Element daysElem = new Element("days");
		Element withCacheElem = new Element("withCache");
		Element withSlowElem = new Element("withSlow");
		Element withCacheSlowElem = new Element("wichCacheSlow");
		Element verboseElem = new Element("verbose");
		Element quietElem = new Element("quiet");
		Element xmltvConfigFileElem = new Element("xmltvConfigFile");
		Element xmltvOutputFileElem = new Element("xmltvOutputFile");
		daysElem.setText(Integer.toString(days));
		withCacheElem.setText(Boolean.toString(withCache));
		withSlowElem.setText(Boolean.toString(withSlow));
		withCacheSlowElem.setText(Boolean.toString(withCacheSlow));
		verboseElem.setText(Boolean.toString(verbose));
		quietElem.setText(Boolean.toString(quiet));
		xmltvConfigFileElem.setText(xmltvConfigFile.getAbsolutePath());
		xmltvOutputFileElem.setText(xmltvOutputFile.getAbsolutePath());
		root.addContent(daysElem);
		root.addContent(withCacheElem);
		root.addContent(withSlowElem);
		root.addContent(withCacheSlowElem);
		root.addContent(verboseElem);
		root.addContent(quietElem);
		root.addContent(xmltvConfigFileElem);
		root.addContent(xmltvOutputFileElem);
		root.addContent(new Comment("JTVGuide preferences file"));
		XMLOutputter output = new XMLOutputter();
		FileWriter fw;
		try {
			fw = new FileWriter(PREFERENCES_FILE);
			output.output(mydoc, fw);
			fw.close();
			return true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
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
	public static void setXmltvConfigFile(String string) {
		xmltvConfigFile = new File(string);
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
	public static void setXmltvOutputFile(String xmltvOF) {
		xmltvOutputFile = new File(xmltvOF);
	}

}
