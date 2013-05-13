package me.Neatoro.PaintYourCTF.Commands;

import java.sql.SQLException;
import java.util.Vector;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandVote {
	
	Player player; 
	PaintYourCTF plugin;
	String[] args;
	
	public CommandVote(Player player, PaintYourCTF plugin, String[] args) {
		this.player = player;
		this.plugin = plugin;
		this.args = args;
	}
	
	public boolean run() {
		if(plugin.status.equalsIgnoreCase("Voting!")) {
			int num = 0;
			if((num = Integer.parseInt(args[1])) != 0) {
				try {
					if(num <= plugin.rs.getFetchSize() && num > 0) {
						Vector<Player> votes = plugin.votes.get(num);
						votes.add(player);
						player.sendMessage(ChatColor.GOLD + "You voted!");
					} else {
						player.sendMessage(ChatColor.RED + "Your choice is not given!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				player.sendMessage(ChatColor.RED + "You give an number to vote!");
			}
		} else {
			player.sendMessage(ChatColor.RED + "That is not the time to vote!");
		}
		return true;
	}

}
