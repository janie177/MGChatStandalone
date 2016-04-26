package com.minegusta.mgchatstandalone.commands;

import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.TimeUnit;

public class MuteCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

		if(!s.hasPermission("minegusta.mute"))
		{
			s.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}


		if(args.length < 2)
		{
			s.sendMessage(ChatColor.RED + "Wrong syntax.");
			s.sendMessage(ChatColor.YELLOW + "/Mute [name] [duration(minutes)]");
			return true;
		}

		try {
			int duration = Integer.valueOf(args[1]);
			String name = args[0];

			MuteHandler.mute(name, TimeUnit.MINUTES.toMillis(duration));

			s.sendMessage(ChatColor.YELLOW + "You have muted " + name + " for " + duration + " minutes.");

		} catch (Exception ignored) {
			s.sendMessage(ChatColor.RED + "Wrong syntax or invalid player.");
			s.sendMessage(ChatColor.YELLOW + "/Mute [name] [duration(minutes)]");
		}
		return true;
	}
}
