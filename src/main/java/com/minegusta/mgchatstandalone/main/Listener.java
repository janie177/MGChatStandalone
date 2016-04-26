package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.listeners.ChatListener;

public enum Listener {
	ChatListener(new ChatListener());

	private org.bukkit.event.Listener l;

	Listener(org.bukkit.event.Listener l)
	{
		this.l = l;
	}

	public org.bukkit.event.Listener getListener()
	{
		return l;
	}
}
