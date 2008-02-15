
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.11408E4E-1FAE-3F5D-ED5E-ECA0D3186445]
// </editor-fold> 
public class Program {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ABABC534-ABDF-0BEB-6302-28FA25E32697]
    // </editor-fold> 
    private String title;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E456E248-25B8-BE68-D5EC-70525DFEDA39]
    // </editor-fold> 
    private String description;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FC9AC9FB-EB23-C6BF-B74E-CB464E49BCE3]
    // </editor-fold> 
    private Date startDate;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9EDAF031-47FA-9F20-AD3E-4170EAF0F473]
    // </editor-fold> 
    private Date stopDate;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D0711CAA-9243-B33C-B956-BBC55D42DA65]
    // </editor-fold> 
    private Channel mChannel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DB828DE0-0596-E19A-AB74-7EE5B5E0BC4B]
    // </editor-fold> 
    public Program () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D66737BF-6016-130B-0988-C34667ED4E3B]
    // </editor-fold> 
    public String getDescription () {
        return description;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EC31C8C8-F7FA-4330-5513-A4656D286D77]
    // </editor-fold> 
    public void setDescription (String val) {
        this.description = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F5934F60-A3F6-35FB-16BC-68006E37AEA4]
    // </editor-fold> 
    public Channel getChannel () {
        return mChannel;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6D6356A5-BB4F-2AB1-CD3C-6A265B317BC0]
    // </editor-fold> 
    public void setChannel (Channel val) {
        this.mChannel = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EA84251E-7C5D-736C-53BD-0B09C67B7214]
    // </editor-fold> 
    public Date getStartDate () {
        return startDate;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.748C3BB0-518D-DE74-9589-7A400086E04B]
    // </editor-fold> 
    public void setStartDate (Date val) {
        this.startDate = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6E4C9533-7A7E-030A-062F-33BE969BB38A]
    // </editor-fold> 
    public Date getStopDate () {
        return stopDate;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4399C329-753E-34AB-0BB3-33AC52F9162B]
    // </editor-fold> 
    public void setStopDate (Date val) {
        this.stopDate = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F506C9AF-DF46-862A-D3E0-1FAFB5A38982]
    // </editor-fold> 
    public String getTitle () {
        return title;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4E187E04-B836-6E68-568A-B1161A19B9FD]
    // </editor-fold> 
    public void setTitle (String val) {
        this.title = val;
    }

}

