package xmltv;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import util.DateFormatter;
import collection.ChannelMap;
import collection.Schedule;
import data.Channel;
import data.Program;

public class XMLTVParser /* implements XMLTVHelper */{

	private Schedule mSchedule;
	private ChannelMap cm;

	public XMLTVParser() {
	}

	public boolean parse() {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;

		try {
			// doc = builder.build(UserPreferences.getXmltvOutputFile());
			doc = builder.build("tv_grab_it.xml");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (doc != null) {
			List<Element> channels = doc.getRootElement().getChildren("channel");
			Iterator<Element> channelsIterator = channels.iterator();
			cm = new ChannelMap();

			/*
			 * abbiamo a disposizione i display-name, controllare sul DTD quanti
			 * ce ne possono essere
			 * 
			 * Parsing dei canali
			 */

			String id;
			String displayName = "foo";
			String icon = "bar";

			while (channelsIterator.hasNext()) {
				Element channel = channelsIterator.next();
				id = channel.getAttributeValue("id");
				List<Element> displayNames = channel.getChildren("display-name");
				Iterator<Element> displayNamesIterator = displayNames.iterator();
				while (displayNamesIterator.hasNext()) {
					Element name = displayNamesIterator.next();
					displayName = name.getValue();
					break;  // CONTROLLARE
				}
				if (channel.getChild("icon") != null) {
					/* icon può essere vuota */
					icon = channel.getChild("icon").getAttributeValue("src");
				}
				/* Creare un nuovo canale e aggiungerlo alla lista dei canali */
				Channel c = new Channel(displayName, id);
				try {
					cm.add(id, c);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			/*
			 * Parsing degli show
			 */

			List<Element> shows = doc.getRootElement().getChildren("programme");
			Iterator<Element> showsIterator = shows.iterator();
			Date startDate = null, stopDate = null;
			String channelID;
			String title, desc;
			Program p;
			mSchedule = new Schedule();
			
			while (showsIterator.hasNext()) {
				Element show = showsIterator.next();
				String start = show.getAttributeValue("start");
				startDate = DateFormatter.formatString(start);
				String stop = show.getAttributeValue("stop");
				stopDate = DateFormatter.formatString(stop);
				channelID = show.getAttributeValue("channel");
				try {
					if (cm.testIfPresent(channelID)) {
						title = show.getChildText("title");
						desc = show.getChildText("desc");
						if (startDate != null && stopDate != null) {
							/*
							 * Creare un nuovo show e aggiungerlo alla lista gli
							 * show
							 */
							p = new Program(startDate,stopDate,cm.get(channelID),title);
							mSchedule.add(p);
						}
					}
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return true;
	}
	

	public ChannelMap getCm() {
		return cm;
	}

	public Schedule getMSchedule() {
		return mSchedule;
	}

}
