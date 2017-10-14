package com.SHGroup.SHTCL.command;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.SHGroup.SHTCL.Main;

public class shskcommand implements CommandExecutor{
	Main plugin;
	public shskcommand(Main instance){
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
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("list")){
				if(plugin.commandlist.isEmpty()){
					plugin.util.msg(sender, "커맨드를 찾을 수 없습니다.");
					return true;
				}
				int i = 1;
				plugin.util.msg(sender, "커맨드 목록");
				for(String n : plugin.commandlist){
					plugin.util.msg(sender, i + " : " + n);
				}
			}else{
				plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
				plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
			}
		}else if(args.length > 2){
			if(args[0].equalsIgnoreCase("Add")){
				if(plugin.commandlist.contains(args[1])){
					String skriptline = "";
					for(int i = 2 ; i < args.length ; i++){
						if(i == 2){
							skriptline = args[i];
							continue;
						}else{
							skriptline += " " + args[i];
						}
					}
					if(plugin.isMakingBefore("command", args[1])){
						plugin.addSkript("command", args[1], skriptline);
					}else{
						plugin.MakingFile("command", args[1]);
						ArrayList<String> list = new ArrayList<String>();
						list.add(skriptline);
						plugin.setSkript("command", args[1], list);
					}
					plugin.util.msg(sender, "\"" + args[1] + "\" 커맨드에 " + "\"" + args[2] + "\" 스크립트가 추가되었습니다.");
					return true;
				}else{
					plugin.util.msg(sender, "커맨드를 찾을 수 않습니다.");
					plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
					return true;
				}
			}
			if(args.length > 3){
				if(args[0].equalsIgnoreCase("Set")){
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
					if(!plugin.isMakingBefore("command", args[1])){
						plugin.MakingFile("command", args[1]);
					}
					list = plugin.getSkript("command", args[1]);
					try{
						list.set((line - 1), skriptline);
					}catch(Exception ex){
						plugin.util.msg(sender, "줄이 올바르지 않습니다!");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
						return true;
					}
					plugin.setSkript("command", args[1], list);
					plugin.util.msg(sender, "\"" + args[1] + "\" 커맨드의 \"" + args[2] + "\"줄이 \"" + args[3] + "\" 스크립트로 설정되었습니다.");
					return true;
					
				}
			}
		}
		if(args.length == 3){
			if(args[0].equalsIgnoreCase("Ov") || args[0].equalsIgnoreCase("override")){
				if(plugin.commandlist.contains(args[1])){
					if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
						ArrayList<Boolean> list = plugin.commandOvAOp.get(args[1]);
						list.set(1, args[2].equalsIgnoreCase("true"));
						plugin.commandOvAOp.put(args[1], list);
						plugin.util.msg(sender, "\"" + args[1] + "\" 커맨드의 덮어쓰기기능이 " + (args[2].equalsIgnoreCase("true")?"켜졌":"꺼졌") +  "습니다.");
						return true;
					}else{
						plugin.util.msg(sender, "커맨드가 올바르지 않습니다(true / false)");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
					}
				}else{
					plugin.util.msg(sender, "커맨드를 찾을 수 않습니다.");
					plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
				}
			}if(args[0].equalsIgnoreCase("setPower")){
				if(plugin.commandlist.contains(args[1])){
					if(args[2].equalsIgnoreCase("OP") || args[2].equalsIgnoreCase("default")){
						ArrayList<Boolean> list = plugin.commandOvAOp.get(args[1]);
						list.set(0, args[2].equalsIgnoreCase("OP"));
						plugin.commandOvAOp.put(args[1], list);
						plugin.util.msg(sender, "\"" + args[1] + "\" 커맨드가 " + (args[2].equalsIgnoreCase("OP")?"OP":"모든 유저") + "전용 이 되었습니다.");
						return true;
					}else{
						plugin.util.msg(sender, "커맨드가 올바르지 않습니다(OP / default)");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
					}
				}else{
					plugin.util.msg(sender, "커맨드를 찾을 수 않습니다.");
					plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
				}
			}else if(args[0].equalsIgnoreCase("create")){
				if(!plugin.commandlist.contains(args[1])){
					if(plugin.eventlist.contains(args[1])){
						plugin.util.msg(sender, "이벤트 이름으로 커맨드를 만들 수 없습니다.");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
						return true;
					}
					if(args[2].equalsIgnoreCase("default") || args[2].equalsIgnoreCase("OP")){
						plugin.commandlist.add(args[1]);
						plugin.MakingFile("command", args[1]);
						ArrayList<Boolean> list = new ArrayList<Boolean>();
						list.add(args[2].equalsIgnoreCase("OP"));
						list.add(false);
						plugin.commandOvAOp.put(args[1], list);
						plugin.util.msg(sender, "\"" + args[1] + "\", " + (args[2].equalsIgnoreCase("OP")?"OP":"모든 유저") +  " 전용 변수가 생성되었습니다.");
					}else{
						plugin.util.msg(sender, "명령어가 올바르지 않습니다.(default/OP)");
						plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
					}
				}else{
					plugin.util.msg(sender, "이미 존재하는 커맨드입니다.");
					plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
				}
			}else{
				plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
				plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
			}
		}else{
			plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
			plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
		}
		return false;
	}
}
