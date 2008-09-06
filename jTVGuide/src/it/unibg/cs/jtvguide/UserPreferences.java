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
public class UserPreferences implements JTVGuidePrefs {

	/*
	 * Defaults
	 */
	static int days = 3;
	static boolean withCache = false;
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
		daysElem.setText(Integer.toString(days));
		withCacheElem.setText(Boolean.toString(withCache));
		quietElem.setText(Boolean.toString(quiet));
		xmltvConfigFileElem.setText(xmltvConfigFile.getAbsolutePath());
		xmltvOutputFileElem.setText(xmltvOutputFile.getAbsolutePath());
		root.addContent(daysElem);
		root.addContent(withCacheElem);
		root.addContent(quietElem);
		root.addContent(xmltvConfigFileElem);
		root.addContent(xmltvOutputFileElem);
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
		locale = c.getLOCALE();
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
		return xmltvConfigFile.getAbsoluteFile();
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
		return xmltvOutputFile.getAbsoluteFile();
	}
	/**
	 * @param xmltvOutputFile the xmltvOutputFile to set
	 */
	public static void setXmltvOutputFile(String xmltvOF) {
		xmltvOutputFile = new File(xmltvOF);
	}

}
