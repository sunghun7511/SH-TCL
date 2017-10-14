package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class PlayerChat implements Listener {
	Main plugin;
	public PlayerChat(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void BreakEvent(org.bukkit.event.player.AsyncPlayerChatEvent event){
		if(plugin.isMakingBefore("event", "PlayerChat")){
			java.util.ArrayList<String> skripts = plugin.getSkript("event", "PlayerChat");
			plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
		}else{
			return;
		}
	}
}
