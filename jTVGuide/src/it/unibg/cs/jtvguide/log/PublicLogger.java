package it.unibg.cs.jtvguide.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * A wrapper for a singleton logger
 * @author Michele Bologna, Sebastiano Rota
 *
 */

public final class PublicLogger {
	private PublicLogger() { }
	static { 
		PropertyConfigurator.configure("properties/log4j.properties");
	}
	public final static Logger log = Logger.getLogger("JTVGuide");
	public static Logger getLogger() {
		return log;
	}
}
