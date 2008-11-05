package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.interfaces.XMLTVGrabbersByCountry;
import it.unibg.cs.jtvguide.log.PublicLogger;
import it.unibg.cs.jtvguide.util.SystemProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * A class to represent user's preferences
 * @author Michele Bologna, Sebastiano Rota
 *
 */

public final class UserPreferences implements DefaultPrefs {

	/*
	 * Defaults
	 */
	static int days = 6;
	static boolean withCache = false;
	static boolean quiet = false;

	/*
	 * XMLTV-related defaults
	 */
	static File xmltvConfigFile = new File("tv_grab.conf");
	static File xmltvOutputFile = new File("tv_grab.xml");
	
	static XMLTVGrabbersByCountry xmltvgbc = XMLTVGrabbersByCountry
			.getXMLGrabbersByCountry(SystemProperties.getSystemLanguage());

	public static XMLTVGrabbersByCountry getXMLTVCountry() {
		return xmltvgbc;
	}

	/**
	 * @return the days
	 */
	public static int getDays() {
		return days;
	}

	public static String getOptions() {
		String options = "";
		options += "--gui ";
		options += "--days " + getDays();
		options += UserPreferences.getXmltvConfigFile().toString().indexOf(' ') == -1 ? " --config-file "
				+ getXmltvConfigFile()
				: " --config-file \"" + UserPreferences.getXmltvConfigFile()
						+ "\"";
		options += UserPreferences.getXmltvOutputFile().toString().indexOf(' ') == -1 ? " --output "
				+ getXmltvOutputFile()
				: " --output \"" + getXmltvOutputFile() + "\"";
		if (isWithCache()) {
			options += " --cache";
		}

		if (isQuiet()) {
			options += " --quiet";
		}
		return options;
	}

	public static String getXMLTVCommandByCountry() {
		return xmltvgbc.getCOMMAND();
	}

	/**
	 * @return the xmltvConfigFile
	 */
	public static File getXmltvConfigFile() {
		return xmltvConfigFile;
	}

	/**
	 * @return the xmltvOutputFile
	 */
	public static File getXmltvOutputFile() {
		return xmltvOutputFile;
	}

	/**
	 * @return the quiet
	 */
	public static boolean isQuiet() {
		return quiet;
	}

	/**
	 * @return the withCache
	 */
	public static boolean isWithCache() {
		return withCache;
	}

	/**
	 * Load the user's preferences from file
	 * @return true if the preferences are correctly loaded, false otherwise
	 * @throws Exception
	 */
	public static boolean loadFromXMLFile() throws Exception {
		if (PREFERENCES_FILE.exists()) {
			SAXBuilder builder = new SAXBuilder();
			Document doc = null;

			try {
				doc = builder.build(PREFERENCES_FILE);
			} catch (JDOMException e) {
				PublicLogger.getLogger().error(e);
				return false;
			} catch (IOException e) {
				PublicLogger.getLogger().error(e);
				return false;
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				setDays(Integer.parseInt(root.getChildText("days")));
				setWithCache(Boolean.parseBoolean(root
						.getChildText("withCache")));
				setQuiet(Boolean.parseBoolean(root.getChildText("quiet")));
				setXmltvConfigFile(root.getChildText("xmltvConfigFile"));
				setXmltvOutputFile(root.getChildText("xmltvOutputFile"));
				setCountry(XMLTVGrabbersByCountry.getXMLGrabbersByCountry(root.getChildText("country")));
				return true;
			}
		}
		return false;
	}

	/**
	 * Save the user's preferences to file
	 * @return true if the preferences are correctly saved, false otherwise
	 * @throws IOException 
	 * @throws Exception
	 */
	public static boolean saveToXMLFile() throws IOException {
		if (PREFERENCES_FILE.exists())
			PREFERENCES_FILE.delete();
		Element root = new Element("preferences");
		root.addContent(new Comment("JTVGuide preferences file"));
		Document mydoc = new Document(root);
		root
				.setAttribute(new Attribute("date-generated", new Date()
						.toString()));
		Element daysElem = new Element("days");
		Element withCacheElem = new Element("withCache");
		Element quietElem = new Element("quiet");
		Element xmltvConfigFileElem = new Element("xmltvConfigFile");
		Element xmltvOutputFileElem = new Element("xmltvOutputFile");
		Element countryElem = new Element("country");
		daysElem.setText(Integer.toString(days));
		withCacheElem.setText(Boolean.toString(withCache));
		quietElem.setText(Boolean.toString(quiet));
		xmltvConfigFileElem.setText(xmltvConfigFile.getAbsolutePath());
		xmltvOutputFileElem.setText(xmltvOutputFile.getAbsolutePath());
		countryElem.setText(xmltvgbc.getLOCALE());
		root.addContent(daysElem);
		root.addContent(withCacheElem);
		root.addContent(quietElem);
		root.addContent(xmltvConfigFileElem);
		root.addContent(xmltvOutputFileElem);
		root.addContent(countryElem);
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(PREFERENCES_FILE);
			xmlOutputter.output(mydoc, fileOutputStream);
			return true;
		} catch (FileNotFoundException e) {
			PublicLogger.getLogger().error(e);
			return false;
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
			return false;
		}
		finally {
			fileOutputStream.close();
		}
	}

	public static boolean resetXMLFile() throws IOException{
		if (PREFERENCES_FILE.exists())
			PREFERENCES_FILE.delete();
		Element root = new Element("preferences");
		root.addContent(new Comment("JTVGuide preferences file"));
		Document mydoc = new Document(root);
		root
				.setAttribute(new Attribute("date-generated", new Date()
						.toString()));
		Element daysElem = new Element("days");
		Element withCacheElem = new Element("withCache");
		Element quietElem = new Element("quiet");
		Element xmltvConfigFileElem = new Element("xmltvConfigFile");
		Element xmltvOutputFileElem = new Element("xmltvOutputFile");
		Element countryElem = new Element("country");
		daysElem.setText(Integer.toString(6));
		withCacheElem.setText(Boolean.toString(false));
		quietElem.setText(Boolean.toString(false));
		xmltvConfigFileElem.setText(new File("tv_grab.conf").getAbsolutePath());
		xmltvOutputFileElem.setText(new File("tv_grab.xml").getAbsolutePath());
		countryElem.setText(SystemProperties.getSystemLanguage());
		root.addContent(daysElem);
		root.addContent(withCacheElem);
		root.addContent(quietElem);
		root.addContent(xmltvConfigFileElem);
		root.addContent(xmltvOutputFileElem);
		root.addContent(countryElem);
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(PREFERENCES_FILE);
			xmlOutputter.output(mydoc, fileOutputStream);
			return true;
		} catch (FileNotFoundException e) {
			PublicLogger.getLogger().error(e);
			return false;
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
			return false;
		}
		finally {
			fileOutputStream.close();
		}
	}

	public static void setCountry(XMLTVGrabbersByCountry c) {
		xmltvgbc = c;
	}

	/**
	 * @param d the n. of tv-programs days that will be in the schedule
	 *           
	 */
	public static void setDays(int d) {
		days = d;
	}

	/**
	 * @param q if xmltv has to be quiet when downloading schedule (= verbose)
	 * 
	 */
	public static void setQuiet(boolean q) {
		quiet = q;
	}

	/**
	 * @param wC if xmltv has to use a cache to speed up schedule downloading
	 * 
	 */
	public static void setWithCache(boolean wC) {
		withCache = wC;
	}

	/**
	 * @param string xmltvConfigFile xmltv config file path
	 */
	public static void setXmltvConfigFile(String string) {
		File f = new File(string);
		xmltvConfigFile = f.exists() && f.canRead() ? f : xmltvConfigFile;
	}

	/**
	 * @param xmltvOF xmltv output file path
	 *  
	 */
	public static void setXmltvOutputFile(String xmltvOF) {
		xmltvOutputFile = new File(xmltvOF);
	}

}
