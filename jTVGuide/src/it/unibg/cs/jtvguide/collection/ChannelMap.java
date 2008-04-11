package it.unibg.cs.jtvguide.collection;

import it.unibg.cs.jtvguide.data.Channel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


/*
 * Attenzione: usare URI perché l'hashCode di un URL è basato sugli IP, e 
 * non sulla stringa. Quindi, nel caso di URL con lo stesso IP, avremo un
 * hash uguale (collisione non riportata da Java):
 * Nome:    www.rete4.com
 * Address:  213.215.144.113
 * Nome:    www.italia1.com
 * Address:  213.215.144.113

 * java.net.URL a = new java.net.URL("http://www.canale5.com/");
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

public class ChannelMap {

	private Map<URI, Channel> channelMap;

	public ChannelMap() {
		this.channelMap = new HashMap<URI, Channel>();
	}

	public void add(URI id, Channel e) {
		this.channelMap.put(id, e);
	}

	public void add(String id, Channel e) throws URISyntaxException {
		this.channelMap.put(new URI("http://" + id), e);
	}

	public boolean testIfPresent(URI id) {
		return this.channelMap.containsKey(id);
	}

	public boolean testIfPresent(String id) throws URISyntaxException {
		return this.channelMap.containsKey(new URI("http://" + id));
	}

	public Channel get(URI id) {
		return this.channelMap.get(id);
	}

	public Channel get(String id) throws URISyntaxException {
		return this.channelMap.get(new URI("http://" + id));
	}

}
