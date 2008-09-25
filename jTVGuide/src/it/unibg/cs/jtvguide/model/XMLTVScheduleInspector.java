package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVInspector;
import it.unibg.cs.jtvguide.util.DateFormatter;
import it.unibg.cs.jtvguide.util.FileUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class XMLTVScheduleInspector implements XMLTVInspector {

	Pattern pattern;

	@Override
	public boolean isUpToDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return isUpToDate(new Date()) && isUpToDate(c.getTime());
	}

	@Override
	public boolean isUpToDate(Date d) {
		pattern = Pattern.compile("<programme start=\""
				+ DateFormatter.formatDate(d));
		return FileUtils.grep(UserPreferences.getXmltvOutputFile(), pattern);
	}

}
