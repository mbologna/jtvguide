package data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Schedule {

    private Program mProgram;
    private List<Program> scheduleList;

    //private XMLTVGrabber mXMLTVGrabber;

    public Schedule () {
    	scheduleList = new LinkedList<Program>();
    }
    
    public void add(Program p) {
    	scheduleList.add(p);
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
    
    public /*Program[]*/ void search(Date startDate, Date stopDate) {
    	return;
    }

/*    public XMLTVGrabber getXMLTVGrabber () {
        return mXMLTVGrabber;
    }

    public void setXMLTVGrabber (XMLTVGrabber val) {
        this.mXMLTVGrabber = val;
    }
 */

}

