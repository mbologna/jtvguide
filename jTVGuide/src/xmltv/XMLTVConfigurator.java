package xmltv;

import java.io.*;
import java.util.Vector;

public class XMLTVConfigurator /*implements XMLTVHelper*/ {

    public XMLTVConfigurator () {
    }

    public void config () {

    	File xmltvConfig = UserPreferences.getXmltvConfigFile();
    	Vector<String> channelVector = new Vector<String>();
    	Vector<Boolean> channelSelected = new Vector<Boolean>();
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(xmltvConfig));
			String line = null;
			while ((line = reader.readLine()) != null) {
				channelVector.add(line.substring(line.lastIndexOf("#")+2));
				channelSelected.add(false);
				/* in channelVector ho la lista dei canali,
				 * channelSelected ha le stesse dimensioni di channelVector,
				 * di default nessun canale è selezionato dal cconfiguratore
				 */
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }



}

