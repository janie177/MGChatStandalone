package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.util.Formatter;
import com.minegusta.mgchatstandalone.util.MessageSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEvent(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled()) return;
		String message = Formatter.formatMessage(e.getPlayer(), e.getMessage());

		e.setMessage(message);
		MessageSender.sendMessageToServers(message);
	}
}
