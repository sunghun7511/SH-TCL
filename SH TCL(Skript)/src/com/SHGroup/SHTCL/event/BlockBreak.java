package com.SHGroup.SHTCL.event;

import com.SHGroup.SHTCL.Main;

import org.bukkit.event.*;

public class BlockBreak implements Listener {
	Main plugin;
	public BlockBreak(Main instance){
		this.plugin = instance;
	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void BreakEvent(org.bukkit.event.block.BlockBreakEvent event){
		if(plugin.isMakingBefore("event", "BlockBreak")){
			java.util.ArrayList<String> skripts = plugin.getSkript("event", "BlockBreak");
			plugin.skriptThread.addSkript((Event)event, skripts, event.getPlayer());
		}else{
			return;
		}
	}
}
