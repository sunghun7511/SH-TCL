package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class PlayerJoin implements Listener {
	Main plugin;
	public PlayerJoin(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void BreakEvent(org.bukkit.event.player.PlayerJoinEvent event){
		if(plugin.isMakingBefore("event", "PlayerJoin")){
			java.util.ArrayList<String> skripts = plugin.getSkript("event", "PlayerJoin");
			plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
		}else{
			return;
		}
	}
}
