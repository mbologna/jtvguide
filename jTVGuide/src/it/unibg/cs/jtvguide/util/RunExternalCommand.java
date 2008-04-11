package it.unibg.cs.jtvguide.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This is class which provides running external commands
 *
 * @author 1337factor.com
 * @version 0.1 8/17/2006
 * @since com._1337factor 1.0
 */
public class RunExternalCommand {

	/**
	 * Runs external command
	 *
	 * @param command A string representing external command to execut
	 * @throws IOException
	 */
	public static void runCommand(String command) throws IOException {
		Process p = null;

		try {
			p = Runtime.getRuntime().exec(command);
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(),
					"OUTPUT");
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(),
					"ERROR");
			errorGobbler.start();
			outputGobbler.start();
			int exitVal = p.waitFor();
			System.out.println("ExitValue: " + exitVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class StreamGobbler extends Thread {
	InputStream is;
	String type;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(type + ">" + line);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
