package com.minegusta.mgchatstandalone.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InterfaceInventoryHolder implements InventoryHolder {

	private String target;

	public InterfaceInventoryHolder(String target)
	{
		super();
		this.target = target;
	}

	@Override
	public Inventory getInventory() {
		return null;
	}


	public String getTarget() {
		return target;
	}
}
