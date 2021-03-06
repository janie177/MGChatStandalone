package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import com.minegusta.mgchatstandalone.util.*;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class ChatListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onEvent(AsyncPlayerChatEvent e) {
		if (e.isCancelled()) return;

		if (MuteHandler.isMuted(e.getPlayer().getName())) {
			e.getPlayer().sendMessage(ChatColor.YELLOW + "You are muted!");
			e.getPlayer().sendMessage(ChatColor.YELLOW + "You can talk again in " + (MuteHandler.getMute(e.getPlayer().getName()).getRemainingMinutes() + 1) + " minutes.");
			e.setCancelled(true);
			return;
		}

		e.setMessage(e.getMessage().replace("%", "٪"));

		String message = e.getMessage();
		Player p = e.getPlayer();

		message = ChatColor.translateAlternateColorCodes('&', message);

		if (!p.hasPermission("minegusta.chatcolor")) {
			message = ChatColor.stripColor(message);
		}

		e.setMessage(message);

		e.getRecipients().clear();

		TextComponent[] format = Formatter.formatMessage(p, message);

		if (!Main.FACTIONS_ENABLED) {
			Bukkit.getOnlinePlayers().forEach(pl -> {
				if (ChatFilter.hasFilter(pl)) {
					pl.spigot().sendMessage(format[2]);
				} else {
					pl.spigot().sendMessage(format[0]);
				}
			});
		} else
		{
			Bukkit.getOnlinePlayers().forEach(pl -> {
				if(ChatFilter.hasFilter(pl))
				{
					pl.spigot().sendMessage(FactionColorUtil.translateFactionColor(format[2], pl, p));
				}
				else
				{
					pl.spigot().sendMessage(FactionColorUtil.translateFactionColor(format[0], pl, p));
				}
			});
		}



		MessageSender.sendMessageToServers(JSonUtil.componentToString(format[1]), JSonUtil.componentToString(format[3]), e.getPlayer().getName());
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