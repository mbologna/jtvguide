package it.unibg.cs.jtvguide.interfaces;

import java.util.Date;

public interface ProgramInterface extends Comparable<ProgramInterface> {
	String getDesc();

	void setDesc(String desc);
	
	int compareTo(ProgramInterface o);
		
	ChannelInterface getChannel();
	
	int getCompletionPercentile();

	String getInfo();

	Date getStartDate();

	long getStartingTime();

	Date getStopDate();

	String getTitle();

	void setChannel(ChannelInterface val);

	void setStartDate(Date val);

	void setStopDate(Date val);

	void setTitle(String val);
}
