package it.unibg.cs.jtvguide.xmltv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Vector;

public class XMLTVConfigurator /*implements XMLTVHelper*/ {

	private static Vector<String> channelNameVector = new Vector<String>();
	private static Vector<Boolean> channelSelectedVector = new Vector<Boolean>();

    public XMLTVConfigurator () {
    }

    public static void config () {

    	File xmltvConfig = UserPreferences.getXmltvConfigFile();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(xmltvConfig));
			String lineRead = null;
			Vector<String> lineWritten = new Vector<String>();
			int lineNumber = 0;
			while ((lineRead = reader.readLine()) != null) {
				if(lineRead.startsWith("#") && channelSelectedVector.get(lineNumber))
					lineWritten.add(lineRead.substring(1));
				else if(lineRead.startsWith("#") && !channelSelectedVector.get(lineNumber))
					lineWritten.add(lineRead);
				else if(lineRead.startsWith("c") && channelSelectedVector.get(lineNumber))
					lineWritten.add(lineRead);
				else if(lineRead.startsWith("c") && !channelSelectedVector.get(lineNumber))
					lineWritten.add("#"+lineRead);
			lineNumber++;
	        }

			/* nel Vector lineWritten scrivo il file di configurazione modificato
			 * in base ai canali selezionati dall'utente
			 */

			PrintStream ps = new PrintStream(new FileOutputStream(xmltvConfig));

			for(int i=0;i<lineWritten.size();i++){
				System.setOut(ps);
				System.out.println(lineWritten.get(i));
			}

			// scrivo il file di configurazione


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void chargeFile(Vector<String> channelName, Vector<Boolean> channelSelected){
    	channelNameVector.removeAllElements();
    	channelSelectedVector.removeAllElements();
    	for(int i=0;i<channelName.size();i++){
    		channelNameVector.add(channelName.get(i));;
    		channelSelectedVector.add(channelSelected.get(i));
    	}
    	XMLTVConfigurator.config();
    }

    public static void chargeVectors(){

		File xmltvConfig = UserPreferences.getXmltvConfigFile();

		channelNameVector.removeAllElements();
    	channelSelectedVector.removeAllElements();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(xmltvConfig));
			String line = null;

			while ((line = reader.readLine()) != null) {
				if(line.startsWith("#"))
					channelSelectedVector.add(false);
				else channelSelectedVector.add(true);

				channelNameVector.add(line.substring(line.lastIndexOf("#")+2));

				/* in channelNameVector ho la lista dei canali,
				 * channelSelectedVector ha le stesse dimensioni di channelVector,
				 */
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void selectChannel(String channelName){
		channelSelectedVector.setElementAt(true,(channelNameVector.indexOf(channelName)));
    }

    public static void notSelectChannel(String channelName){
    	channelSelectedVector.setElementAt(false,(channelNameVector.indexOf(channelName)));
    }

    public static Vector<String> getChannelNameVector(){
    	return channelNameVector;
    }

    public static Vector<Boolean> getChannelSelectedVector(){
    	 return channelSelectedVector;
    }


}

