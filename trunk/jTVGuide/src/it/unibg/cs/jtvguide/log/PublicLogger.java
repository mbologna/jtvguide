package it.unibg.cs.jtvguide.log;

import org.apache.log4j.Logger;

/**
 * A wrapper for a singleton logger
 * @author Michele Bologna, Sebastiano Rota
 *
 */

public final class PublicLogger {
	private PublicLogger() { }
	public final static Logger log = Logger.getLogger("JTVGuide");
	public static Logger getLogger() {
		return log;
	}
}
