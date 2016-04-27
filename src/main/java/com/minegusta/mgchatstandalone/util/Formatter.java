package com.minegusta.mgchatstandalone.util;

import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;

public class Formatter {

	public static String formatMessage(Player p)
	{
		String result = "";

		String displayName = p.getDisplayName();
		String server = ConfigHandler.SERVER_NAME_IN_CHAT;
		String rank = "";
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
				rank = rank + rankDisplay;
			}
		}

		result = server + "&f" + rank + "&f" + displayName + "&7: ";


		return ChatColor.translateAlternateColorCodes('&', result);
	}
}
