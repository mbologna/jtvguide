package it.unibg.cs.jtvguide.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class RunExternalCommand {

	static final int BLOCK_SIZE = 256000;
	static byte[] buf = new byte[BLOCK_SIZE];

	public static int runCommand(String command) {
		int result = -1;
		try {
			System.out.println("Running: " + command);
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
				}
			}
			return result;
		} catch (Exception e) {
			System.exit(0);
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

class StreamGobbler extends Thread {
	InputStream is;
	String type;
	OutputStream os;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
