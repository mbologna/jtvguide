package util;

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

	private String command = null;
	private StringBuffer input = new StringBuffer();
	private StringBuffer error = new StringBuffer();

	/**
	 * Creates a new <code>ExternalCommand</code> instance with given String command.
	 *
	 * @param command A string representing external command to execute
	 */
	public RunExternalCommand(String command) {
		this.command = command;
	}

	/**
	 * Creates a new <code>ExternalCommand</code> instance with given String command.
	 */
	public RunExternalCommand() {
	}

	/**
	 * Returns input of external command
	 *
	 * @return <code>String</code>
	 */
	public String getInput() {
		return input.toString();
	}

	/**
	 * Returns error of external command
	 *
	 * @return <code>String</code>
	 */
	public String getError() {
		return error.toString();
	}

	/**
	 * Runs external command
	 *
	 * @throws IOException
	 */
	public void runCommand() throws IOException {
		input.setLength(0); //erase input StringBuffer
		error.setLength(0); //erase error StringBuffer
		String s;
		if (command != null) {
			Runtime a = Runtime.getRuntime();
			java.lang.Process p = a.exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));

			// read the output from the command

			//System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				input.append(s);
			}

			// read any errors from the attempted command

			//System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				error.append(s);
			}
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Runs external command
	 *
	 * @param command A string representing external command to execut
	 * @throws IOException
	 */
	public void runCommand(String command) throws IOException {
		input.setLength(0); //erase input StringBuffer
		error.setLength(0); //erase error StringBuffer

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
