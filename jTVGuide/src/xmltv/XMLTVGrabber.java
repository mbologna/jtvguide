import java.io.BufferedReader;
import java.io.InputStreamReader;


public class XMLTVGrabber implements XMLTVHelper {

	private XMLTVParser mXMLTVParser;

	public XMLTVGrabber () {
	}

	public boolean grabSchedule (XMLTVHelper options) {
		/* devo richiamare il grabber di xmltv */
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("windows") > -1) {
			/* siamo in Windows */
			String command = "xmltv.exe " + mXMLTVGrabbers.getGrabber(mUserPreferences.getLocation());
		}
		else {
			/* UNIX e derivati */
			String command = mXMLTVGrabbers.getGrabber(mUserPreferences.getLocation());
		}

		String parameters = userPreferences.getOptions();
		
		Process p = Runtime.getRuntime().exec(command + " " + parameters);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        p.waitFor();
        status = p.exitValue();
		return (status == 0) ? true : false;
		mXMLTVParser.parse(xmltvOutput);
	}

	public XMLTVParser getXMLTVParser () {
		return mXMLTVParser;
	}

	public void setXMLTVParser (XMLTVParser val) {
		this.mXMLTVParser = val;
	}

}

