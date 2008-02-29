package xmltv;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLTVParser /* implements XMLTVHelper */{

	// private Schedule mSchedule;

	public XMLTVParser() {
	}

	public static boolean parse() {
		SAXBuilder builder = new SAXBuilder();
		// Create the document
		Document doc;
		try {
			doc = builder.build(new File("tv_grab_it.xml"));
			Element root = doc.getRootElement();
			List channels = root.getChildren("channel");
			Iterator channelsIterator = channels.iterator();
			while (channelsIterator.hasNext()) {
				Element channel = (Element) channelsIterator.next();
				Element displayName = channel.getChild("display-name");
				System.out.println(displayName.getValue() + " " + channel.getAttribute("id").getValue());
			}
			List shows = root.getChildren("programme");
			Iterator showsIterator = shows.iterator();
			while (showsIterator.hasNext()) {
				Element show = (Element) showsIterator.next();
				String start = show.getAttributeValue("start");
				String stop = show.getAttributeValue("start");
				String channel = show.getAttributeValue("channel");
				String title = show.getChildText("title");
				String desc = show.getChildText("desc");
				System.out.println(start + "-" + stop + ": " + title + " --- " + channel);
			}			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	/*
	 * public Schedule getSchedule () { return mSchedule; }
	 * 
	 * public void setSchedule (Schedule val) { this.mSchedule = val; }
	 */

}
