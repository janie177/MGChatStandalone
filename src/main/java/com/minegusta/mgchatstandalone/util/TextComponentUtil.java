package com.minegusta.mgchatstandalone.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class TextComponentUtil {

	public static TextComponent stringToComp(String string){
		TextComponent base = new TextComponent("");
		boolean previousLetter = false;
		ChatColor currentColor = null;
		boolean bold = false;
		boolean italic = false;
		boolean underline = false;
		boolean strike = false;
		boolean magic = false;
		String currentstring = "";
		for (int i = 0; i < string.length(); i++){
			char c = string.charAt(i);
			if(c=='§'){
				if(string.charAt(i+1)=='l'){
					if(previousLetter){
						TextComponent newTC = new TextComponent(currentstring);
						if(currentColor!=null){ newTC.setColor(currentColor); }
						newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
						newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
						base.addExtra(newTC);
						bold = false; italic = false; underline = false; strike = false; magic = false;
						currentstring = "";
						currentColor = null;
						i++;
						previousLetter=false;
					}else{
						bold = true;
						i++;
					}
				}else if(string.charAt(i+1)=='k'){
					if(previousLetter){
						TextComponent newTC = new TextComponent(currentstring);
						if(currentColor!=null){ newTC.setColor(currentColor); }
						newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
						newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
						base.addExtra(newTC);
						bold = false; italic = false; underline = false; strike = false; magic = false;
						currentstring = "";
						currentColor = null;
						i++;
						previousLetter=false;
					}else{
						magic = true;
						i++;
					}
				}else if(string.charAt(i+1)=='m'){
					if(previousLetter){
						TextComponent newTC = new TextComponent(currentstring);
						if(currentColor!=null){ newTC.setColor(currentColor); }
						newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
						newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
						base.addExtra(newTC);
						bold = false; italic = false; underline = false; strike = false; magic = false;
						currentstring = "";
						currentColor = null;
						i++;
						previousLetter=false;
					}else{
						strike = true;
						i++;
					}
				}else if(string.charAt(i+1)=='n'){
					if(previousLetter){
						TextComponent newTC = new TextComponent(currentstring);
						if(currentColor!=null){ newTC.setColor(currentColor); }
						newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
						newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
						base.addExtra(newTC);
						bold = false; italic = false; underline = false; strike = false; magic = false;
						currentstring = "";
						currentColor = null;
						i++;
						previousLetter=false;
					}else{
						underline = true;
						i++;
					}
				}else if(string.charAt(i+1)=='o'){
					if(previousLetter){
						TextComponent newTC = new TextComponent(currentstring);
						if(currentColor!=null){ newTC.setColor(currentColor); }
						newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
						newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
						base.addExtra(newTC);
						bold = false; italic = false; underline = false; strike = false; magic = false;
						currentstring = "";
						currentColor = null;
						i++;
						previousLetter=false;
					}else{
						italic = true;
						i++;
					}
				}else if(string.charAt(i+1)=='r'){
					TextComponent newTC = new TextComponent(currentstring);
					if(currentColor!=null){ newTC.setColor(currentColor); }
					newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
					newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
					base.addExtra(newTC);
					bold = false; italic = false; underline = false; strike = false; magic = false;
					currentstring = "";
					currentColor = null;
					i++;
					previousLetter=false;
				}
				else if(ChatColor.getByChar(string.charAt(i+1))!=null){
					if(previousLetter){
						TextComponent newTC = new TextComponent(currentstring);
						if(currentColor!=null){ newTC.setColor(currentColor); }
						newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
						newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
						base.addExtra(newTC);
						bold = false; italic = false; underline = false; strike = false; magic = false;
						currentColor = ChatColor.getByChar(string.charAt(i+1));
						currentstring = "";
						i++;
						previousLetter=false;
					}else{
						currentColor = ChatColor.getByChar(string.charAt(i+1));
						i++;
					}
				}else{
					previousLetter = true;
					currentstring = currentstring+c;
				}
			}
			else{
				previousLetter = true;
				currentstring = currentstring+c;
			}
		}
		TextComponent newTC = new TextComponent(currentstring);
		if(currentColor!=null){ newTC.setColor(currentColor); }
		newTC.setBold(bold); newTC.setItalic(italic); newTC.setUnderlined(underline);
		newTC.setStrikethrough(strike); newTC.setObfuscated(magic);
		base.addExtra(newTC);
		return base;
	}
}
