package com.minegusta.mgchatstandalone.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class MessageListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {

		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();

		if (subchannel.equals("MGChat")) {

			try {
				short len = in.readShort();
				byte[] msgbytes = new byte[len];
				in.readFully(msgbytes);
				DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));

				String receivedMessage = ChatColor.translateAlternateColorCodes('&', msgin.readUTF());
				String[] servers = msgin.readUTF().split(" ");

				for (String s : servers) {
					if (s.equalsIgnoreCase(ConfigHandler.SERVER_NAME)) {
						Bukkit.getOnlinePlayers().stream().forEach(p -> p.sendMessage(receivedMessage));
						break;
					}
				}
			} catch (Exception ignored) {}
		}
	}
}
