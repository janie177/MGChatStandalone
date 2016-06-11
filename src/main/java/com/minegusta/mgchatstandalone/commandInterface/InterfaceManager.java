package com.minegusta.mgchatstandalone.commandInterface;

import com.minegusta.mgchatstandalone.util.InterfaceInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;

public class InterfaceManager {

	private static ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14)
	{
		{
			ItemMeta meta = getItemMeta();
			meta.setDisplayName("");
			setItemMeta(meta);
		}
	};

	public static void openInterface(Player p, String target)
	{
		InterfaceInventoryHolder holder = new InterfaceInventoryHolder(target);
		Inventory inv = Bukkit.createInventory(holder, 9 * 6, ChatColor.YELLOW + "Manage " + ChatColor.RED + target);

		PermissionUser user = PermissionsEx.getUser(target);
		List<String> groups = user.getParentIdentifiers();

		//Set stained glass
		for(int i = 0; i < inv.getSize(); i++)
		{
			if(i < 9 || i > 45 || i%9 == 0|| i%9 == 8) inv.setItem(i, glass);
		}

		for(ChatInterfaceAction a : ChatInterfaceAction.values())
		{
			if(a.getIndex() < inv.getSize())
			{
				ItemStack stack = new ItemStack(a.getMaterial(), a.getAmount(), (byte) a.getData())
				{
					{
						ItemMeta meta = getItemMeta();
						meta.setDisplayName(a.getName().replace("$name$", target));
						if(a.getLore().size() > 0 && a.getLore().get(0).equalsIgnoreCase("$groups$"))
						{
							meta.setLore(groups);
						}
						else meta.setLore(a.getLore());
						setItemMeta(meta);
					}
				};

				inv.setItem(a.getIndex(), stack);
			}
		}

		p.openInventory(inv);
	}

}
