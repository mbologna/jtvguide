package it.unibg.cs.jtvguide.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Channel implements Comparable<Channel> {

	private String displayName;
	private URI id;

	public Channel(String id, String displayName) throws ParseException {

		try {
			this.id = new URI("http://" + id);
		} catch (URISyntaxException e) {
			throw new ParseException("could not parse channel", 0);
		}

		this.displayName = displayName;
	}

	@Override
	public int compareTo(Channel o) {
		return displayName.compareTo(o.getDisplayName());
	}

	public boolean equals(Object o) {
		Channel c = (Channel) o;
		if (c.getId() == this.id)
			return true;
		else
			return false;
	}

	public String getDisplayName() {
		return displayName;
	}

	public URI getId() {
		return id;
	}

	public void setDisplayName(String name) {
		this.displayName = name;
	}

	public void setId(URI id) {
		this.id = id;
	}

	public String toString() {
		return displayName;
	}
}
