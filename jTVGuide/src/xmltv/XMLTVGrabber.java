
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.EB9E395E-C248-AA12-1B35-BC72B983F573]
// </editor-fold> 
public class XMLTVGrabber implements XMLTVHelper {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.48263C8B-41F9-D809-56E1-80B8D9745791]
    // </editor-fold> 
    private XMLTVParser mXMLTVParser;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C1E48814-8479-7812-970C-7951B4A1A72F]
    // </editor-fold> 
    public XMLTVGrabber () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.61BB927D-8543-3DCB-B06A-A2037B51C911]
    // </editor-fold> 
    public boolean grabSchedule (XMLTVHelper options) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EDA2BAD7-BFEC-5880-36EB-D1CE8CF870D7]
    // </editor-fold> 
    public XMLTVParser getXMLTVParser () {
        return mXMLTVParser;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.759DB021-5021-9B98-84C2-55E5FA01CF83]
    // </editor-fold> 
    public void setXMLTVParser (XMLTVParser val) {
        this.mXMLTVParser = val;
    }

}

