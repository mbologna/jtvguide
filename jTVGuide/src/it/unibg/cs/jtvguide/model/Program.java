package it.unibg.cs.jtvguide.model;
import it.unibg.cs.jtvguide.util.DateFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




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
    
    public int getCompletionPercentile() {
        Calendar start = new GregorianCalendar();
    	Calendar stop = new GregorianCalendar();
    	Calendar now = Calendar.getInstance();
		stop.setTime(stopDate);
		start.setTime(startDate);
    	return (int) ((now.getTimeInMillis()-start.getTimeInMillis())
    			*100/
    			(stop.getTimeInMillis()-start.getTimeInMillis()));
    }
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append(DateFormatter.formatDate(startDate) + "-");
    	sb.append(DateFormatter.formatDate(stopDate));
    	if (getCompletionPercentile() >= 0)
    		sb.append("   ("+getCompletionPercentile()+"%)");
    	sb.append("   "+ title + "   (");
    	sb.append(mChannel.getDisplayName() + ")");
    	return sb.toString();
    }

}

