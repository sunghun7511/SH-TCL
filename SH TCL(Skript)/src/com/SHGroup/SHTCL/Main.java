package com.SHGroup.SHTCL;

import java.io.*;
import java.util.*;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;


public class Main extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener{
	public SHUti util;
	public HashMap<String, String> status = new HashMap<String, String>();
	public HashMap<String, Integer> var = new HashMap<String, Integer>();
	public ArrayList<String> varlist = new ArrayList<String>();
	public HashMap<String, String> string = new HashMap<String, String>();
	public ArrayList<String> stringlist = new ArrayList<String>();
	public ArrayList<String> commandlist = new ArrayList<String>();
	public HashMap<String, ArrayList<Boolean>> commandOvAOp = new HashMap<String, ArrayList<Boolean>>();
	public ArrayList<String> eventlist = new ArrayList<String>();
	public MainSkriptThread skriptThread;
	final ArrayList<String> notvarlist = new ArrayList<String>();
	public boolean printError = true;
	
	
	@Override
	public void onEnable() {
		eventlist.add("PlayerQuit");
		eventlist.add("BlockPlace");
		eventlist.add("BlockBreak");
		eventlist.add("PlayerJoin");
		eventlist.add("PlayerClick");
		eventlist.add("PlayerMove");
		eventlist.add("PlayerChat");
		notvarlist.add("PlayerName");
		notvarlist.add("TargetBlockID");
		notvarlist.add("TargetBlockX");
		notvarlist.add("TargetBlockY");
		notvarlist.add("TargetBlockZ");
		notvarlist.add("WorldSpawnX");
		notvarlist.add("WorldSpawnY");
		notvarlist.add("WorldSpawnZ");
		notvarlist.add("PlayerWorld");
		notvarlist.add("GetDownBlockID");
		notvarlist.add("GetDownBlockX");
		notvarlist.add("GetDownBlockY");
		notvarlist.add("GetDownBlockZ");
		notvarlist.add("GetLocBlockID");
		notvarlist.add("GetLocBlockX");
		notvarlist.add("GetLocBlockY");
		notvarlist.add("GetLocBlockZ");
		notvarlist.add("GetClickBlockID");
		notvarlist.add("GetClickBlockX");
		notvarlist.add("GetClickBlockY");
		notvarlist.add("GetClickBlockZ");
		notvarlist.add("GetJoinLocationX");
		notvarlist.add("GetJoinLocationY");
		notvarlist.add("GetJoinLocationZ");
		notvarlist.add("GetJoinLocationID");
		notvarlist.add("GetClickBlockItemID");
		notvarlist.add("GetJoinLocationX");
		notvarlist.add("GetJoinLocationY");
		notvarlist.add("GetJoinLocationZ");
		notvarlist.add("GetJoinLocationID");
		notvarlist.add("GetJoinMessage");
		notvarlist.add("GetQuitLocationX");
		notvarlist.add("GetQuitLocationY");
		notvarlist.add("GetQuitLocationZ");
		notvarlist.add("GetQuitLocationID");
		notvarlist.add("GetQuitMessage");
		notvarlist.add("GetPlaceBlockID");
		notvarlist.add("GetPlaceBlockX");
		notvarlist.add("GetPlaceBlockY");
		notvarlist.add("GetPlaceBlockZ");
		notvarlist.add("GetBreakBlockID");
		notvarlist.add("GetBreakBlockX");
		notvarlist.add("GetBreakBlockY");
		notvarlist.add("GetBreakBlockZ");
		notvarlist.add("&a");
		notvarlist.add("&b");
		notvarlist.add("&c");
		notvarlist.add("&d");
		notvarlist.add("&e");
		notvarlist.add("&f");
		notvarlist.add("&0");
		notvarlist.add("&1");
		notvarlist.add("&2");
		notvarlist.add("&3");
		notvarlist.add("&4");
		notvarlist.add("&5");
		notvarlist.add("&6");
		notvarlist.add("&7");
		notvarlist.add("&8");
		notvarlist.add("&9");
		notvarlist.add("&l");
		notvarlist.add("&m");
		notvarlist.add("&n");
		notvarlist.add("&o");
		util = new SHUti(this.getDescription());
		load();
		
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.PlayerMove(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.PlayerCommandAccess(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.PlayerClick(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.BlockBreak(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.BlockPlace(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.PlayerJoin(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.PlayerQuit(this) ,this);
		this.getServer().getPluginManager().registerEvents(new com.SHGroup.SHTCL.event.PlayerChat(this) ,this);
		
		skriptThread = new MainSkriptThread(this);
		skriptThread.start();
		
		getCommand("TCL").setExecutor(new com.SHGroup.SHTCL.command.shsk(this));
		getCommand("TCLEvent").setExecutor(new com.SHGroup.SHTCL.command.shskevent(this));
		getCommand("TCLCmd").setExecutor(new com.SHGroup.SHTCL.command.shskcommand(this));
		getCommand("TCLRemove").setExecutor(new com.SHGroup.SHTCL.command.shskremove(this));
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		save();
		skriptThread.stop();
	}
	public void startCommandSkript(String cmd, ArrayList<String> skript, Player player, HashMap<String, Boolean> canifs){
		HashMap<String, Boolean> canif = canifs;
		for(int abcdefg=0;abcdefg<skript.size();abcdefg++){
			String n = skript.get(abcdefg);
			if(skript.isEmpty()){
				return;
			}
			String ad = cmd;
			String[] ars = ad.split(" ");
			String[] ar = Arrays.copyOfRange(ars, 1 , ars.length);
			n = n.replace("%ArgsLength%", "" + (ar.length));
			n = n.replace("%Message%", ad.replaceFirst(ars[0] + " ",""));
			for(int i = 1; i <= ar.length ; i++){
				n = n.replace("%Args" + i + "%", ar[(i - 1)]);
			}
			n = n.replace("%PlayerName%", player.getName());
			n = n.replace("%PlayerWorld%", player.getWorld().getName());
			n = n.replace("%PlayerLevel%", player.getLevel() + "");
			try{
				n = n.replace("%TargetBlockID%", "" + player.getTargetBlock(null, 0).getTypeId());
			}catch(Exception ex){
				n = n.replace("%TargetBlockID%", "0");
			}
			try{
				n = n.replace("%TargetBlockX%", "" + player.getTargetBlock(null, 0).getX());
			}catch(Exception ex){
				n = n.replace("%TargetBlockX%", "0");
			}
			try{
				n = n.replace("%TargetBlockY%", "" + player.getTargetBlock(null, 0).getY());
			}catch(Exception ex){
				n = n.replace("%TargetBlockY%", "0");
			}
			try{
				n = n.replace("%TargetBlockZ%", "" + player.getTargetBlock(null, 0).getZ());
			}catch(Exception ex){
				n = n.replace("%TargetBlockZ%", "0");
			}
			n = n.replace("%WorldSpawnX%", player.getWorld().getSpawnLocation().getX() + "");
			n = n.replace("%WorldSpawnY%", player.getWorld().getSpawnLocation().getY() + "");
			n = n.replace("%WorldSpawnZ%", player.getWorld().getSpawnLocation().getZ() + "");
			n = n.replace("%GetDownBlockID%", player.getLocation().add(0, -1, 0).getBlock().getTypeId() + "");
			n = n.replace("%GetDownBlockX%", player.getLocation().add(0, -1, 0).getBlock().getX() + "");
			n = n.replace("%GetDownBlockY%", player.getLocation().add(0, -1, 0).getBlock().getY() + "");
			n = n.replace("%GetDownBlockZ%", player.getLocation().add(0, -1, 0).getBlock().getZ() + "");
			n = n.replace("%GetLocBlockID%", player.getLocation().getBlock().getTypeId() + "");
			n = n.replace("%GetLocBlockX%", (int)player.getLocation().getBlock().getX() + "");
			n = n.replace("%GetLocBlockY%", (int)player.getLocation().getBlock().getY() + "");
			n = n.replace("%GetLocBlockZ%", (int)player.getLocation().getBlock().getZ() + "");
			n = n.replace("%GetItemInHandID%", player.getItemInHand().getTypeId() + "");
			n = n.replace("&a", "§a");
			n = n.replace("&b", "§b");
			n = n.replace("&c", "§c");
			n = n.replace("&d", "§d");
			n = n.replace("&e", "§e");
			n = n.replace("&f", "§f");
			n = n.replace("&0", "§0");
			n = n.replace("&1", "§1");
			n = n.replace("&2", "§2");
			n = n.replace("&3", "§3");
			n = n.replace("&4", "§4");
			n = n.replace("&5", "§5");
			n = n.replace("&6", "§6");
			n = n.replace("&7", "§7");
			n = n.replace("&8", "§8");
			n = n.replace("&9", "§9");
			n = n.replace("&l", "§l");
			n = n.replace("&m", "§m");
			n = n.replace("&n", "§n");
			n = n.replace("&o", "§o");
			for(String vars : this.varlist){
				if(n.contains(vars)){
					n = n.replace("<" + vars + ">", Integer.toString(var.get(vars)));
				}
			}
			for(String strings : this.stringlist){
				if(n.contains(strings)){
					n = n.replace("<" + strings + ">", string.get(strings));
				}
			}
			if(!canif.isEmpty()){
				if(!n.equalsIgnoreCase("#ENDIF")){
					
					if(!canif.get("" + canif.size())){
						skript.remove(skript.size() - 1);
						return;
					}
				}
			}
			if(n.equalsIgnoreCase("#ENDIF")){
				canif.remove(canif.size());
				if(!canif.isEmpty()){
					if(!canif.get("" + canif.size())){
						skript.remove(skript.size() - 1);
						return;
					}
				}
			}else if(n.startsWith("#BROADCAST ")){
				util.bc(n.replaceFirst("#BROADCAST ", ""));
			}else if(n.startsWith("#SENDMSG")){
				player.sendMessage(n.replaceFirst("#SENDMSG ", ""));
			}else if(n.startsWith("#SLEEP ")){
				try{
					skript.remove(0);
					int time = Integer.parseInt(n.replaceFirst("#SLEEP ", ""));
					CooltimeThread thread = new CooltimeThread(this, skript, time, player, canif, cmd);
					thread.start();
					return;
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SLEEP는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 01SLEEP" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#TELEPORT ")){
				String[] args = (n.replaceFirst("#TELEPORT ", "")).split(" ");
				try{
					if(args[0].equalsIgnoreCase("player")){
						Player p = Bukkit.getPlayer(args[0]);
						player.teleport((org.bukkit.entity.Entity)p);
					}
					if(args[0].equalsIgnoreCase("location")){
						Location l = new Location(Bukkit.getWorld(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
						player.teleport(l);
					}
					if(args[0].equalsIgnoreCase("LookLocation")){
						Location loc = player.getTargetBlock(null, 0).getLocation().add(0,1,0);
						player.teleport(loc);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "TELEPORT는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 03TELEPORT" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VECTOR ")){
				String[] args = (n.replaceFirst("#VECTOR ", "")).split(" ");
				try{
					org.bukkit.util.Vector vc = new org.bukkit.util.Vector(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
					player.setVelocity(vc);
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VECTOR는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 05VECTOR" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#CMD ")){
				String message = n.replaceFirst("#CMD ", "");
	            try {
	              this.getServer().dispatchCommand(player, message);
	            }catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "CMD는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 07CMD" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#CMDOP ")){
				String message = n.replaceFirst("#CMDOP ", "");
                boolean isop = player.isOp();
                player.setOp(true);
	            try {
	            	this.getServer().dispatchCommand(player, message);
	            }catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "CMDOP는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 09CMDOP" + ex.getMessage());
					}
				}
                player.setOp(isop);
			}else if(n.startsWith("#CMDCON ")){
				String message = n.replaceFirst("#CMDCON ", "");
				this.getServer().dispatchCommand(Bukkit.getConsoleSender(), message);
			}else if(n.startsWith("#EXPLOSION ")){
				String[] args = (n.replaceFirst("#EXPLOSION ", "")).split(" ");
				try{
					Bukkit.getWorld(args[0]).createExplosion(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]));
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "EXPLOSION는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 11EXPLOSION" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#SETBLOCK ")){
				String[] args = (n.replaceFirst("#SETBLOCK ", "")).split(" ");
				try{
					Location l = new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
					String[] arg = args[4].split(":");
					l.getBlock().setTypeId(Integer.parseInt(arg[0]));
					try{
						l.getBlock().setData(Byte.parseByte(arg[1]));
					}catch(Exception ex){}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SETBLOCK는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 13SETBLOCK" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARCREATE ")){
				String[] args = (n.replaceFirst("#VARCREATE ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(varcheck(varname)){
						var.put(varname, varValue);
						varlist.add(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARCREATE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 15VARCREATE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARADD ")){
				String[] args = (n.replaceFirst("#VARADD ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, (var.get(varname) + varValue));
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARADD는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 17VARADD" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARMUL ")){
				String[] args = (n.replaceFirst("#VARMUL ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, (var.get(varname) * varValue));
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARMUL는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 19VARMUL" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARDIV ")){
				String[] args = (n.replaceFirst("#VARMUL ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, (var.get(varname) / varValue));
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARDIV는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 21VARDIV" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARSET ")){
				String[] args = (n.replaceFirst("#VARADD ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, varValue);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARSET는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 23VARSET" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARREMOVE ")){
				try{
					String varname = (n.replaceFirst("#VARREMOVE ", ""));
					if(var.get(varname) != null){
						varlist.remove(varname);
						var.remove(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARREMOVE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 25VARREMOVE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#STRINGCREATE ")){
				String[] args = (n.replaceFirst("#STRINGCREATE ", "")).split(" ");
				try{
					String varname = args[0];
					String varValue = args[1];
					if(varcheck(varname)){
						string.put(varname, varValue);
						stringlist.add(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "STRINGCREATE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 27STRINGCREATE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#STRINGSET ")){
				String[] args = (n.replaceFirst("#STRINGSET ", "")).split(" ");
				try{
					String varname = args[0];
					String varValue = args[1];
					if(string.get(varname) != null){
						string.put(varname, varValue);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "STRINGSET는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 29STRINGSET" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#STRINGREMOVE ")){
				try{
					String varname = (n.replaceFirst("#STRINGREMOVE ", ""));
					if(string.get(varname) != null){
						stringlist.remove(varname);
						string.remove(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "STRINGREMOVE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 31STRINGREMOVE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#SETHUNGER ")){
				try{
					String hung = (n.replaceFirst("#SETHUNGER ", ""));
					player.setFoodLevel(Integer.parseInt(hung));
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SETHUNGER는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 33SETHUNGER" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#SETHEALTH ")){
				try{
					String hung = (n.replaceFirst("#SETHEALTH ", ""));
					player.setHealth(Integer.parseInt(hung));
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SETHEALTH는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 35SETHEALTH" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#BAN")){
				String message = n.replaceFirst("#BAN", "");
				if(message.startsWith(" ")){
					player.kickPlayer(message.replaceFirst(" ", ""));
					player.setBanned(true);
				}else{
					player.setBanned(true);
				}
			}else if(n.startsWith("#KICK")){
				String message = n.replaceFirst("#KICK", "");
				if(message.startsWith(" ")){
					player.kickPlayer(message.replaceFirst(" ", ""));
				}else{
					player.kickPlayer("");
				}
			}else if(n.startsWith("#IF ")){
				try{
					String jogun = n.replaceFirst("#IF ", "");
					if(jogun.split("==").length == 2){
						String[] arg = jogun.split("==");
						if(arg[0].equals(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split("!=").length == 2){
						String[] arg = jogun.split("!=");
						if(!arg[0].equals(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split(">").length == 2){
						String[] arg = jogun.split(">");
						if(Integer.parseInt(arg[0]) > Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split("<").length == 2){
						String[] arg = jogun.split("<");
						if(Integer.parseInt(arg[0]) < Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split(">=").length == 2){
						String[] arg = jogun.split(">=");
						if(Integer.parseInt(arg[0]) >= Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split("<=").length == 2){
						String[] arg = jogun.split("<=");
						if(Integer.parseInt(arg[0]) <= Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "IF는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 37IF" + ex.getMessage());
					}
				}
			}
		}
	}

	public void startEventSkript(Event event, ArrayList<String> skript, Player player, HashMap<String, Boolean> canifs){
		HashMap<String, Boolean> canif = canifs;
		for(int abcdefg=0;abcdefg<skript.size();abcdefg++){
			String n = skript.get(abcdefg);
			if(skript.isEmpty()){
				return;
			}
			if(event instanceof PlayerInteractEvent){
				n = n.replace("%GetClickBlockID%", ((PlayerInteractEvent)event).getClickedBlock().getTypeId() + "");
				n = n.replace("%GetClickBlockX%", ((PlayerInteractEvent)event).getClickedBlock().getX() + "");
				n = n.replace("%GetClickBlockY%", ((PlayerInteractEvent)event).getClickedBlock().getY() + "");
				n = n.replace("%GetClickBlockZ%", ((PlayerInteractEvent)event).getClickedBlock().getZ() + "");
				n = n.replace("%GetClickBlockItemID%", ((PlayerInteractEvent)event).getPlayer().getItemInHand().getTypeId() + "");
				if(((PlayerInteractEvent)event).getAction() == Action.LEFT_CLICK_AIR){
					n = n.replace("%GetAction%", "0");
				}else if(((PlayerInteractEvent)event).getAction() == Action.LEFT_CLICK_BLOCK){
					n = n.replace("%GetAction%", "1");
				}else if(((PlayerInteractEvent)event).getAction() == Action.RIGHT_CLICK_AIR){
					n = n.replace("%GetAction%", "2");
				}else if(((PlayerInteractEvent)event).getAction() == Action.RIGHT_CLICK_BLOCK){
					n = n.replace("%GetAction%", "3");
				}
			}
			if(event instanceof PlayerJoinEvent){
				n = n.replace("%GetJoinLocationX%", ((PlayerJoinEvent)event).getPlayer().getLocation().getX() + "");
				n = n.replace("%GetJoinLocationY%", ((PlayerJoinEvent)event).getPlayer().getLocation().getY() + "");
				n = n.replace("%GetJoinLocationZ%", ((PlayerJoinEvent)event).getPlayer().getLocation().getZ() + "");
				n = n.replace("%GetJoinLocationID%", ((PlayerJoinEvent)event).getPlayer().getLocation().getBlock().getTypeId() + "");
				n = n.replace("%GetJoinMessage%", ((PlayerJoinEvent)event).getJoinMessage());
			}
			if(event instanceof PlayerQuitEvent){
				n = n.replace("%GetQuitLocationX%", ((PlayerQuitEvent)event).getPlayer().getLocation().getX() + "");
				n = n.replace("%GetQuitLocationY%", ((PlayerQuitEvent)event).getPlayer().getLocation().getY() + "");
				n = n.replace("%GetQuitLocationZ%", ((PlayerQuitEvent)event).getPlayer().getLocation().getZ() + "");
				n = n.replace("%GetQuitLocationID%", ((PlayerQuitEvent)event).getPlayer().getLocation().getBlock().getTypeId() + "");
				n = n.replace("%GetQuitMessage%", ((PlayerQuitEvent)event).getQuitMessage());
			}
			if(event instanceof BlockPlaceEvent){
				n = n.replace("%GetPlaceBlockID%", ((BlockPlaceEvent)event).getBlock().getTypeId() + "");
				n = n.replace("%GetPlaceBlockX%", ((BlockPlaceEvent)event).getBlock().getLocation().getX() + "");
				n = n.replace("%GetPlaceBlockY%", ((BlockPlaceEvent)event).getBlock().getLocation().getY() + "");
				n = n.replace("%GetPlaceBlockZ%", ((BlockPlaceEvent)event).getBlock().getLocation().getZ() + "");
			}
			if(event instanceof BlockBreakEvent){
				n = n.replace("%GetBreakBlockID%", ((BlockBreakEvent)event).getBlock().getTypeId() + "");
				n = n.replace("%GetBreakBlockX%", ((BlockBreakEvent)event).getBlock().getLocation().getX() + "");
				n = n.replace("%GetBreakBlockY%", ((BlockBreakEvent)event).getBlock().getLocation().getY() + "");
				n = n.replace("%GetBreakBlockZ%", ((BlockBreakEvent)event).getBlock().getLocation().getZ() + "");
			}
			n = n.replace("%PlayerName%", player.getName());
			n = n.replace("%PlayerWorld%", player.getWorld().getName());
			n = n.replace("%PlayerLevel%", player.getLevel() + "");
			n = n.replace("%TargetBlockID%", "" + player.getTargetBlock(null, 0).getTypeId());
			n = n.replace("%TargetBlockX%", "" + player.getTargetBlock(null, 0).getX());
			n = n.replace("%TargetBlockY%", "" + player.getTargetBlock(null, 0).getY());
			n = n.replace("%TargetBlockZ%", "" + player.getTargetBlock(null, 0).getZ());
			n = n.replace("%WorldSpawnX%", player.getWorld().getSpawnLocation().getX() + "");
			n = n.replace("%WorldSpawnY%", player.getWorld().getSpawnLocation().getY() + "");
			n = n.replace("%WorldSpawnZ%", player.getWorld().getSpawnLocation().getZ() + "");
			n = n.replace("%GetDownBlockID%", player.getLocation().add(0, -1, 0).getBlock().getTypeId() + "");
			n = n.replace("%GetDownBlockX%", player.getLocation().add(0, -1, 0).getBlock().getX() + "");
			n = n.replace("%GetDownBlockY%", player.getLocation().add(0, -1, 0).getBlock().getY() + "");
			n = n.replace("%GetDownBlockZ%", player.getLocation().add(0, -1, 0).getBlock().getZ() + "");
			n = n.replace("%GetLocBlockID%", player.getLocation().getBlock().getTypeId() + "");
			n = n.replace("%GetLocBlockX%", player.getLocation().getBlock().getX() + "");
			n = n.replace("%GetLocBlockY%", player.getLocation().getBlock().getY() + "");
			n = n.replace("%GetLocBlockZ%", player.getLocation().getBlock().getZ() + "");
			n = n.replace("%GetItemInHandID%", player.getItemInHand().getTypeId() + "");
			n = n.replace("&a", "§a");
			n = n.replace("&b", "§b");
			n = n.replace("&c", "§c");
			n = n.replace("&d", "§d");
			n = n.replace("&e", "§e");
			n = n.replace("&f", "§f");
			n = n.replace("&0", "§0");
			n = n.replace("&1", "§1");
			n = n.replace("&2", "§2");
			n = n.replace("&3", "§3");
			n = n.replace("&4", "§4");
			n = n.replace("&5", "§5");
			n = n.replace("&6", "§6");
			n = n.replace("&7", "§7");
			n = n.replace("&8", "§8");
			n = n.replace("&9", "§9");
			n = n.replace("&l", "§l");
			n = n.replace("&m", "§m");
			n = n.replace("&n", "§n");
			n = n.replace("&o", "§o");
			for(String vars : this.varlist){
				if(n.contains(vars)){
					n = n.replace("<" + vars + ">", Integer.toString(var.get(vars)));
				}
			}
			for(String strings : this.stringlist){
				if(n.contains(strings)){
					n = n.replace("<" + strings + ">", string.get(strings));
				}
			}
			if(!canif.isEmpty()){
				if(!n.equalsIgnoreCase("#ENDIF")){
					
					if(!canif.get("" + canif.size())){
						skript.remove(skript.size() - 1);
						return;
					}
				}
			}
			if(n.equalsIgnoreCase("#ENDIF")){
				canif.remove(canif.size());
				if(!canif.isEmpty()){
					if(!canif.get("" + canif.size())){
						skript.remove(skript.size() - 1);
						return;
					}
				}
			}else if(n.startsWith("#BROADCAST ")){
				util.bc(n.replaceFirst("#BROADCAST ", ""));
			}else if(n.startsWith("#SENDMSG")){
				player.sendMessage(n.replaceFirst("#SENDMSG ", ""));
			}else if(n.startsWith("#SLEEP ")){
				try{
					skript.remove(0);
					int time = Integer.parseInt(n.replaceFirst("#SLEEP ", ""));
					CooltimeThread thread = new CooltimeThread(this, skript, time, player, canif, event);
					thread.start();
					return;
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SLEEP는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 02SLEEP" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#TELEPORT ")){
				String[] args = (n.replaceFirst("#TELEPORT ", "")).split(" ");
				try{
					if(args[0].equalsIgnoreCase("player")){
						Player p = Bukkit.getPlayer(args[1]);
						player.teleport((org.bukkit.entity.Entity)p);
					}
					if(args[0].equalsIgnoreCase("location")){
						Location l = new Location(Bukkit.getWorld(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
						player.teleport(l);
					}
					if(args[0].equalsIgnoreCase("LookLocation")){
						Location loc = player.getTargetBlock(null, 0).getLocation().add(0,1,0);
						player.teleport(loc);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "TELEPORT는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 03TELEPORT" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VECTOR ")){
				String[] args = (n.replaceFirst("#VECTOR ", "")).split(" ");
				try{
					org.bukkit.util.Vector vc = new org.bukkit.util.Vector(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
					player.setVelocity(vc);
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VECTOR는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 06VECTOR" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#CMD ")){
				String message = n.replaceFirst("#CMD ", "");
	            try {
	              this.getServer().dispatchCommand(player, message);
	            }catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "CMD는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 08CMD" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#CMDOP ")){
				String message = n.replaceFirst("#CMDOP ", "");
                boolean isop = player.isOp();
                player.setOp(true);
	            try {
	            	this.getServer().dispatchCommand(player, message);
	            }catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "CMDOP는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 10CMDOP" + ex.getMessage());
					}
				}
                player.setOp(isop);
			}else if(n.startsWith("#CMDCON ")){
				String message = n.replaceFirst("#CMDCON ", "");
				this.getServer().dispatchCommand(Bukkit.getConsoleSender(), message);
			}else if(n.startsWith("#EXPLOSION ")){
				String[] args = (n.replaceFirst("#EXPLOSION ", "")).split(" ");
				try{
					Bukkit.getWorld(args[0]).createExplosion(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]));
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "EXPLOSION는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 12EXPLOSION" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#SETBLOCK ")){
				String[] args = (n.replaceFirst("#SETBLOCK ", "")).split(" ");
				try{
					Location l = new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
					String[] arg = args[4].split(":");
					l.getBlock().setTypeId(Integer.parseInt(arg[0]));
					try{
						l.getBlock().setData(Byte.parseByte(arg[1]));
					}catch(Exception ex){}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SETBLOCK는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 14SETBLOCK" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARCREATE ")){
				String[] args = (n.replaceFirst("#VARCREATE ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(varcheck(varname)){
						var.put(varname, varValue);
						varlist.add(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARCREATE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 16VARCREATE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARADD ")){
				String[] args = (n.replaceFirst("#VARADD ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, (var.get(varname) + varValue));
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARADD는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 18VARADD" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARMUL ")){
				String[] args = (n.replaceFirst("#VARMUL ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, (var.get(varname) * varValue));
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARMUL는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 20VARMUL" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARDIV ")){
				String[] args = (n.replaceFirst("#VARDIV ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, (var.get(varname) / varValue));
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARMUL는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 22VARDIV" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARSET ")){
				String[] args = (n.replaceFirst("#VARSET ", "")).split(" ");
				try{
					String varname = args[0];
					int varValue = Integer.parseInt(args[1]);
					if(var.get(varname) != null){
						var.put(varname, varValue);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARSET는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 24VARSET" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#VARREMOVE ")){
				try{
					String varname = (n.replaceFirst("#VARREMOVE ", ""));
					if(var.get(varname) != null){
						varlist.remove(varname);
						var.remove(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "VARREMOVE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 26VARREMOVE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#STRINGCREATE ")){
				String[] args = (n.replaceFirst("#STRINGCREATE ", "")).split(" ");
				try{
					String varname = args[0];
					String varValue = args[1];
					if(varcheck(varname)){
						string.put(varname, varValue);
						stringlist.add(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "STRINGCREATE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 28STRINGCREATE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#STRINGSET ")){
				String[] args = (n.replaceFirst("#STRINGSET ", "")).split(" ");
				try{
					String varname = args[0];
					String varValue = args[1];
					if(string.get(varname) != null){
						string.put(varname, varValue);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "STRINGSET는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 30STRINGSET" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#STRINGREMOVE ")){
				try{
					String varname = (n.replaceFirst("#STRINGREMOVE ", ""));
					if(string.get(varname) != null){
						stringlist.remove(varname);
						string.remove(varname);
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "STRINGREMOVE는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 32STRINGREMOVE" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#SETHUNGER ")){
				try{
					String hung = (n.replaceFirst("#SETHUNGER ", ""));
					player.setFoodLevel(Integer.parseInt(hung));
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SETHUNGER는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 34SETHUNGER" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#SETHEALTH ")){
				try{
					String hung = (n.replaceFirst("#SETHEALTH ", ""));
					player.setHealth(Integer.parseInt(hung));
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "SETHEALTH는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 36SETHEALTH" + ex.getMessage());
					}
				}
			}else if(n.startsWith("#BAN")){
				String message = n.replaceFirst("#BAN", "");
				if(message.startsWith(" ")){
					player.kickPlayer(message.replaceFirst(" ", ""));
					player.setBanned(true);
				}else{
					player.setBanned(true);
				}
			}else if(n.startsWith("#KICK")){
				String message = n.replaceFirst("#KICK", "");
				if(message.startsWith(" ")){
					player.kickPlayer(message.replaceFirst(" ", ""));
				}else{
					player.kickPlayer("");
				}
			}else if(n.startsWith("#IF ")){
				try{
					String jogun = n.replaceFirst("#IF ", "");
					if(jogun.split("==").length == 2){
						String[] arg = jogun.split("==");
						if(arg[0].equals(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split("!=").length == 2){
						String[] arg = jogun.split("!=");
						if(!arg[0].equals(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split(">").length == 2){
						String[] arg = jogun.split(">");
						if(Integer.parseInt(arg[0]) > Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split("<").length == 2){
						String[] arg = jogun.split("<");
						if(Integer.parseInt(arg[0]) < Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split(">=").length == 2){
						String[] arg = jogun.split(">=");
						if(Integer.parseInt(arg[0]) >= Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}else if(jogun.split("<=").length == 2){
						String[] arg = jogun.split("<=");
						if(Integer.parseInt(arg[0]) <= Integer.parseInt(arg[1])){
							canif.put("" + (canif.size() + 1), true);
						}else{
							canif.put("" + (canif.size() + 1), false);
						}
					}
				}catch(Exception ex){
					if(printError){
						util.bc(util.getPerfix() + "스크립트 실행중 오류가 발생하였습니다!");
						util.bc(util.getPerfix() + "IF는 실행되지 않습니다.");
						util.bc(util.getPerfix() + "오류 코드 : 37IF" + ex.getMessage());
					}
				}
			}
			//이벤트 라인
			else if(n.startsWith("#CANCEL")){
				if(event instanceof BlockBreakEvent){
					((BlockBreakEvent) event).setCancelled(true);
				}else if(event instanceof BlockPlaceEvent){
					((BlockPlaceEvent) event).setCancelled(true);
				}else if(event instanceof AsyncPlayerChatEvent){
					((AsyncPlayerChatEvent) event).setCancelled(true);
				}else if(event instanceof PlayerMoveEvent){
					((PlayerMoveEvent) event).setCancelled(true);
				}else if(event instanceof PlayerInteractEvent){
					((PlayerInteractEvent) event).setCancelled(true);
				}
			}else if(n.startsWith("#UNCANCEL")){
				if(event instanceof BlockBreakEvent){
					((BlockBreakEvent) event).setCancelled(false);
				}else if(event instanceof BlockPlaceEvent){
					((BlockPlaceEvent) event).setCancelled(false);
				}else if(event instanceof AsyncPlayerChatEvent){
					((AsyncPlayerChatEvent) event).setCancelled(false);
				}else if(event instanceof PlayerMoveEvent){
					((PlayerMoveEvent) event).setCancelled(false);
				}else if(event instanceof PlayerInteractEvent){
					((PlayerInteractEvent) event).setCancelled(false);
				}
			}else if(n.startsWith("#SETFORMAT ")){
				if(event instanceof AsyncPlayerChatEvent){
					((AsyncPlayerChatEvent) event).setCancelled(true);;
					Bukkit.broadcastMessage(n.replaceFirst("#SETFORMAT ", ""));
				}
			}else if(n.startsWith("#SETJOINMSG ")){
				if(event instanceof PlayerJoinEvent){
					((PlayerJoinEvent) event).setJoinMessage(n.replaceFirst("#SETJOINMSG ", ""));
				}
			}else if(n.startsWith("#SETQUITMSG ")){
				if(event instanceof PlayerQuitEvent){
					((PlayerQuitEvent) event).setQuitMessage(n.replaceFirst("#SETJOINMSG ", ""));
				}
			}
			skript.remove(skript.size() - 1);
		}
	}

	public boolean varcheck(String varsname){
		if(varlist.contains(varsname)){
			return false;
		}
		if(stringlist.contains(varsname)){
			return false;
		}
		if(notvarlist.contains(varsname)){
			return false;
		}
		return true;
	}
	public ArrayList<String> getSkript(String what, String name){
		ArrayList<String> skript = new ArrayList<String>();
		try{
			File f = new File("plugins\\SH TCL\\" + what + "\\" + name + ".shsk");
			if(!f.exists()){
				return null;
			}
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			while((line = br.readLine()) != null){
				skript.add(line);
			}
			br.close();
		}catch(Exception ex){
			skript = null;
			util.print(ex.getMessage());
			util.print("1");
		}
		return skript;
	}
	public void addSkript(String what, String name, String line){
		ArrayList<String> skript = this.getSkript(what, name);
		skript.add(line);
		this.setSkript(what, name, skript);
	}
	public void RemoveAllSkript(){
		this.setSkript("event", "PlayerMove", new ArrayList<String>());
		this.setSkript("event", "PlayerChat", new ArrayList<String>());
		this.setSkript("event", "PlayerClick", new ArrayList<String>());
		this.setSkript("event", "PlayerQuit", new ArrayList<String>());
		this.setSkript("event", "PlayerJoin", new ArrayList<String>());
		this.setSkript("event", "BlockPlace", new ArrayList<String>());
		this.setSkript("event", "BlockBreak", new ArrayList<String>());
	}
	public void setSkript(String what, String name, ArrayList<String> skriptline){
		try{
			File f = new File("plugins\\SH TCL\\" + what + "\\" + name + ".shsk");
			if(!f.exists()){
				return;
			}
			BufferedWriter br = new BufferedWriter(new FileWriter(f));
			String to = "";
			boolean b = true;
			for(String n : skriptline){
				if(b){
					to = n;
					b = false;
				}else{
					to += "\n" + n;
				}
			}
			br.append(to);
			br.flush();
			br.close();
		}catch(Exception ex){
			util.print(ex.getMessage());
			util.print("2");
			return;
		}
		return;
	}
	public boolean isMakingBefore(String what, String name){
		try{
			File f = new File("plugins\\SH TCL\\" + what + "\\" + name + ".shsk");
			if(!f.exists()){
				return false;
			}else{
				return true;
			}
		}catch(Exception ex){
			util.print(ex.getMessage());
			util.print("3");
			return false;
		}
	}
	public void MakingFile(String what, String name){
		try{
			File f = new File("plugins\\SH TCL\\" + what + "\\" + name + ".shsk");
			File fold = new File("plugins\\SH TCL\\" + what + "\\");
			fold.mkdirs();
			f.createNewFile();
		}catch(Exception ex){
			util.print(ex.getMessage());
			util.print("4");
			return;
		}
	}
	public void load(){
		try{
			File f = new File("plugins\\SH TCL\\varlist.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedReader bw = new BufferedReader(new FileReader(f));
			String n;
			while((n=bw.readLine()) != null){
				String[] ar = n.split(" : ");
				varlist.add(ar[0]);
				var.put(ar[0], Integer.parseInt(ar[1]));
			}
			bw.close();
		}catch(Exception ex){
			this.util.bc("VAR 변수 파일을 로드하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
			
		}
		try{
			File f = new File("plugins\\SH TCL\\stringlist.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedReader bw = new BufferedReader(new FileReader(f));
			String n;
			while((n=bw.readLine()) != null){
				String[] ar = n.split(" : ");
				stringlist.add(ar[0]);
				string.put(ar[0], ar[1]);
			}
			bw.close();
		}catch(Exception ex){
			this.util.bc("STRING 변수 파일을 로드하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
		}
		try{
			File f = new File("plugins\\SH TCL\\commandlist.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedReader bw = new BufferedReader(new FileReader(f));
			String n;
			while((n=bw.readLine()) != null){
				this.commandlist.add(n);
			}
			bw.close();
		}catch(Exception ex){
			this.util.bc("커맨드리스트 파일을 로드하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
		}
		try{
			File f = new File("plugins\\SH TCL\\overrideandop.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedReader bw = new BufferedReader(new FileReader(f));
			String n;
			while((n=bw.readLine()) != null){
				ArrayList<Boolean> list = new ArrayList<Boolean>();
				list.add(Boolean.parseBoolean(n.split(":")[1].split(",")[0]));
				list.add(Boolean.parseBoolean(n.split(":")[1].split(",")[1]));
				this.commandOvAOp.put(n.split(":")[0], list);
			}
			bw.close();
		}catch(Exception ex){
			this.util.bc("커맨드리스트 파일을 로드하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
		}
	}
	public void save(){
		try{
			File f = new File("plugins\\SH TCL\\varlist.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String n = "";
			boolean b = true;
			for(String l : this.varlist){
				if(b){
					n = l + " : " + var.get(l);
					b = false;
				}else{
					n += "\n" + l + " : " + var.get(l);
				}
			}
			bw.append(n);
			bw.flush();
			bw.close();
		}catch(Exception ex){
			this.util.bc("VAR 변수 파일을 저장하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
		}
		try{
			File f = new File("plugins\\SH TCL\\stringlist.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String n = "";
			boolean b = true;
			for(String l : this.stringlist){
				if(b){
					n = l + " : " + string.get(l);
					b = false;
				}else{
					n += "\n" + l + " : " + string.get(l);
				}
			}
			bw.append(n);
			bw.flush();
			bw.close();
		}catch(Exception ex){
			this.util.bc("STRING 변수 파일을 저장하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
		}
		try{
			File f = new File("plugins\\SH TCL\\commandlist.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String n = "";
			boolean b = true;
			for(String l : this.commandlist){
				if(b){
					n = l;
					b = false;
				}else{
					n += "\n" + l;
				}
			}
			bw.append(n);
			bw.flush();
			bw.close();
		}catch(Exception ex){
			this.util.bc("커맨드리스트 파일을 저장하다가 오류가 발생하였습니다!");
			this.util.bc(ex.getMessage());
		}
		try{
			File f = new File("plugins\\SH TCL\\overrideandop.data");
			if(f.exists()){
				new File("plugins\\SH TCL\\").mkdirs();
				f.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String n = "";
			boolean b = true;
			for(String l : this.commandlist){
				if(b){
					n = l + ":" + commandOvAOp.get(l).get(0).toString() + "," + commandOvAOp.get(l).get(1).toString();
					b = false;
				}else{
					n += "\n" + l + ":" + commandOvAOp.get(l).get(0).toString() + "," + commandOvAOp.get(l).get(1).toString();
				}
			}
			bw.append(n);
			bw.flush();
			bw.close();
		}catch(Exception ex){
			this.util.bc("Override, op 파일을 저장하다가 오류가 발생하였습니다!");
		}
	}
}

