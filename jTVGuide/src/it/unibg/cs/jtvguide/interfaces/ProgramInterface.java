package it.unibg.cs.jtvguide.interfaces;

import java.util.Date;

public interface ProgramInterface extends Comparable<ProgramInterface> {
	public String getDesc();

	public void setDesc(String desc);
	
	public int compareTo(ProgramInterface o);
		
	public ChannelInterface getChannel();
	
	public int getCompletionPercentile();

	public String getInfo();

	public Date getStartDate();

	public long getStartingTime();

	public Date getStopDate();

	public String getTitle();

	public void setChannel(ChannelInterface val);

	public void setStartDate(Date val);

	public void setStopDate(Date val);

	public void setTitle(String val);
}
