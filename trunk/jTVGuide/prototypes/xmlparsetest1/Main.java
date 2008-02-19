/*
 *
 */
package xmlparsetest1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Michele
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Calendar startDate = null;
        Calendar stopDate = null;
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setValidating(true);
        System.out.println("************************************************************");
        System.out.println("Data/ora attuale: " + formatter1.format(now.getTime()));
        System.out.println("************************************************************");
        try {
            DocumentBuilder db = docBuilderFactory.newDocumentBuilder();
            Document dom = db.parse("tv_grab_it.xml");

            Element docEle = dom.getDocumentElement();
            NodeList nl = docEle.getElementsByTagName("programme");
            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element el = (Element) nl.item(i);
                    String start = el.getAttribute("start");
                    int year = Integer.parseInt(start.substring(0, 4));
                    int month = Integer.parseInt(start.substring(4, 6));
                    int day = Integer.parseInt(start.substring(6, 8));
                    int hour = Integer.parseInt(start.substring(8, 10));
                    int minute = Integer.parseInt(start.substring(10, 12));
                    startDate = new GregorianCalendar(year, month - 1, day, hour, minute);

                    String stop = el.getAttribute("stop");
                    year = Integer.parseInt(stop.substring(0, 4));
                    month = Integer.parseInt(stop.substring(4, 6));
                    day = Integer.parseInt(stop.substring(6, 8));
                    hour = Integer.parseInt(stop.substring(8, 10));
                    minute = Integer.parseInt(stop.substring(10, 12));
                    stopDate = new GregorianCalendar(year, month - 1, day, hour, minute);

                    if (now.compareTo(startDate) > 0 && now.compareTo(stopDate) < 0) {
//                        System.out.print(" [IN ONDA]: ");
//                    }
                        System.out.print("[" + formatter2.format(startDate.getTime()) +"-" + formatter2.format(stopDate.getTime()) + "]");
                        System.out.print(" (" + el.getAttribute("channel") + ")");

                        NodeList al = el.getElementsByTagName("title");
                        if (al != null && al.getLength() > 0) {
                            Element ol = (Element) al.item(0);
                            System.out.println(" --- " + ol.getFirstChild().getNodeValue());
                        }
                    }
                }
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
