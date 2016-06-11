package com.minegusta.mgchatstandalone.listeners;

import com.minegusta.mgchatstandalone.commandInterface.ChatInterfaceAction;
import com.minegusta.mgchatstandalone.commandInterface.InterfaceManager;
import com.minegusta.mgchatstandalone.util.InterfaceInventoryHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryInterfaceListener implements Listener {

	@EventHandler
	public void onInterfaceClick(InventoryClickEvent e)
	{
		if(e.getClickedInventory() == null || e.getCurrentItem() == null || !(e.getWhoClicked() instanceof Player) || e.getCurrentItem().getType() == Material.AIR)
		{
			return;
		}

		//Its the interface menu
		if(e.getClickedInventory().getHolder() instanceof InterfaceInventoryHolder)
		{
			e.setCancelled(true);

			String target = ((InterfaceInventoryHolder) e.getClickedInventory().getHolder()).getTarget();
			String command = ChatInterfaceAction.getCommandForIndex(e.getSlot(), target);

			//Slot has a command to it, run the command.
			if(!command.isEmpty())
			{
				((Player) e.getWhoClicked()).performCommand(command);
				InterfaceManager.openInterface((Player) e.getWhoClicked(), target);
			}


		}
	}

}
