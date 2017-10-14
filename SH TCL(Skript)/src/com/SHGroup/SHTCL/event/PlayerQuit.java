package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class PlayerQuit implements Listener {
	Main plugin;
	public PlayerQuit(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void BreakEvent(org.bukkit.event.player.PlayerQuitEvent event){
		if(plugin.isMakingBefore("event", "PlayerQuit")){
			java.util.ArrayList<String> skripts = plugin.getSkript("event", "PlayerQuit");
			plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
		}else{
			return;
		}
	}
}
