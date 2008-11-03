package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVInspector;
import it.unibg.cs.jtvguide.util.DateFormatter;
import it.unibg.cs.jtvguide.util.FileUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * A class to inspect xmltv schedule
 * @author Michele
 *
 */
public class XMLTVScheduleInspector implements XMLTVInspector {

	Pattern pattern;

	/**
	 * test if the specified schedule is up to date.
	 * @return true if the schedule is updated, false otherwise
	 */
	@Override
	public boolean isUpToDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return isUpToDate(new Date()) && isUpToDate(c.getTime());
	}
	/**
	 * test if the specified schedule is up to date and contains programs for the specified date
	 * @param d the date to test
	 * @return true if the schedule is updated and contains the specified date, false otherwise
	 */
	@Override
	public boolean isUpToDate(Date d) {
		pattern = Pattern.compile("<programme start=\""
				+ DateFormatter.formatDate(d));
		return FileUtils.grep(UserPreferences.getXmltvOutputFile(), pattern);
	}

}
