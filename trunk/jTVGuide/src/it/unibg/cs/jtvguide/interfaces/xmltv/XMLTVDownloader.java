package it.unibg.cs.jtvguide.interfaces.xmltv;

/**
 * An interface for download XMLTV schedules.
 * It has one method to download the schedule for the option supplied by the user.
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public interface XMLTVDownloader {
	/**
	 * Download the XMLTV schedule
	 * @return 0 if the schedule is downloaded correctly, -1 otherwise.
	 */
	public int downloadSchedule();
}
