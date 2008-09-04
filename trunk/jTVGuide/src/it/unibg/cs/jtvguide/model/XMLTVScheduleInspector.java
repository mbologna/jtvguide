package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVInspector;
import it.unibg.cs.jtvguide.util.DateFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLTVScheduleInspector implements XMLTVInspector {
	
	Pattern pattern;

	@Override
	public boolean isUpToDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, UserPreferences.getDays()-1); // don't sum today
		pattern = Pattern.compile("<programme start=\"" + DateFormatter.formatDate(c.getTime()));
		return grep(pattern);
	}

	@Override
	public boolean isUpToDate(Date d) {
		pattern = Pattern.compile("<programme start=\"" +DateFormatter.formatDate2Time(d));
		return grep(pattern);
	}

	public boolean grep(Pattern p) {
		Matcher m;
		try {
			m = p.matcher(fromFile(UserPreferences.getXmltvOutputFile()
					.toString()));
			return m.find();
		} catch (IOException e) {
			return false;
		}
	}

	public CharSequence fromFile(String filename) throws IOException {
		FileInputStream input = new FileInputStream(filename);
		FileChannel channel = input.getChannel();
		ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				(int) channel.size());
		CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
		return cbuf;
	}

}
