package it.unibg.cs.jtvguide.xmltv.interfaces;

import java.util.Date;
/**
 * An interface to specify which operations should offer an XMLTV Schedule inspector
 * 
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public interface XMLTVInspector {
	/**
	 * test if the specified schedule is up to date.
	 * @return true if the schedule is updated, false otherwise
	 */
	public boolean isUpToDate();
	/**
	 * test if the specified schedule is up to date and contains programs for the specified date
	 * @param d the date to test
	 * @return true if the schedule is updated and contains the specified date, false otherwise
	 */
	public boolean isUpToDate(Date d);
}
