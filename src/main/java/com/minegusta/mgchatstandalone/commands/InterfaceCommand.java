package com.minegusta.mgchatstandalone.commands;

import com.minegusta.mgchatstandalone.commandInterface.InterfaceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InterfaceCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender.hasPermission("minegusta.chatinterface") && sender instanceof Player)
		{
			if(args.length < 1)
			{
				return true;
			}

			String target = args[0];
			InterfaceManager.openInterface((Player) sender, target);

			return true;
		}

		return true;
	}
}
