package com.minegusta.mgchatstandalone.commands;

import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnMuteCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

		if(!s.hasPermission("minegusta.mute"))
		{
			s.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}


		if(args.length < 1)
		{
			s.sendMessage(ChatColor.RED + "Invalid syntax.");
			s.sendMessage(ChatColor.YELLOW + "/Unmute [name]");
			return true;
		}

		String name = args[0];

		if(MuteHandler.isMuted(name))
		{
			MuteHandler.unMute(name);
			s.sendMessage(ChatColor.YELLOW + "You unmuted " + name + ".");
			return true;
		}
		else {
			s.sendMessage(ChatColor.RED + "That player is not muted!");
			s.sendMessage(ChatColor.RED + "You have to type the name exactly right.");
			s.sendMessage(ChatColor.RED + "Mutes expire automatically after the period defined while muting.");
		}

		return true;
	}
}
