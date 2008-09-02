package it.unibg.cs.jtvguide.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Channel {

	private String displayName;
	private URL id;
	
	public Channel(String displayName, String id) {
		try {
			this.id = new URL("http://" + id);
		} catch (MalformedURLException e) {
			e.getMessage();
		}
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String name) {
		this.displayName = name;
	}

	public URL getId() {
		return id;
	}

	public void setId(URL id) {
		this.id = id;
	}
}
