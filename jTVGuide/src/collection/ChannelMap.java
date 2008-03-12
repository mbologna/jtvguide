package collection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import data.Channel;


public class ChannelMap{
	
	private Map<URL, Channel> channelMap;
	
	public ChannelMap() {
		this.channelMap = new HashMap<URL,Channel>();
	}
	
	public void add(URL id, Channel e) {
		this.channelMap.put(id, e);
	}
	
	public void add(String id, Channel e) throws MalformedURLException {
		this.channelMap.put(new URL("http://"+id), e);
	}
	
	public boolean testIfPresent(URL id) {
		return this.channelMap.containsKey(id);
	}
	
	public boolean testIfPresent(String id) throws MalformedURLException {
		return this.channelMap.containsKey(new URL("http://"+id));
	}
	
	public Channel get(URL id) {
		return this.channelMap.get(id);
	}
	
	public Channel get(String id) throws MalformedURLException {
		return this.channelMap.get(new URL("http://"+id));
	}
	
}
