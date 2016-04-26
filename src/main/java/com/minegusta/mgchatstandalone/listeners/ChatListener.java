package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.util.Formatter;
import com.minegusta.mgchatstandalone.util.MessageSender;
import org.bukkit.Bukkit;
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

		e.setCancelled(true);
		e.setMessage("");

		Bukkit.getOnlinePlayers().stream().forEach(p -> p.sendMessage(message));
		MessageSender.sendMessageToServers(message);
	}
}
