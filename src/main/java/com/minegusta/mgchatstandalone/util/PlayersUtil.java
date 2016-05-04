package com.minegusta.mgchatstandalone.util;

import com.google.common.collect.Lists;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.minegusta.mgchatstandalone.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlayersUtil implements PluginMessageListener {

	private static List<String> players = Lists.newArrayList();

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {

		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();

		if (subchannel.equals("PlayerList")) {
			try {
				String server = in.readUTF(); // The name of the server you got the player list of, as given in args.
				String[] playerList = in.readUTF().split(", ");
				players = Arrays.asList(playerList);
			} catch (Exception ignored) {
			}
		}
		else if(subchannel.equals("PlayerListUpdate"))
		{
			updatePlayers();
		}
	}

	public static void updatePlayers()
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try
		{
			out.writeUTF("PlayerList");
			out.writeUTF("ALL");
		}
		catch (Exception ignored){
		}

		Optional<? extends Player> p = Bukkit.getOnlinePlayers().stream().findAny();
		if(p.isPresent()) p.get().sendPluginMessage(Main.getPlugin(), "BungeeCord", b.toByteArray());
	}

	public static List<String> getPlayers()
	{
		return players;
	}
}
