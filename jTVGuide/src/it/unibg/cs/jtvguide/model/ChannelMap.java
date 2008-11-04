package it.unibg.cs.jtvguide.model;

import it.unibg.cs.jtvguide.log.PublicLogger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * Attenzione: usare URI perche' l'hashCode di un URL e' basato sugli IP, e 
 * non sulla stringa. Quindi, nel caso di URL con lo stesso IP, avremo un
 * hash uguale (collisione non riportata da Java):
 * michele@michele-desktop:~$ host www.rete4.com
 * www.rete4.com has address 213.215.144.113
 * michele@michele-desktop:~$ host www.italia1.com
 * www.italia1.com has address 213.215.144.113
 * michele@michele-desktop:~$ host www.la7.it
 * www.la7.it has address 83.221.117.94

 * java.net.URL b = new java.net.URL("http://www.rete4.com/");
 * java.net.URL c = new java.net.URL("http://www.italia1.com/");
 * java.net.URL d = new java.net.URL("http://www.la7.it");
 * System.out.println(a.hashCode());
 * System.out.println(b.hashCode());
 * System.out.println(c.hashCode());
 * System.out.println(d.hashCode());

 * -704079496
 * -704079496
 * -704079496
 * 1410235958

 * Usando la classe URI, il problema non si presenta.
 */

/**
 * A class to contain channels, indexed by channel IDs
 * @author Michele Bologna, Sebastiano Rota
 */
public class ChannelMap {

	private Map<URI, Channel> channelMap;

	public ChannelMap() {
		this.channelMap = new HashMap<URI, Channel>();
	}

	/**
	 * Add a channel to the channelmap
	 * @param id the id of the channel
	 * @param e the channel to add
	 */
	public void add(String id, Channel e) {
		try {
			this.channelMap.put(new URI("http://" + id), e);
		} catch (URISyntaxException e1) {
			PublicLogger.getLogger().error(e1);
		}
	}
	
	/**
	 * Add a channel to the channelmap
	 * @param id the id of the channel
	 * @param e the channel to add
	 */
	public void add(URI id, Channel e) {
		this.channelMap.put(id, e);
	}
	
	/**
	 * Test if the channel is already in the map
	 * @param id the id of the channel
	 * @return true if the channel is already in, false otherwise
	 */
	public boolean contains(String id) {
		try {
			return this.channelMap.containsKey(new URI("http://" + id));
		} catch (URISyntaxException e) {
			PublicLogger.getLogger().error(e);
		}
		return false;
	}

	/**
	 * Test if the channel is already in the map
	 * @param id the id of the channel
	 * @return true if the channel is already in, false otherwise
	 */
	public boolean contains(URI id) {
		return this.channelMap.containsKey(id);
	}

	/**
	 * Returns the occurrence of the channel specified
	 * @param id the id of the channel
	 * @return the occurrence of the channel if found, false otherwise
	 */
	public Channel get(String id) {
		try {
			return this.channelMap.get(new URI("http://" + id));
		} catch (URISyntaxException e) {
			PublicLogger.getLogger().error(e);
		}
		return null;
	}

	/**
	 * Returns the occurrence of the channel specified
	 * @param id the id of the channel
	 * @return the occurrence of the channel if found, false otherwise
	 */
	public Channel get(URI id) {
		return this.channelMap.get(id);
	}
	/**
	 * Returns an iterator to cycle over the map
	 * @return iterator to cycle over the map
	 */
	public Iterator<URI> iterator() {
		return this.channelMap.keySet().iterator();
	}
}
