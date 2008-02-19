
public class XMLTVGrabber implements XMLTVHelper {

    private XMLTVParser mXMLTVParser;

    public XMLTVGrabber () {
    }

    public boolean grabSchedule (XMLTVHelper options) {
        return true;
    }

    public XMLTVParser getXMLTVParser () {
        return mXMLTVParser;
    }

    public void setXMLTVParser (XMLTVParser val) {
        this.mXMLTVParser = val;
    }

}

