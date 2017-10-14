package com.SHGroup.SHTCL.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.SHGroup.SHTCL.Main;

public class shskremove implements CommandExecutor{
	Main plugin;
	public shskremove(Main instance){
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
			if(args[0].equalsIgnoreCase("ALL")){
				plugin.RemoveAllSkript();
				for(String n : plugin.commandlist){
					plugin.setSkript("command", n, new java.util.ArrayList<String>());
				}
				plugin.commandlist.clear();
				plugin.commandOvAOp.clear();
				plugin.util.msg(sender, "모든 스크립트가 제거되었습니다.");
			}else{
				plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
				plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
			}
			return true;
		}else if(args.length == 2){
			if(args[0].equalsIgnoreCase("Last")){
				String name = args[1];
				if(plugin.commandlist.contains(name)){
					java.util.ArrayList<String> list = plugin.getSkript("command", name);
					if(list.isEmpty()){
						plugin.util.msg(sender, "해당 커맨드는 입력된 스크립트값이 없습니다.");
						return true;
					}
					String n = list.get(list.size() - 1);
					list.remove((list.size() - 1));
					plugin.setSkript("command", name, list);
					plugin.util.msg(sender, "\"" + name + "\"커맨드의 \"" + n + "\" 스크립트가 삭제되었습니다.");
				}else if(plugin.eventlist.contains(name)){
					java.util.ArrayList<String> list = plugin.getSkript("event", name);
					if(list.isEmpty()){
						plugin.util.msg(sender, "해당 이벤트는 입력된 스크립트값이 없습니다.");
						return true;
					}
					String n = list.get(list.size() - 1);
					list.remove((list.size() - 1));
					plugin.setSkript("event", name, list);
					plugin.util.msg(sender, "\"" + name + "\"이벤트의 \"" + n + "\" 스크립트가 삭제되었습니다.");
				}else{
					plugin.util.msg(sender, "해당 이벤트 / 커맨드를 찾을 수 없습니다.");
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
