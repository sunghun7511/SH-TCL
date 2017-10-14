package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.*;

public class PlayerCommandAccess implements Listener {
	Main plugin;
	public PlayerCommandAccess(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void CommandEvent(org.bukkit.event.player.PlayerCommandPreprocessEvent event){
		Player p = event.getPlayer();
		String label = event.getMessage().split(" ")[0].replaceFirst("/", "");
		if(plugin.commandlist.contains(label)){
			if(plugin.commandOvAOp.get(label).get(0)){
				if(!plugin.util.isOp(p)){
					return;
				}
			}
			if(plugin.commandOvAOp.get(label).get(1)){
				event.setCancelled(true);
			}else{
				event.setCancelled(false);
			}
			plugin.skriptThread.addSkript(event.getMessage(), plugin.getSkript("command", label), p);
		}
	}
}
