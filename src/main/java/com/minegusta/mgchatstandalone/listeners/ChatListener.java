package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.util.Formatter;
import com.minegusta.mgchatstandalone.util.MessageSender;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEvent(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled()) return;

		if(MuteHandler.isMuted(e.getPlayer().getName()))
		{
			e.getPlayer().sendMessage(ChatColor.YELLOW + "You are muted!");
			e.getPlayer().sendMessage(ChatColor.YELLOW + "You can talk again in " + MuteHandler.getMute(e.getPlayer().getName()).getRemainingMinutes() + " minutes.");
			e.setCancelled(true);
			return;
		}

		String message = Formatter.formatMessage(e.getPlayer(), e.getMessage());

		e.setCancelled(true);
		e.setMessage("");

		Bukkit.getOnlinePlayers().stream().forEach(p -> p.sendMessage(message));
		MessageSender.sendMessageToServers(message, e.getPlayer().getName());
	}
}
