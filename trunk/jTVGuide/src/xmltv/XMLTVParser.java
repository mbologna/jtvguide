package xmltv;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import data.Channel;

public class XMLTVParser /* implements XMLTVHelper */{

	// private Schedule mSchedule;

	public XMLTVParser() {
	}

	public static boolean parse() {
		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			//doc = builder.build(UserPreferences.getXmltvOutputFile());
			doc = builder.build("tv_grab_it.xml");
			List channels = doc.getRootElement().getChildren("channel");
			Iterator channelsIterator = channels.iterator();
			
			/* 
			 * abbiamo a disposizione i display-name, controllare sul DTD quanti ce ne possono essere 
			 * 
			 * Parsing dei canali
			 */
		
			String id;
			String displayName = "foo";
			String icon = "bar";
			
			while (channelsIterator.hasNext()) {
				Element channel = (Element) channelsIterator.next();
				id = channel.getAttributeValue("id");
				List displayNames = channel.getChildren("display-name");
				Iterator displayNamesIterator = displayNames.iterator();
				while (displayNamesIterator.hasNext()) {
					Element name = (Element) displayNamesIterator.next();				
					displayName = name.getValue();
				}
				if (channel.getChild("icon") != null) {
					/* icon può essere vuota */
					icon = channel.getChild("icon").getAttributeValue("src");
				}
				System.out.println(id + "," + displayName + "," + icon);
				
				/* Creare un nuovo canale e aggiungerlo alla lista dei canali */
			}
			
			/*
			 * Parsing degli show
			 */
					
			List shows = doc.getRootElement().getChildren("programme");
			Iterator showsIterator = shows.iterator();
			Date startDate, stopDate;
			String channel; /* ------------------------------------- da cambiare */
			String title, desc;
			while (showsIterator.hasNext()) {
				Element show = (Element) showsIterator.next();
				String start = show.getAttributeValue("start");
				SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMddHHmmss ZZZZZ");
				startDate = dateParser.parse(start);
				String stop = show.getAttributeValue("stop");
				stopDate = dateParser.parse(stop);
				channel = show.getAttributeValue("channel");
				title = show.getChildText("title");
				desc = show.getChildText("desc");	
				SimpleDateFormat dateFormatter = new SimpleDateFormat("(dd/MM) [HH:mm]");
				System.out.println(dateFormatter.format(startDate)+","+dateFormatter.format(stopDate)+ ","+channel+","+title+","+desc);
				/* Creare un nuovo show e aggiungerlo alla lista gli show */
			}			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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
