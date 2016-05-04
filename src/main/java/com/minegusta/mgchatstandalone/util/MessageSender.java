package com.minegusta.mgchatstandalone.util;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class MessageSender {

	public static void sendMessageToServers(String message, String playerName)
	{
		try {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Forward");
			out.writeUTF("ALL");
			out.writeUTF("MGChat");

			Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

			ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
			DataOutputStream msgout = new DataOutputStream(msgbytes);

			msgout.writeUTF(message);
			msgout.writeUTF(ConfigHandler.SEND_TO);
			msgout.writeUTF(playerName);
			msgout.writeLong(System.currentTimeMillis());

			out.writeShort(msgbytes.toByteArray().length);
			out.write(msgbytes.toByteArray());

			player.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());

		} catch (Exception ignored) {}
	}

	public static void sendUpdatePlayerList()
	{
		try {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Forward");
			out.writeUTF("ALL");
			out.writeUTF("PlayerListUpdate");

			Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
			player.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());

		} catch (Exception ignored) {}
	}

	public static void sendPlayerMessage(Player from, String player, String message)
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try
		{
			out.writeUTF("Message");
			out.writeUTF(player);
			out.writeUTF(ChatColor.GRAY + "[MSG] " + ChatColor.GOLD + from.getName() + ChatColor.GRAY + " -> Me: " + message);
		}
		catch (Exception ignored){}

		from.sendPluginMessage(Main.getPlugin(), "BungeeCord", b.toByteArray());
	}
}
