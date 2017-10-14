package com.SHGroup.SHTCL;

import java.util.*;

import org.bukkit.event.Event;

public class CooltimeThread extends Thread{
	ArrayList<String> skripts;
	String type;
	int tick;
	Long time;
	Main main;
	Event event;
	String command;
	HashMap<String, Boolean> canifs;
	org.bukkit.entity.Player player;
	public CooltimeThread(Main main, ArrayList<String> skript, int second, org.bukkit.entity.Player player,HashMap<String, Boolean> canifs, Event event){
		this.skripts = skript;
		this.tick = second;
		this.time = System.currentTimeMillis();
		this.main = main;
		this.type = "event";
		this.player= player;
		this.canifs = canifs;
		this.event = event;
	}
	public CooltimeThread(Main main, ArrayList<String> skript, int second, org.bukkit.entity.Player player,HashMap<String, Boolean> canifs, String command){
		this.skripts = skript;
		this.tick = second;
		this.time = System.currentTimeMillis();
		this.main = main;
		this.type = "command";
		this.player= player;
		this.canifs = canifs;
		this.command = command;
	}
	public void run(){
		while(true){
			if((time + (tick * 1000)) <= System.currentTimeMillis()){
				if(type.equals("event")){
					main.startEventSkript(event, skripts, player, canifs);
					break;
				}else{
					main.startCommandSkript(command, skripts, player, canifs);
					break;
				}
			}
		}
	}
}
