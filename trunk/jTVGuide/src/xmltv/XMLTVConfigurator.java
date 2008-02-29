package xmltv;

import java.io.*;
import java.util.Vector;

public class XMLTVConfigurator /*implements XMLTVHelper*/ {

	private Vector<String> channelNameVector = new Vector<String>();;
	private Vector<Boolean> channelSelectedVector = new Vector<Boolean>();


    public XMLTVConfigurator () {
    }

    public void config () {

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

    public void chargeVectors(){

		File xmltvConfig = UserPreferences.getXmltvConfigFile();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(xmltvConfig));
			String line = null;

			while ((line = reader.readLine()) != null) {
				channelNameVector.add(line.substring(line.lastIndexOf("#")+2));
				channelSelectedVector.add(false);

				/* in channelNameVector ho la lista dei canali,
				 * channelSelectedVector ha le stesse dimensioni di channelVector,
				 * di default nessun canale è selezionato dal configuratore
				 */
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void selectChannel(String channelName){
		channelSelectedVector.setElementAt(true,(channelNameVector.indexOf(channelName)));
    }

    public void notSelectChannel(String channelName){
    	channelSelectedVector.setElementAt(false,(channelNameVector.indexOf(channelName)));
    }

    public Vector<String> getChannelNameVector(){
    	return channelNameVector;
    }

    public Vector<Boolean> getChannelSelectedVector(){
    	 return channelSelectedVector;
    }


}

