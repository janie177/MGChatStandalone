package com.minegusta.mgchatstandalone.commands;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentMap;

public class FactionsChatCommand implements CommandExecutor {

	private static ConcurrentMap<String, Boolean> fChatMap = Maps.newConcurrentMap();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return true;

		Player p = (Player) sender;

		if (sender.hasPermission("minegusta.factionchat")) {
			if(hasFChatEnabled(p))
			{
				setFChat(p, false);
			}
			else {
				setFChat(p, true);
			}
		} else
		{
			if(hasFChatEnabled(p))
			{
				setFChat(p, false);
			}
			else sender.sendMessage(ChatColor.RED + "You have no permission to use this command.");

		}
		return true;
	}


	public static boolean hasFChatEnabled(Player p)
	{
		return fChatMap.containsKey(p.getName()) && fChatMap.get(p.getName());
	}

	public static void setFChat(Player p, boolean chat)
	{
		fChatMap.put(p.getName(), chat);
		if(chat)
		{
			p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.LIGHT_PURPLE + "Factions Chat Enabled.");
		} else {
			p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.RED + "Factions Chat Disabled.");
		}
	}
}
