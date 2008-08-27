package it.unibg.cs.jtvguide.xmltv;

public enum XMLTVGrabbersByCountry {
	EN ("tv_grab_en"),
	IT ("tv_grab_it");
	
	private final String COMMAND;

	private XMLTVGrabbersByCountry(String command) {
		this.COMMAND = command;
	}

	public String getGrabberByCountry() {
		return this.COMMAND;
	}
}
