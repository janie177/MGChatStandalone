package com.minegusta.mgchatstandalone.commands;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentMap;

public class FactionsChatCommand implements CommandExecutor {

	private static ConcurrentMap<String, Integer> fChatMap = Maps.newConcurrentMap();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return true;

		Player p = (Player) sender;

		if (sender.hasPermission("minegusta.factionchat")) {
			if(getFChatType(p) == 1)
			{
				setFChat(p, 2);
			}
			else if (getFChatType(p) > 1) {
				setFChat(p, 0);
			}
			else
			{
				setFChat(p, 1);
			}
		} else
		{
			if(getFChatType(p) != 0)
			{
				setFChat(p, 0);
			}
			else sender.sendMessage(ChatColor.RED + "You have no permission to use this command.");

		}
		return true;
	}


	/**
	 * Get the f chat type.
	 * @param p The player to get it for.
	 * @return 0 for normal chat, 1 for faction chat and 2 for ally chat.
	 */
	public static int getFChatType(Player p)
	{
		if(fChatMap.containsKey(p.getName()))
		{
			return fChatMap.get(p.getName());
		}
		return 0;
	}

	public static void setFChat(Player p, int chat)
	{
		fChatMap.put(p.getName(), chat);
		if(chat == 1) {
			p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.GREEN + "Factions Chat Enabled.");
		} else if(chat > 1) {
			p.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "AC" + ChatColor.DARK_PURPLE + "] " + ChatColor.GREEN + "Ally chat enabled.");
		} else {
			p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.RED + "Factions Chat Disabled.");
		}
	}
}
