package com.minegusta.mgchatstandalone.commands;

import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FilterCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) return true;

		Player p = (Player) sender;
		if(p.hasPermission("minegusta.chatfilter"))
		{
			if(args.length == 0)
			{
				if(ChatFilter.toggleFilter(p))
				{
					p.sendMessage(ChatColor.GREEN + "Your chat is now filtered from rude words.");
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Your chat is no longer filtered.");
				}
			}
			else
			{
				if(args[0].equalsIgnoreCase("off"))
				{
					ChatFilter.setFilter(p, false);
					p.sendMessage(ChatColor.RED + "Your chat is no longer filtered.");
				}
				else
				{
					ChatFilter.setFilter(p, true);
					p.sendMessage(ChatColor.GREEN + "Your chat is now filtered from rude words.");
				}
			}
		}
		return true;
	}
}
