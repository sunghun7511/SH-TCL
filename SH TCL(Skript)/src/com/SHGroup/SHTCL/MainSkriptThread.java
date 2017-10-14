package com.SHGroup.SHTCL;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class MainSkriptThread extends Thread{
	Main main;
	boolean isevent;
	private ArrayList<EvtSkript> evskripts = new ArrayList<EvtSkript>();
	private ArrayList<CmdSkript> cmskripts = new ArrayList<CmdSkript>();
	public MainSkriptThread(Main instance){
		this.main = instance;
		isevent = true;
	}
	public void addSkript(Event event, ArrayList<String> skript, Player player){
		evskripts.add(new EvtSkript(event, skript, player));
	}
	public void addSkript(String command, ArrayList<String> skript, Player player){
		cmskripts.add(new CmdSkript(command, skript, player));
	}
	public void run(){
		while(true){
			if(isevent){
				if(evskripts.isEmpty()){
					isevent = false;
					continue;
				}
				EvtSkript ev = evskripts.get(0);
				main.startEventSkript(ev.event, ev.skript, ev.player, new HashMap<String, Boolean>());
				evskripts.remove(0);
			}else{
				if(cmskripts.isEmpty()){
					isevent = true;
					continue;
				}
				CmdSkript ev = cmskripts.get(0);
				if(ev.cmd == null){
					main.util.print("error1");
				}
				if(ev.skript == null){
					main.util.print("error2");
				}
				if(ev.player == null){
					main.util.print("error3");
				}
				main.startCommandSkript(ev.cmd, ev.skript, ev.player, new HashMap<String, Boolean>());
				cmskripts.remove(0);
			}
		}
	}
}
