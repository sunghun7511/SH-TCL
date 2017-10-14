package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class PlayerClick implements Listener {
	Main plugin;
	public PlayerClick(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void ClickEvent(org.bukkit.event.player.PlayerInteractEvent event){
		if(plugin.isMakingBefore("event", "PlayerClick")){
			java.util.ArrayList<String> skripts = plugin.getSkript("event", "PlayerClick");
			plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
		}else{
			return;
		}
	}
}
