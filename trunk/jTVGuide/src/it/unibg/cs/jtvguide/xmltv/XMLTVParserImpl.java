package it.unibg.cs.jtvguide.xmltv;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.interfaces.xmltv.XMLTVParser;
import it.unibg.cs.jtvguide.model.Channel;
import it.unibg.cs.jtvguide.model.ChannelMap;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.util.DateFormatter;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLTVParserImpl implements XMLTVParser {

	private Schedule schedule;
	private ChannelMap cm;
	
	public XMLTVParserImpl() {
		cm = new ChannelMap();
		schedule = new Schedule(cm);
	}
	
	public Schedule getSchedule() {
		return schedule;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean parse() {
		SAXBuilder builder = new SAXBuilder(true);
		Document doc = null;

		try {
			 doc = builder.build(UserPreferences.getXmltvOutputFile());
		} catch (JDOMException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		if (doc != null) {
			List<Element> channels = doc.getRootElement()
					.getChildren("channel");
			Iterator<Element> channelsIterator = channels.iterator();

			String id;
			String displayName;

			while (channelsIterator.hasNext()) {
				Element channel = channelsIterator.next();
				id = channel.getAttributeValue("id");
				displayName = channel.getChildText("display-name");

				/* Creare un nuovo canale e aggiungerlo alla lista dei canali */

				Channel c = new Channel(id, displayName);
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
						schedule.add(p);
					}
				}
				else {
					throw new RuntimeException("Channel not found");
				}
			}
		}
		return true;
	}
}
