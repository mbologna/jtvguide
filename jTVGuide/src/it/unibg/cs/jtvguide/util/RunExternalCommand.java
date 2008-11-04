package it.unibg.cs.jtvguide.util;

import it.unibg.cs.jtvguide.log.PublicLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * A class to run external programs
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class RunExternalCommand {

	static final int BLOCK_SIZE = 256000;
	static byte[] buf = new byte[BLOCK_SIZE];
	final static Logger log = Logger.getLogger("JTVGuide");

	/**
	 * Run an command
	 * @param command to execute
	 * @return the exit-status of the command
	 */
	public static int runCommand(String command) {
		int result = -1;		
		
		try {
			log.debug("Running: " + command);
			Process process = Runtime.getRuntime().exec(command);

			for (boolean isRunning = true; isRunning;) {
				Thread.sleep(400);
				try {
					result = process.exitValue();
					isRunning = false;
				} catch (IllegalThreadStateException ie) {
				}

				try {
					pipe(System.in, process.getOutputStream(), false);
					pipe(process.getErrorStream(), System.err, false);
					pipe(process.getInputStream(), System.out, false);
				} catch (Exception e) {
					PublicLogger.getLogger().error(e);
				}
			}
			return result;
		} catch (Exception e) {
			PublicLogger.getLogger().error(e);
		}
		return result;
	}

	static void pipe(InputStream in, OutputStream out, boolean isBlocking)
			throws IOException {
		int nread;
		int navailable;
		while ((navailable = isBlocking ? Integer.MAX_VALUE : in.available()) > 0
				&& (nread = in.read(buf, 0, Math.min(buf.length, navailable))) >= 0) {
			out.write(buf, 0, nread);
		}
		out.flush();
	}	
}


