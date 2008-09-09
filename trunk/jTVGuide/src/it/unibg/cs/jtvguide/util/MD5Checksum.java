package it.unibg.cs.jtvguide.util;

import it.unibg.cs.jtvguide.interfaces.JTVGuidePrefs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class MD5Checksum {

	public static boolean checkMD5(String file, String MD5) {
		try {
			return getMD5Checksum(file).equals(MD5);
		} catch (Exception e) {
			return false;
		}
	}

	public static String getMD5Checksum(String filename) throws Exception {
		byte[] b = createChecksum(filename);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static String readMD5FromFile() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					JTVGuidePrefs.CONFIG_FILE_MD5));
			String MD5 = br.readLine();
			br.close();
			return MD5;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}

	public static boolean writeMD5ToFile(File f) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(
					JTVGuidePrefs.CONFIG_FILE_MD5));
			bw.write(getMD5Checksum(f.toString()));
			bw.close();
			return true;
		} catch (IOException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	private static byte[] createChecksum(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}
}
