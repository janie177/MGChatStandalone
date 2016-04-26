package com.minegusta.mgchatstandalone.util;

public class Rank {
	private int weight;
	private String display;
	private String name;

	public Rank(String name, String display, int weight)
	{
		this.name = name;
		this.display = display;
		this.weight = weight;
	}

	public int getWeight()
	{
		return weight;
	}

	public String getName()
	{
		return name;
	}

	public String getDisplay()
	{
		return display;
	}
}
