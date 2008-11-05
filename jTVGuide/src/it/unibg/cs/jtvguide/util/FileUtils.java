package it.unibg.cs.jtvguide.util;

import it.unibg.cs.jtvguide.log.PublicLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Various fileutils
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public final class FileUtils {
	private FileUtils() { }
	/**
	 * search for a pattern inside a text file
	 * @param f the file to search within
	 * @param p the pattern to search
	 * @return true if found something, false otherwise
	 */
	public static boolean grep(File f, Pattern p) {
		Matcher m;
		try {
			m = p.matcher(fromFile(f.toString()));
			return m.find();
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
			return false;
		}
	}
	
	/**
	 * Count the lines uncommented
	 * @param f the file to search within
	 * @return the number of lines uncommented 
	 */

	public static int uncommentedLinesCount(File f) {
		BufferedReader br = null;
		int counter = 0;
		String line;
		try {
			br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				if (!(line.charAt(0) == '#'))
					counter++;
			}
			br.close();
			return counter;
		} catch (FileNotFoundException e) {
			PublicLogger.getLogger().error(e);
			return -1;
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
			return -1;
		}
	}
	/**
	 * Copy of two files
	 * @param src file 
	 * @param dst file
	 * @throws IOException
	 */
    public static void copy(File src, File dst) {
        InputStream in = null;
		try {
			in = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			PublicLogger.getLogger().error(e);
		}
        OutputStream out = null;
		try {
			out = new FileOutputStream(dst);
		} catch (FileNotFoundException e) {
			PublicLogger.getLogger().error(e);
		}
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        try {
			while ((len = in.read(buf)) > 0) {
			    out.write(buf, 0, len);
			}
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
		}
        try {
			in.close();
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
		}
        try {
			out.close();
		} catch (IOException e) {
			PublicLogger.getLogger().error(e);
		}
    }


	private static CharSequence fromFile(String filename) throws IOException {
		FileInputStream input = new FileInputStream(filename);
		FileChannel channel = input.getChannel();
		ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				(int) channel.size());
		CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
		return cbuf;
	}
}
