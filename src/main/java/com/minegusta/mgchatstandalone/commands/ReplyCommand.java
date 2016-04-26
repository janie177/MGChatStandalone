package com.minegusta.mgchatstandalone.commands;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentMap;

public class ReplyCommand implements CommandExecutor {

	private static ConcurrentMap<String, String> replyMap = Maps.newConcurrentMap();

	public static void setReply(String name, String to) {
		replyMap.put(name.toLowerCase(), to.toLowerCase());
	}

	public static String getForPlayer(String name)
	{
		return replyMap.get(name.toLowerCase());
	}

	public static boolean hasReplies(String playerName)
	{
		return replyMap.containsKey(playerName.toLowerCase());
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(!s.hasPermission("minegusta.whisper"))
		{
			s.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}

		if(args.length < 1)
		{
			s.sendMessage(ChatColor.RED + "Usage:");
			s.sendMessage(ChatColor.RED + "/Reply [Message]");
			return true;
		}

		if(!(s instanceof Player))
		{
			s.sendMessage(ChatColor.RED + "Consoles cannot use reply! Nice try! HAHAHAHHAAHAHAHAHAHA.");
			return true;
		}

		if(!hasReplies(s.getName()))
		{
			s.sendMessage(ChatColor.RED + "You have nobody to reply to.");
			return true;
		}

		String message = "";
		for(int i = 1; i < args.length; i++)
		{
			message = message + args[i] + " ";
		}

		Player p = (Player) s;

		String to = getForPlayer(p.getName());

		p.chat("/msg " + to + " " + message);
		return true;
	}
}
