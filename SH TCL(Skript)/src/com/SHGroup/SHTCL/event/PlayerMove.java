package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class PlayerMove implements Listener {
	Main plugin;
	public PlayerMove(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void MoveEvent(org.bukkit.event.player.PlayerMoveEvent event){
		if(plugin.isMakingBefore("event", "PlayerMove")){
			if((event.getFrom().getX() != event.getTo().getX()) || (event.getFrom().getY() != event.getTo().getY()) || (event.getFrom().getZ() != event.getTo().getZ())){
				java.util.ArrayList<String> skripts = plugin.getSkript("event", "PlayerMove");
				plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
			}
		}else{
			return;
		}
	}
}
