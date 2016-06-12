
package com.minegusta.mgchatstandalone.util;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import com.minegusta.mgracesredone.main.Races;
import net.md_5.bungee.api.chat.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.time.LocalTime;
import java.util.List;

public class Formatter {

	public static TextComponent[] formatMessage(Player p, String message)
	{
		TextComponent[] result = new TextComponent[4];

		String displayName = p.getDisplayName();
		String server = ConfigHandler.SERVER_NAME_IN_CHAT;
		String rank = "";

		if(!displayName.equals(p.getName()))
		{
			rank = rank + ChatColor.YELLOW + "Real Name: " + ChatColor.GRAY + p.getName() + "\n";
		}

		rank = rank + displayName + ChatColor.WHITE + "'s Ranks: \n";

		BaseComponent[] ranks;

		if(Main.PEX_ENABLED){
			PermissionUser user = PermissionsEx.getUser(p);
			List<String> groups = user.getParentIdentifiers();
			for(String s : groups)
			{
				//Getting the rank, defaults to empty string if it doesn't exist in the file.
				String rankDisplay = ConfigHandler.getDisplay(s);

				//If the player has a donor rank, it gets the number and inserts it in the rank here. No need to make a donor display for every possible amount.
				if(s.toLowerCase().startsWith("donor"))
				{
					String digit = s.replaceAll("[\\D]", "");

					rankDisplay = ConfigHandler.getDisplay("donor").replace("%amount%", digit);
				}
				rank = rank + rankDisplay + ChatColor.GRAY + " " + s + " \n";
			}
		}

		ranks = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', rank));



		String local = ChatColor.translateAlternateColorCodes('&', ConfigHandler.LOCAL_FORMAT.replace("$player$", displayName).replace("$server$", server).replace("$rank$", "").replace("$time$", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute()));
		String global = ChatColor.translateAlternateColorCodes('&', ConfigHandler.GLOBAL_FORMAT.replace("$player$", displayName).replace("$server$", server).replace("$rank$", "").replace("$time$", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute()));

		if(Main.RACES_ENABLED)
		{
			global = global.replace("$race$", Races.getRace(p).getTag());
			local = local.replace("$race$", Races.getRace(p).getTag());
		}
		if(Main.FACTIONS_ENABLED)
		{
			MPlayer uplayer = MPlayer.get(p);
			Faction faction = uplayer.getFaction();
			String factionName = faction.getName();

			global = global.replace("$faction$", factionName);
			local = local.replace("$faction$", factionName);
		}

		TextComponent localComponent = TextComponentUtil.stringToComp(local);
		TextComponent globalComponent = TextComponentUtil.stringToComp(global);

		globalComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ranks));
		localComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ranks));

		globalComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/chatinterface " + p.getName()));
		localComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/chatinterface " + p.getName()));

		TextComponent filteredGlobal = (TextComponent) globalComponent.duplicate();
		TextComponent filteredLocal = (TextComponent) localComponent.duplicate();

		String filteredMessage = message;
		for(String s : ChatFilter.getBlocked())
		{
			filteredMessage = filteredMessage.replaceAll("(?i)" + s, ChatFilter.getReplacement());
		}

		for(BaseComponent component : TextComponent.fromLegacyText(filteredMessage))
		{
			filteredGlobal.addExtra(component);
			filteredLocal.addExtra(component);
		}

		for(BaseComponent component : TextComponent.fromLegacyText(message))
		{
			localComponent.addExtra(component);
			globalComponent.addExtra(component);
		}

		result[0] = localComponent;
		result[1] = globalComponent;
		result[2] = filteredLocal;
		result[3] = filteredGlobal;

		return result;
	}
}