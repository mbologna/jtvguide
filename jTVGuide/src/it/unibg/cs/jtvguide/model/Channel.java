package it.unibg.cs.jtvguide.model;

import java.net.URI;
import java.net.URISyntaxException;

public class Channel implements Comparable<Channel> {

	private String displayName;
	private URI id;
	
	public Channel(String id, String displayName) {
	
			try {
				this.id = new URI("http://" + id);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.displayName = displayName;
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

	public void setDisplayName(String name) {
		this.displayName = name;
	}

	public URI getId() {
		return id;
	}

	public void setId(URI id) {
		this.id = id;
	}

	@Override
	public int compareTo(Channel o) {
		return displayName.compareTo(o.getDisplayName());
	}
}
