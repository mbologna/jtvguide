package data;
import java.util.Date;

import util.DateFormatter;



public class Program {

    private String title;
    private Date startDate;
    private Date stopDate;
    private Channel mChannel;

    public Program (Date startDate, Date stopDate, Channel c, String title) {
    	this.startDate = startDate;
    	this.stopDate = stopDate;
    	this.mChannel = c;
    	this.title = title;
    }

    public Channel getChannel () {
        return mChannel;
    }

    public void setChannel (Channel val) {
        this.mChannel = val;
    }

    public Date getStartDate () {
        return startDate;
    }

    public void setStartDate (Date val) {
        this.startDate = val;
    }

    public Date getStopDate () {
        return stopDate;
    }

    public void setStopDate (Date val) {
        this.stopDate = val;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String val) {
        this.title = val;
    }
    
    public String toString() {
    	return  DateFormatter.formatDate(startDate) + "-" +
    			DateFormatter.formatDate(stopDate) + "\t\t" +
    			title + "\t\t[" +
    			mChannel.getName() + "]";
    }

}

