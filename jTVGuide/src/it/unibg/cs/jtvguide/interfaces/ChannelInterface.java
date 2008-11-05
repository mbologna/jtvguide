package it.unibg.cs.jtvguide.interfaces;

import java.net.URI;

public interface ChannelInterface extends Comparable<ChannelInterface>{

	public int compareTo(ChannelInterface o);

	public boolean equals(Object o);

	public int hashCode();

	public String getDisplayName();

	public URI getId();

	public void setDisplayName(String name);

	public void setId(URI id);

	public String toString();
}
