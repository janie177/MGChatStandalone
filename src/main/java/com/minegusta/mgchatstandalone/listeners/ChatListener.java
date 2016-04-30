package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.util.Formatter;
import com.minegusta.mgchatstandalone.util.MessageSender;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class ChatListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEvent(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled()) return;

		if(MuteHandler.isMuted(e.getPlayer().getName()))
		{
			e.getPlayer().sendMessage(ChatColor.YELLOW + "You are muted!");
			e.getPlayer().sendMessage(ChatColor.YELLOW + "You can talk again in " + (MuteHandler.getMute(e.getPlayer().getName()).getRemainingMinutes() + 1) + " minutes.");
			e.setCancelled(true);
			return;
		}

		e.setMessage(e.getMessage().replace("%", ""));

		String message = e.getMessage();
		Player p = e.getPlayer();

		message = ChatColor.translateAlternateColorCodes('&', message);

		if(!p.hasPermission("minegusta.chatcolor"))
		{
			message = ChatColor.stripColor(message);
		}

		e.setMessage(message);

		String[] format = Formatter.formatMessage(p);
		e.setFormat(format[0] + e.getMessage());

		MessageSender.sendMessageToServers(format[1] + message, e.getPlayer().getName());
	}

	private static final List<String> blockedCMDS = ConfigHandler.getBlockedCMDS();

	@EventHandler
	public void onCommandSpam(PlayerCommandPreprocessEvent e)
	{
		Player p = e.getPlayer();
		if(MuteHandler.isMuted(p.getName()))
		{
			String command = e.getMessage().split(" ")[0].replace("/", "");
			for(String s : blockedCMDS)
			{
				if(s.equalsIgnoreCase(command))
				{
					e.setCancelled(true);
					p.sendMessage(ChatColor.YELLOW + "That command is blocked while you are muted.");
					break;
				}
			}
		}
	}
}
