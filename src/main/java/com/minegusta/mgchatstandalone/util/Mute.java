package com.minegusta.mgchatstandalone.util;

import java.util.concurrent.TimeUnit;

public class Mute {

	private long duration;
	private long start;
	private String name;

	private Mute(String name, long duration)
	{
		this.duration = duration;
		this.name = name;
		this.start = System.currentTimeMillis();
	}

	private Mute(String name, long duration, long start)
	{
		this.duration = duration;
		this.name = name;
		this.start = start;
	}

	public long getRemainingMinutes()
	{
		return TimeUnit.MILLISECONDS.toMinutes((duration + start) - System.currentTimeMillis());
	}

	public static Mute create(String name, long duration)
	{
		return new Mute(name, duration);
	}

	public static Mute create(String name, long duration, long start)
	{
		return new Mute(name, duration, start);
	}

	public long getDuration()
	{
		return duration;
	}

	public long getStart()
	{
		return start;
	}

	public String getName()
	{
		return name;
	}

	public boolean isExpired()
	{
		return ((start + duration) - System.currentTimeMillis()) < 0;
	}
}
