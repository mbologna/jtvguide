package it.unibg.cs.jtvguide.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class RunExternalCommand {

	/**
	 * Runs external command
	 * 
	 * @param command
	 *            A string representing external command to execute
	 * @return
	 * @throws IOException
	 */
	public static int runCommand(String command) {
		/*
		 * Process p = null; int exitVal = -1;
		 * 
		 * try { System.out.println("running " + command); p =
		 * Runtime.getRuntime().exec(command); StreamGobbler outputGobbler = new
		 * StreamGobbler(p.getInputStream(), "OUTPUT"); StreamGobbler
		 * errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
		 * StreamGobbler inputGobbler = new
		 * StreamGobbler(p.getInputStream(),"INPUT"); errorGobbler.start();
		 * outputGobbler.start(); inputGobbler.start(); exitVal = p.waitFor(); }
		 * catch (IOException e) { e.printStackTrace(); } catch
		 * (InterruptedException e) { e.printStackTrace(); } finally { return
		 * exitVal; }
		 */
		Process proc = null;

		try {
			proc = Runtime.getRuntime().exec(command);
			StreamGobbler outThread = new StreamGobbler(proc.getInputStream(),
					"OUT");
			StreamGobbler errThread = new StreamGobbler(proc.getErrorStream(),
					"ERR");
			StreamGobbler stdinThread = new StreamGobbler(System.in, proc
					.getOutputStream(), "STDIN");

			stdinThread.start();
			outThread.start();
			errThread.start();

			proc.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
}

class StreamGobbler extends Thread {
	InputStream is;
	PrintStream out;
	String type;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.out = System.out;
		this.type = type;
	}

	StreamGobbler(InputStream is, OutputStream os, String type) {
		this.is = is;
		this.out = new PrintStream(os);
		this.type = type;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);

			int b = 0;
			while ((b = isr.read()) != -1) {
				out.print((char) b);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
