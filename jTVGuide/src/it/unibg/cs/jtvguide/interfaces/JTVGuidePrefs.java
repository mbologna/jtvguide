package it.unibg.cs.jtvguide.interfaces;

import it.unibg.cs.jtvguide.util.SystemProperties;

import java.io.File;

public interface JTVGuidePrefs {
	public final static String PREFERENCES_FILENAME = ".jtvguide.xml";
	public final static String PREFERENCES_PATH = SystemProperties.getHomeDirectory()+SystemProperties.getFileSeparator();
	public final static File PREFERENCES_FILE = new File(PREFERENCES_PATH+PREFERENCES_FILENAME);
}
