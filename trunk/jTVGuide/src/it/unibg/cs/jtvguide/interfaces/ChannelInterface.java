package it.unibg.cs.jtvguide.interfaces;

import java.net.URI;

public interface ChannelInterface extends Comparable<ChannelInterface>{

	int compareTo(ChannelInterface o);

	boolean equals(Object o);

	int hashCode();

	String getDisplayName();

	URI getId();

	void setDisplayName(String name);

	void setId(URI id);

	String toString();
}
