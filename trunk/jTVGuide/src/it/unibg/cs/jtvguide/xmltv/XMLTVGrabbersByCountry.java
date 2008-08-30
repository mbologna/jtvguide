package it.unibg.cs.jtvguide.xmltv;

public enum XMLTVGrabbersByCountry {
	JAPAN ("jp","tv_grab_jp"),
	ITALY ("it","tv_grab_it"),
	ARGENTINA("ar","tv_grab_ar"),
	BELGIUM("be","tv_grab_be"),
	BRASIL("br","tv_grab_br_net"),
	SWITZERLAND("ch","tv_grab_ch_search"),
	DENMARK("dk","tv_grab_dk"),
	ESTONIA("ee","tv_grab_ee"),
	SPAIN("es","tv_grab_es"),
	FINLAND("fi","tv_grab_fi"),
	FRANCE("fr","tv_grab_fr"),
	CROATIA("hr","tv_grab_hr"),
	HOLLAND("nl","tv_grab_nl_wolf"),
	NORWAY("no","tv_grab_no_gfeed"),
	PORTUGAL("pt", "tv_grab_pt"),
	SWEDEN("se","tv_grab_se_swedb"),
	UNITEDKINGDOM("uk","tv_grab_uk_rt");
	
	/*
	 *               tv_grab_es_miguiatv  tv_grab_se_swedb
                 tv_grab_eu_epgdata   tv_grab_uk_bleb
        tv_grab_fi           tv_grab_uk_rt
         tv_grab_fr           tv_grab_za
    tv_grab_ar           tv_grab_hr           tv_grep
    tv_grab_be           tv_grab_huro         tv_imdb
    tv_grab_br_net       tv_grab_it           tv_remove_some_overlapping
    tv_grab_ch_search    tv_grab_na_dd        tv_sort
    tv_grab_combiner     tv_grab_na_dtv       tv_split
    tv_grab_dk           tv_grab_na_icons     tv_to_latex
    tv_grab_dtv_la       tv_grab_nl_wolf      tv_to_potatoe
    tv_grab_ee           tv_grab_no_gfeed     tv_to_text
    tv_grab_es           tv_grab_pt           tv_validate_file
    tv_grab_es_laguiatv  tv_grab_re           tv_validate_grabber

	 */
	
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
