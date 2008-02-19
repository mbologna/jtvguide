
public class Schedule {

    private Program mProgram;

    private XMLTVGrabber mXMLTVGrabber;

    public Schedule () {
    }

    public Program getProgram () {
        return mProgram;
    }

    public void setProgram (Program val) {
        this.mProgram = val;
    }

    public boolean upToDate () {
        return true;
    }

    public boolean update () {
        return true;
    }

    public XMLTVGrabber getXMLTVGrabber () {
        return mXMLTVGrabber;
    }

    public void setXMLTVGrabber (XMLTVGrabber val) {
        this.mXMLTVGrabber = val;
    }

}

