package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVInspector;
import it.unibg.cs.jtvguide.util.DateFormatter;
import it.unibg.cs.jtvguide.util.FileUtils;

import java.util.Date;
import java.util.regex.Pattern;

public class XMLTVScheduleInspector implements XMLTVInspector {

	Pattern pattern;

	@Override
	public boolean isUpToDate() {
		pattern = Pattern.compile("<programme start=\""
				+ DateFormatter.formatDate(new Date()));
		return FileUtils.grep(UserPreferences.getXmltvOutputFile(), pattern);
	}

	@Override
	public boolean isUpToDate(Date d) {
		pattern = Pattern.compile("<programme start=\""
				+ DateFormatter.formatDate2Time(d));
		return FileUtils.grep(UserPreferences.getXmltvOutputFile(), pattern);
	}

}
