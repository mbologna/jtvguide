package it.unibg.cs.jtvguide.model;

import java.util.List;

/**
 * A particular schedule which behaves like a set: only programs that have the same channel 
 * @author Michele
 *
 */
public class ScheduleByChannel extends Schedule {

	private final Channel c;

	/**
	 * Build a new schedule
	 * @param scheduleList a list containing the programs
	 * @param c the channel specified
	 */
	public ScheduleByChannel(List<Program> scheduleList, Channel c) {
		super(scheduleList);
		this.c = c;
	}

	/**
	 * Adds a new program to the schedule. The method tests if the inserting program's channel is the same as the channel of the programs previously inserted
	 * If so, it adds the program. Otherwise, it won't add the program.
	 */
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
