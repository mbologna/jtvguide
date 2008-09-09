package it.unibg.cs.jtvguide;

import it.unibg.cs.jtvguide.interfaces.JTVGuidePrefs;
import it.unibg.cs.jtvguide.util.SystemProperties;
import it.unibg.cs.jtvguide.xmltv.XMLTVGrabbersByCountry;

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
 * @author Michele
 *
 */
public final class UserPreferences implements JTVGuidePrefs {

	/*
	 * Defaults
	 */
	static int days = 2;
	static boolean withCache = false;
	static boolean quiet = false;
	
	/*
	 * XMLTV-related defaults
	 */
	static File xmltvConfigFile = new File("tv_grab.conf");
	static File xmltvOutputFile = new File("tv_grab.xml");
	static String locale = SystemProperties.getSystemLanguage();
	static XMLTVGrabbersByCountry xmltvgbc = XMLTVGrabbersByCountry.getXMLGrabbersByCountry(locale);

	public static boolean loadFromXMLFile() {
		if (PREFERENCES_FILE.exists()) {
			SAXBuilder builder = new SAXBuilder();
			Document doc = null;

			try {
				doc = builder.build(PREFERENCES_FILE);
			} catch (JDOMException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				setDays(Integer.parseInt(root.getChildText("days")));
				setWithCache(Boolean.parseBoolean(root.getChildText("withCache")));				
				setQuiet(Boolean.parseBoolean(root.getChildText("quiet")));
				setXmltvConfigFile(root.getChildText("xmltvConfigFile"));
				setXmltvOutputFile(root.getChildText("xmltvOutputFile"));
				return true;
			}
		}
		return false;
	}

	public static boolean saveToXMLFile() {
		if (PREFERENCES_FILE.exists()) PREFERENCES_FILE.delete();
		Element root = new Element("preferences");
		root.addContent(new Comment("JTVGuide preferences file"));
		Document mydoc = new Document(root);
		root.setAttribute(new Attribute("date-generated",new Date().toString()));
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
		countryElem.setText(locale);
		root.addContent(daysElem);
		root.addContent(withCacheElem);
		root.addContent(quietElem);
		root.addContent(xmltvConfigFileElem);
		root.addContent(xmltvOutputFileElem);
		root.addContent(countryElem);
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(PREFERENCES_FILE);
			xmlOutputter.output(mydoc, fileOutputStream);
			fileOutputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	public static void setCountry(XMLTVGrabbersByCountry c) {
		xmltvgbc = c;
	}

	public static String getXMLTVCommandByCountry() {
		return xmltvgbc.getCOMMAND();
	}

	public static String getOptions() {
		String options = new String();
		options += "--gui ";
		options += "--days " + getDays();
		options += UserPreferences.getXmltvConfigFile().toString().indexOf(' ') == -1?
				 " --config-file " + getXmltvConfigFile()
				 : " --config-file \"" + UserPreferences.getXmltvConfigFile() + "\"";
		options += UserPreferences.getXmltvOutputFile().toString().indexOf(' ') == -1?
				" --output " + getXmltvOutputFile()
				: " --output \"" + getXmltvOutputFile() + "\"";
		if (isWithCache()) {
			options += " --cache";
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
		File f = new File(string);
		xmltvConfigFile = f.exists() && f.canRead() ? f: xmltvConfigFile; 
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
