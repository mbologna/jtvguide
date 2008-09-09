package it.unibg.cs.jtvguide.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
	public static boolean grep(File f, Pattern p) {
		Matcher m;
		try {
			m = p.matcher(fromFile(f.toString()));
			return m.find();
		} catch (IOException e) {
			return false;
		}
	}

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
			return -1;
		} catch (IOException e) {
			return -1;
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
