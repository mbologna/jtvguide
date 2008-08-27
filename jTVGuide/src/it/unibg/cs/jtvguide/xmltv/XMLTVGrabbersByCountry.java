package it.unibg.cs.jtvguide.xmltv;

public enum XMLTVGrabbersByCountry {
	JAPAN ("jp","tv_grab_jp"),
	ITALY ("it","tv_grab_it");
	
	private final String COMMAND, LOCALE;

	private XMLTVGrabbersByCountry(String locale, String command) {
		this.LOCALE = locale;
		this.COMMAND = command;
	}
	
	public String getCOMMAND() {
		return COMMAND;
	}

	public String getLOCALE() {
		return LOCALE;
	}

}
