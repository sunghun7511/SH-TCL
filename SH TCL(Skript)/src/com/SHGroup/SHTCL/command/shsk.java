package com.SHGroup.SHTCL.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import com.SHGroup.SHTCL.Main;

public class shsk implements CommandExecutor {
	Main plugin;

	public shsk(Main instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		if(args.length == 0){
			plugin.util.msg(sender, "제작 : sunghun7511");
			plugin.util.msg(sender, "SH TCL 플러그인");
			plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
			return true;
		}else if(args.length == 1){
			if(args[0].equalsIgnoreCase("help")){
				plugin.util.msg(sender, "SH TCL 플러그인 도움말");
				plugin.util.msg(sender, "/TCL create <이벤트 이름>");
				plugin.util.msg(sender, "/TCLEvent Add <이벤트 이름> <스크립트라인>");
				plugin.util.msg(sender, "/TCLEvent Set <이벤트 이름> <색인> <스크립트라인>");
				plugin.util.msg(sender, "/TCLCmd create <커맨드 이름> <default/OP>");
				plugin.util.msg(sender, "/TCLCmd setPower <커맨드 이름> <default/OP>");
				plugin.util.msg(sender, "/TCLCmd Ov <커맨드 이름> <true/false>");
				plugin.util.msg(sender, "/TCLCmd Add <커맨드 이름> <스크립트라인>");
				plugin.util.msg(sender, "/TCLCmd Set <커맨드 이름> <색인> <스크립트라인>");
				plugin.util.msg(sender, "/TCLCmd list");
				plugin.util.msg(sender, "/TCLRemove All");
				plugin.util.msg(sender, "/TCLRemove Last <커맨드 이름 / 이벤트 이름>");
				plugin.util.msg(sender, "/TCL printError");
				plugin.util.msg(sender, "/TCL help");
				plugin.util.msg(sender, "/TCL");
				plugin.util.msg(sender, "이벤트 목록");
				plugin.util.msg(sender, " PlayerQuit / BlockPlace / BlockBreak / PlayerJoin");
				plugin.util.msg(sender, " PlayerClick / PlayerMove / PlayerChat");
				return true;
			}else if(args[0].equalsIgnoreCase("printError")){
				if(!sender.isOp()){
					Player p = (Player) sender;
					if(!plugin.util.hasPerm(p, "SHTCL.use")){
						plugin.util.msg(sender, "당신은 권한이 없습니다!");
						return true;
					}
				}
				if(plugin.printError){
					plugin.printError = false;
					plugin.util.bc(plugin.util.getPerfix() + "에러 출력이 꺼졌습니다.");
				}else{
					plugin.printError = true;
					plugin.util.bc(plugin.util.getPerfix() + "에러 출력이 켜졌습니다.");
				}
				return true;
			}else{
				plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
				plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
				return true;
			}
		}else{
			plugin.util.msg(sender, "명령어가 올바르지 않습니다.");
			plugin.util.msg(sender, "도움말을 보려면 /TCL help 를 입력해주세요");
			return true;
		}
	}
}
