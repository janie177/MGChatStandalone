package com.minegusta.mgchatstandalone.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.util.JSonUtil;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import net.md_5.bungee.api.chat.TextComponent;
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

				String receivedMessage = msgin.readUTF();
				String filtered = msgin.readUTF();
				final TextComponent component = JSonUtil.jsonToComponent(receivedMessage);
				final TextComponent filteredComponent = JSonUtil.jsonToComponent(filtered);

				String[] servers = msgin.readUTF().split(" ");
				String playerName = msgin.readUTF();
				long time = msgin.readLong() + 5000;

				//Return if message is older than 5 seconds.
				if(time < System.currentTimeMillis()) return;

				if(MuteHandler.isMuted(playerName)) return;

				for (String s : servers) {
					if (s.equalsIgnoreCase(ConfigHandler.SERVER_NAME)) {
						Bukkit.getLogger().info(component.getText());
						Bukkit.getOnlinePlayers().forEach(pl ->
						{
							if(ChatFilter.hasFilter(pl))
							{
								pl.spigot().sendMessage(filteredComponent);
							}
							else
							{
								pl.spigot().sendMessage(component);
							}
						});
						break;
					}
				}

			} catch (Exception ignored) {}
		}
	}
}