
public class XMLTVParser implements XMLTVHelper {

    private Schedule mSchedule;

    public XMLTVParser () {
    }

    public boolean parseXML () {
        return true;
    }

    public Schedule getSchedule () {
        return mSchedule;
    }

    public void setSchedule (Schedule val) {
        this.mSchedule = val;
    }

}

