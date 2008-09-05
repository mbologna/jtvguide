package it.unibg.cs.jtvguide.model;

import java.util.List;

public class ScheduleByChannel extends Schedule {
	
	private final Channel c;
	
	public ScheduleByChannel(List<Program> scheduleList, Channel c) {
		super(scheduleList);
		this.c = c;
	}

	@Override
	public void add(Program p) {
		if (!this.scheduleList.isEmpty()) {
			if (this.c == p.getChannel()) {
				scheduleList.add(p);
			}
		}
	}
	
	public String getChannelName() {
		return c.getDisplayName();
	}
}
