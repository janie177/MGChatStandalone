package com.minegusta.mgchatstandalone.util;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.util.RelationUtil;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FactionColorUtil {

	public static TextComponent translateFactionColor(TextComponent input, Player pl, Player p)
	{
		MPlayer player1 = MPlayer.get(pl);
		MPlayer player2 = MPlayer.get(p);
		ChatColor color = RelationUtil.getColorOfThatToMe(player1, player2);
		String legacyText = input.toLegacyText();
		legacyText = legacyText.replace("$factioncolor$", color + "");
		TextComponent backToComponent = TextComponentUtil.stringToComp(legacyText);
		backToComponent.setHoverEvent(input.getHoverEvent());
		backToComponent.setClickEvent(input.getClickEvent());


		return backToComponent;
	}
}
