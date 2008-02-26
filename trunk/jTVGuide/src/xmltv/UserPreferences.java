package xmltv;

import java.io.File;


/**
 * @author Michele
 *
 */
public class UserPreferences {
	private int days = 1;
	private boolean withCache = false;
	private boolean withSlow = false;
	private boolean withCacheSlow = false;
	private boolean verbose = true;
	private boolean quiet = false;
	private File xmltvConfigFile = null;
	private File xmltvOutputFile = null;
	
	public String getOptions() {
		String options = new String();
		options += "--days " + this.getDays();
		options += " --config-file " + this.getXmltvConfigFile();
		options += " --output " + this.getXmltvOutputFile();
		if (this.isWithCache()) {
			options += " --cache";
		}
		if (this.isWithSlow()) {
			options += " --slow";
		}
		if (this.isWithCacheSlow()) {
			options += " --cache-slow";
		}
		if (this.isVerbose()) {
			options += " --verbose";
		}
		if (this.isQuiet()) {
			options += " --quiet";
		}
		return options;
	}
	
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the withCache
	 */
	public boolean isWithCache() {
		return withCache;
	}
	/**
	 * @param withCache the withCache to set
	 */
	public void setWithCache(boolean withCache) {
		this.withCache = withCache;
	}
	/**
	 * @return the withSlow
	 */
	public boolean isWithSlow() {
		return withSlow;
	}
	/**
	 * @param withSlow the withSlow to set
	 */
	public void setWithSlow(boolean withSlow) {
		this.withSlow = withSlow;
	}
	/**
	 * @return the withCacheSlow
	 */
	public boolean isWithCacheSlow() {
		return withCacheSlow;
	}
	/**
	 * @param withCacheSlow the withCacheSlow to set
	 */
	public void setWithCacheSlow(boolean withCacheSlow) {
		this.withCacheSlow = withCacheSlow;
	}
	/**
	 * @return the verbose
	 */
	public boolean isVerbose() {
		return verbose;
	}
	/**
	 * @param verbose the verbose to set
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	/**
	 * @return the quiet
	 */
	public boolean isQuiet() {
		return quiet;
	}
	/**
	 * @param quiet the quiet to set
	 */
	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}
	
	/**
	 * @return the xmltvConfigFile
	 */
	public File getXmltvConfigFile() {
		return xmltvConfigFile;
	}
	/**
	 * @param xmltvConfigFile the xmltvConfigFile to set
	 */
	public void setXmltvConfigFile(File xmltvConfigFile) {
		this.xmltvConfigFile = xmltvConfigFile;
	}
	/**
	 * @return the xmltvOutputFile
	 */
	public File getXmltvOutputFile() {
		return xmltvOutputFile;
	}
	/**
	 * @param xmltvOutputFile the xmltvOutputFile to set
	 */
	public void setXmltvOutputFile(File xmltvOutputFile) {
		this.xmltvOutputFile = xmltvOutputFile;
	}
	
}
