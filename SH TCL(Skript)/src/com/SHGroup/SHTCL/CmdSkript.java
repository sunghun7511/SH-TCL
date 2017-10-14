package com.SHGroup.SHTCL;


import org.bukkit.entity.Player;

public class CmdSkript {
	String cmd;
	java.util.ArrayList<String> skript;
	Player player;
	public CmdSkript(String cmd, java.util.ArrayList<String> skript, Player player){
		this.cmd = cmd;
		this.skript = skript;
		this.player = player;
	}
}
