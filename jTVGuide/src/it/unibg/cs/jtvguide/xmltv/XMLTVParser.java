package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.collection.ChannelMap;
import it.unibg.cs.jtvguide.collection.Schedule;
import it.unibg.cs.jtvguide.data.Channel;
import it.unibg.cs.jtvguide.data.Program;
import it.unibg.cs.jtvguide.util.DateFormatter;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLTVParser /* implements XMLTVHelper */{

	private Schedule mSchedule;
	private ChannelMap cm;

	public XMLTVParser() {
	}

	@SuppressWarnings("unchecked")
	public void parse(Schedule mSchedule) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;

		try {
			 doc = builder.build(UserPreferences.getXmltvOutputFile());
		} catch (JDOMException e) {
			return;
		} catch (IOException e) {
			return;
		}

		if (doc != null) {
			List<Element> channels = doc.getRootElement()
					.getChildren("channel");
			Iterator<Element> channelsIterator = channels.iterator();
			cm = new ChannelMap();

			String id;
			String displayName;

			while (channelsIterator.hasNext()) {
				Element channel = channelsIterator.next();
				id = channel.getAttributeValue("id");
				displayName = channel.getChildText("display-name");

				/* Creare un nuovo canale e aggiungerlo alla lista dei canali */

				Channel c = new Channel(displayName, id);
				cm.add(id, c);
			}

			/*
			 * Parsing degli show
			 */

			List<Element> shows = doc.getRootElement().getChildren("programme");
			Iterator<Element> showsIterator = shows.iterator();
			Date startDate = null, stopDate = null;
			String channelID;
			String title;
			Program p;
			//mSchedule = new Schedule();

			while (showsIterator.hasNext()) {
				Element show = showsIterator.next();
				String start = show.getAttributeValue("start");
				startDate = DateFormatter.formatString(start);
				String stop = show.getAttributeValue("stop");
				stopDate = DateFormatter.formatString(stop);
				channelID = show.getAttributeValue("channel");

				if (cm.contains(channelID)) {
					title = show.getChildText("title");
					if (startDate != null && stopDate != null) {
						/*
						 * Creare un nuovo show e aggiungerlo alla lista gli
						 * show
						 */
						p = new Program(startDate, stopDate, cm.get(channelID),
								title);
						mSchedule.add(p);
					}
				}
				/*else {
					new Exception("Channel not found");
				}*/
			}
			this.mSchedule = mSchedule;
		}
	}

	public ChannelMap getCm() {
		return cm;
	}

	public Schedule getMSchedule() {
		return mSchedule;
	}

}
