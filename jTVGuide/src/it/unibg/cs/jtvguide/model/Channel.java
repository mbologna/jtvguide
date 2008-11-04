package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.log.PublicLogger;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
/**
 * Class to depict a television channel entity
 * @author Michele Bologna, Sebastiano Rota
 *
 */
public class Channel implements Comparable<Channel> {

	private String displayName;
	private URI id;

	/**
	 * Construct a new channel
	 * @param id an unique-id to identify the channel (tipically its URL)
	 * @param displayName an human-friendly display name for the channel
	 * @throws ParseException
	 */
	public Channel(String id, String displayName) throws ParseException {

		try {
			this.id = new URI("http://" + id);
		} catch (URISyntaxException e) {
			PublicLogger.getLogger().error(e);
			throw new ParseException("could not parse channel", 0);
		}

		this.displayName = displayName;
	}

	/**
	 * Compare two channels
	 */
	@Override
	public int compareTo(Channel o) {
		return displayName.compareTo(o.getDisplayName());
	}
	/**
	 * Compare two channels
	 */
	public boolean equals(Object o) {
		Channel c = (Channel) o;
		if (c != null) {
			if (c.getId() == this.id)
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return super.hashCode();
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
