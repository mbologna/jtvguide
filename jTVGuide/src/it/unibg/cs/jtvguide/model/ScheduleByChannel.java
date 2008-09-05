package it.unibg.cs.jtvguide.model;

import java.util.List;

public class ScheduleByChannel extends Schedule {
	
	@SuppressWarnings("unused")
	private ScheduleByChannel() { }
	
	public ScheduleByChannel(List<Program> scheduleList) {
		this.scheduleList = scheduleList;
	}

	@Override
	public void add(Program p) {
		if (!this.scheduleList.isEmpty()) {
			if (this.scheduleList.get(0).getChannel() == p.getChannel()) {
				scheduleList.add(p);
			}
		}
	}
}
