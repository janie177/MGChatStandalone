package com.minegusta.mgchatstandalone.commands;

import com.minegusta.mgchatstandalone.util.MessageSender;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhisperCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

		if(!s.hasPermission("minegusta.whisper"))
		{
			s.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}

		if(args.length < 2)
		{
			s.sendMessage(ChatColor.GRAY + "To whisper someone, use the following format:");
			s.sendMessage(ChatColor.GRAY + "/Msg [player] [message]");
			return true;
		}

		if(!(s instanceof Player))
		{
			s.sendMessage("Only players can whisper because of the bungee thing. Suck it.");
			return true;
		}

		Player p = (Player) s;

		if(MuteHandler.isMuted(p.getName()))
		{
			p.sendMessage(ChatColor.YELLOW + "You are muted!");
			p.sendMessage(ChatColor.YELLOW + "You can talk again in " + (MuteHandler.getMute(p.getName()).getRemainingMinutes() + 1) + " minutes.");
			return true;
		}

		try
		{
			String name = args[0];

			String msg = "";

			for(int i = 1; i < args.length; i++)
			{
				msg = msg + " "  + args[i];
			}

			MessageSender.sendPlayerMessage(p, name, msg);

			ReplyCommand.setReply(p.getName(), name);
			ReplyCommand.setReply(name, p.getName());

			p.sendMessage(ChatColor.GRAY + "[MSG]Me -> " + ChatColor.GOLD + name + ChatColor.GRAY + ": " + msg);


		} catch (Exception ignored)
		{
			s.sendMessage(ChatColor.GRAY + "That person could not be found.");
		}

		return true;
	}
}
