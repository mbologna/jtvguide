package it.unibg.cs.jtvguide.data;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Channel {

	private String name;
	private int channelNumber;
	private URL id;
	private Icon icon;

	public Channel(String displayName, int number, String idURL, String iconURL) {
		this.name = displayName;
		this.channelNumber = number;
		try {
			this.id = new URL("http://" + idURL);
			this.icon = new ImageIcon(new URL(iconURL));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Channel(String displayName, String idURL) {
		this.name = displayName;
		this.channelNumber = 0;
		try {
			this.id = new URL("http://" + idURL);
			this.icon = null /* mettere un quadrato vuoto ? */;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(int channelNumber) {
		this.channelNumber = channelNumber;
	}

	public URL getId() {
		return id;
	}

	public void setId(URL id) {
		this.id = id;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
}
