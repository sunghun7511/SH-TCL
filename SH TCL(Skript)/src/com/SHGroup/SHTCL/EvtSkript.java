package com.SHGroup.SHTCL;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtSkript {
	Event event;
	java.util.ArrayList<String> skript;
	Player player;
	public EvtSkript(Event event, java.util.ArrayList<String> skript, Player player){
		this.event = event;
		this.skript = skript;
		this.player = player;
	}
}
