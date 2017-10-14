package com.SHGroup.SHTCL.command;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.SHGroup.SHTCL.Main;

public class shskevent implements CommandExecutor{
	Main plugin;
	public shskevent(Main instance){
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		if(!sender.isOp()){
			Player p = (Player) sender;
			if(!plugin.util.hasPerm(p, "SHTCL.use")){
				plugin.util.msg(sender, "당신은 권한이 없습니다!");
				return true;
			}
		}
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("create")){
				if(plugin.eventlist.contains(args[1])){
					if(!plugin.isMakingBefore("event", args[1])){
						plugin.MakingFile("event", args[1]);
						plugin.util.msg(sender, "\"" + args[1] + "\" 이벤트가 생성되었습니다!");
					}else{
						plugin.util.msg(sender, "이미 존재하는 이벤트입니다!");
						return true;
					}
				}else{
					plugin.util.msg(sender, "이벤트를 찾을 수 않습니다.");
					plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
					return true;
				}
			}else{
				plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
				plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
				return true;
			}
		}else if(args.length > 2){
			if(args[0].equalsIgnoreCase("Add")){
				if(plugin.eventlist.contains(args[1])){
					if(plugin.isMakingBefore("event", args[1])){
						String skriptline = "";
						for(int i = 2 ; i < args.length ; i++){
							if(i == 2){
								skriptline = args[i];
							}else{
								skriptline += " " + args[i];
							}
						}
						plugin.addSkript("event", args[1], skriptline);
						plugin.util.msg(sender, "\"" + args[1] + "\" 에 " + skriptline + "가 추가되었습니다.");
						return true;
					}else{
						plugin.util.msg(sender, "이벤트를 생성하지 않았습니다!");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
						return true;
					}
				}else{
					plugin.util.msg(sender, "이벤트를 찾을 수 않습니다.");
					plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
					return true;
				}
			}
			if(args.length > 3){
				if(args[0].equalsIgnoreCase("Set")){
					if(plugin.eventlist.contains(args[1])){
						if(plugin.isMakingBefore("event", args[1])){
							String skriptline = "";
							int line = 0;
							try{
								line = Integer.parseInt(args[2]);
							}catch(Exception ex){
								plugin.util.msg(sender, "줄이 올바르지 않습니다!");
								plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
								return true;
							}
							for(int i = 3 ; i < args.length ; i++){
								if(i == 3){
									skriptline = args[i];
									continue;
								}else{
									skriptline += " " + args[i];
								}
							}
			
							ArrayList<String> list = null;
							if(!plugin.isMakingBefore("event", args[1])){
								plugin.MakingFile("event", args[1]);
							}
							list = plugin.getSkript("event", args[1]);
							try{
								list.set((line - 1), skriptline);
							}catch(Exception ex){
								plugin.util.msg(sender, "줄이 올바르지 않습니다!");
								plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
								return true;
							}
							plugin.setSkript("event", args[1], list);
							plugin.util.msg(sender, "\"" + args[1] + "\" 이벤트의 \"" + args[2] + "\"줄이 \"" + args[3] + "\" 스크립트로 설정되었습니다.");
							return true;
						}else{
							plugin.util.msg(sender, "이벤트를 생성하지 않았습니다!");
							plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
							return true;
						}
					}else{
						plugin.util.msg(sender, "이벤트를 찾을 수 않습니다.");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
						return true;
					}
				}
			}
		}
		plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
		plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
		return false;
	}
}
