package it.unibg.cs.jtvguide.interfaces;

/**
 * An enum to match the human-representation of a Nation (e.g. ITALY) with the corresponding
 * XMLTV grabber (this is a command that will be used to download the schedule)
 * @author Michele Bologna, Sebastiano Rota
 *
 */

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
	
	/**
	 * specifing a locale, return the corresponding enum
	 * 
	 * @param locale - the locale detected automatically by the program
	 * @return the enum corresponding to the locale
	 */
	public static XMLTVGrabbersByCountry getXMLGrabbersByCountry(final String locale) {
		for (XMLTVGrabbersByCountry e : XMLTVGrabbersByCountry.values()) {
			if (locale.equals(e.getLOCALE())) {
				return e;
			}
		}
		return null;
	}

	private final String COMMAND, LOCALE;
	
	private XMLTVGrabbersByCountry(final String locale, final String command) {
		this.LOCALE = locale;
		this.COMMAND = command;
	}

	/**
	 * 
	 * @return the command to use 
	 *
	 */
	public String getCOMMAND() {
		return COMMAND;
	}
	
	/**
	 * 
	 * @return the locale specified
	 * 
	 */
	public String getLOCALE() {
		return LOCALE;
	}
}
