
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.5E66BDD8-A5C7-9F81-3962-AFF9538BC118]
// </editor-fold> 
public class Schedule {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.161616BC-D669-CDA8-1F98-A65754A9CDD5]
    // </editor-fold> 
    private Program mProgram;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0C40E748-D96A-5C96-B4F9-4E8252295BE3]
    // </editor-fold> 
    private XMLTVGrabber mXMLTVGrabber;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4418A11E-C8B6-DE0B-BC24-49599239A684]
    // </editor-fold> 
    public Schedule () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E4A5A946-B7D8-F94F-B516-63635FA28B02]
    // </editor-fold> 
    public Program getProgram () {
        return mProgram;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.749ADF8F-9117-C83E-F1E4-18CBBEFF8035]
    // </editor-fold> 
    public void setProgram (Program val) {
        this.mProgram = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F7FD1B4F-08B0-F938-A999-CC26BA1DEAF7]
    // </editor-fold> 
    public boolean upToDate () {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D2A95D25-C5DF-3604-A5FA-822770AF1482]
    // </editor-fold> 
    public boolean update () {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4ED86DA9-190B-6A16-E74A-F7C7D477B2D7]
    // </editor-fold> 
    public XMLTVGrabber getXMLTVGrabber () {
        return mXMLTVGrabber;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0A240DE9-5290-343E-A8A0-032290453A73]
    // </editor-fold> 
    public void setXMLTVGrabber (XMLTVGrabber val) {
        this.mXMLTVGrabber = val;
    }

}

