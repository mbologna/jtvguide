
public class Program {

    private String title;

    private String description;

    private Date startDate;

    private Date stopDate;

    private Channel mChannel;

    public Program () {
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String val) {
        this.description = val;
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

}

