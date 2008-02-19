
public class Channel {

    private String name;

    private Logo logo;

    private URL url;

    private int channelNumber;

    public Channel () {
    }

    public int getChannelNumber () {
        return channelNumber;
    }

    public void setChannelNumber (int val) {
        this.channelNumber = val;
    }

    public Logo getLogo () {
        return logo;
    }

    public void setLogo (Logo val) {
        this.logo = val;
    }

    public String getName () {
        return name;
    }

    public void setName (String val) {
        this.name = val;
    }

    public URL getUrl () {
        return url;
    }

    public void setUrl (URL val) {
        this.url = val;
    }

}

