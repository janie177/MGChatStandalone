package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.util.MessageSender;
import com.minegusta.mgchatstandalone.util.PlayersUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onEvent(PlayerJoinEvent e)
	{
		PlayersUtil.updatePlayers();
		MessageSender.sendUpdatePlayerList();
	}

	@EventHandler
	public void onEvent(PlayerQuitEvent e)
	{
		PlayersUtil.updatePlayers();
		MessageSender.sendUpdatePlayerList();
	}
}
