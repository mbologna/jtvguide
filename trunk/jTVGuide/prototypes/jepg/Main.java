/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jepg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Michele
 */
public class Main {

    public static void main(String[] args) {
        try {
            final String xmltvExecutable = "xmltv.exe tv_grab_it";
            final String xmltvBasedir = "xmltv";
            final String xmltvConfigFile = "tv_grab_it.conf";
            final String xmltvParseFile = "tv_grab_it.xml";
            final int days = 3;

            ////////////////////////////////////////////////////////////////
            final String xmltvConfigFileAbsolute = xmltvBasedir + "/" + xmltvConfigFile;
            final String xmltvParseFileAbsolute = xmltvBasedir + "/" + xmltvParseFile;
            final String xmltvSwitches = "--days " + days + " " + "--config-file " + xmltvConfigFileAbsolute + " " + "--output " + xmltvParseFileAbsolute;
            //final String[] xmltvCommand = {xmltvExecutable, xmltvSwitches};
            File xmltvParseFileAbsoluteF = new File(xmltvParseFileAbsolute);
            Calendar now = new GregorianCalendar();
            now.setTime(new Date());
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String today = dateFormat.format(now.getTime());
            boolean found = false;

            if (xmltvParseFileAbsoluteF.exists() && xmltvParseFileAbsoluteF.canRead()) {
                System.out.println("File trovato");
                try {
                    Scanner scanner = new Scanner(xmltvParseFileAbsoluteF);
                    String line;
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        if (line.contains("start=\"" + today)) {
                            System.out.println("File aggiornato");
                            found = true;
                            break;
                        }
                    }
                    scanner.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!xmltvParseFileAbsoluteF.exists() || !xmltvParseFileAbsoluteF.canRead() || found == false) {
                System.out.println("File non aggiornato o non presente");
                int status = -1;
                String s;
                try {
                    Process p = Runtime.getRuntime().exec(xmltvExecutable + " " + xmltvSwitches);
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    p.waitFor();
                    status = p.exitValue();
                    if (status == 0) {
                        System.out.println("Esecuzione di " + xmltvExecutable + " " + xmltvSwitches + " eseguita correttamente");
                        while ((s = stdInput.readLine()) != null) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("Esecuzione di " + xmltvExecutable + " " + xmltvSwitches + " : errore");
                        while ((s = stdError.readLine()) != null) {
                            System.out.println(s);
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(xmltvParseFileAbsoluteF);

            NodeList nodes = doc.getChildNodes();
            System.out.println("There are " + nodes.getLength() + " total nodes.");

            int count = 0;
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = (Node) nodes.item(i);
                System.out.println(node.getNodeName() + " " + node.getNodeType() + ":" + node.getChildNodes().getLength());
            }

            Node rootNode = doc.getLastChild();
            nodes = rootNode.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = (Node) nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("|-" + node.getNodeName() + " " + node.getNodeType() + ":" + node.getChildNodes().getLength());
                }
            }

            Element docEle = doc.getDocumentElement();
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
                    Calendar startDate = new GregorianCalendar(year, month - 1, day, hour, minute);
                    
                    String stop = el.getAttribute("stop");
                    year = Integer.parseInt(stop.substring(0, 4));
                    month = Integer.parseInt(stop.substring(4, 6));
                    day = Integer.parseInt(stop.substring(6, 8));
                    hour = Integer.parseInt(stop.substring(8, 10));
                    minute = Integer.parseInt(stop.substring(10, 12));
                    Calendar stopDate = new GregorianCalendar(year, month - 1, day, hour, minute);
                    if (now.compareTo(startDate) > 0 && now.compareTo(stopDate) < 0) {
                        System.out.print("[" + timeFormat.format(startDate.getTime()) + "-" + timeFormat.format(stopDate.getTime()) + "]");
                        System.out.print(" (" + el.getAttribute("channel") + ")");

                        NodeList al = el.getElementsByTagName("title");
                        if (al != null && al.getLength() > 0) {
                            Element ol = (Element) al.item(0);
                            System.out.println(" --- " + ol.getFirstChild().getNodeValue());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
