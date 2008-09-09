package it.unibg.cs.jtvguide.model;

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

public class ChannelMap {

	private Map<URI, Channel> channelMap;

	public ChannelMap() {
		this.channelMap = new HashMap<URI, Channel>();
	}

	public void add(String id, Channel e) {
		try {
			this.channelMap.put(new URI("http://" + id), e);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	public void add(URI id, Channel e) {
		this.channelMap.put(id, e);
	}

	public boolean contains(String id) {
		try {
			return this.channelMap.containsKey(new URI("http://" + id));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean contains(URI id) {
		return this.channelMap.containsKey(id);
	}

	public Channel get(String id) {
		try {
			return this.channelMap.get(new URI("http://" + id));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Channel get(URI id) {
		return this.channelMap.get(id);
	}

	public Iterator<URI> iterator() {
		return this.channelMap.keySet().iterator();
	}
}
