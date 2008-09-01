package it.unibg.cs.jtvguide.xmltv;

public enum XMLTVGrabbersByCountry {
	ARGENTINA("ar","tv_grab_ar"),
	BELGIUM("be","tv_grab_be"),
	BRAZIL("br","tv_grab_br_net"),
	CROATIA("hr","tv_grab_hr"),
	DENMARK("dk","tv_grab_dk"),
	ESTONIA("ee","tv_grab_ee"),
	FINLAND("fi","tv_grab_fi"),
	FRANCE("fr","tv_grab_fr"),
	HOLLAND("nl","tv_grab_nl_wolf"),
	HUNGARY("hu","tv_grab_huro"),
	ISRAEL("is","tv_grab_il"),
	ITALY ("it","tv_grab_it"),
	JAPAN ("jp","tv_grab_jp"),
	NORWAY("no","tv_grab_no_gfeed"),
	PORTUGAL("pt", "tv_grab_pt"),
	REUNION_ISLAND("re","tv_grab_re"),
	ROMANIA("ro","tv_grab_huro"),
	SOUTH_AFRICA("za","tv_grab_za"),
	SPAIN("es","tv_grab_es"),
	SWEDEN("se","tv_grab_se_swedb"),
	SWITZERLAND("ch","tv_grab_ch_search"),
	UNITED_KINGDOM("en","tv_grab_uk_rt");
	
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
