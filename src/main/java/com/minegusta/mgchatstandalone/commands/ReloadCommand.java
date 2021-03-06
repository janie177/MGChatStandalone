package com.minegusta.mgchatstandalone.commands;

import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import com.minegusta.mgchatstandalone.chatfilter.ChatFilterFileManager;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp() || sender.hasPermission("minegusta.reloadchat"))
		{
			MuteHandler.saveMutes();
			MuteHandler.createOrLoadMuteFile(Main.getPlugin());
			MuteHandler.loadMutes();
			Main.getPlugin().reloadConfig();
			ConfigHandler.readConfig(Main.getPlugin().getConfig());
			ChatFilterFileManager.createOrLoadMuteFile(Main.getPlugin());
			ChatFilter.init();
			sender.sendMessage(ChatColor.GREEN + "Reloaded MGChat Standalone");
		}
		return true;
	}
}
