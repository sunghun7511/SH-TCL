package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class BlockPlace implements Listener {
	Main plugin;
	public BlockPlace(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void PlaceEvent(org.bukkit.event.block.BlockPlaceEvent event){
		if(plugin.isMakingBefore("event", "BlockPlace")){
			java.util.ArrayList<String> skripts = plugin.getSkript("event", "BlockPlace");
			plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
		}else{
			return;
		}
	}
}
