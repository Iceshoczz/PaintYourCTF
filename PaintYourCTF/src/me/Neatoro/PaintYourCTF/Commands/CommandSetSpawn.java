package me.Neatoro.PaintYourCTF.Commands;

import java.sql.SQLException;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandSetSpawn {
	
	Player player; 
	PaintYourCTF plugin;
	String[] args;
	
	public CommandSetSpawn(Player player, PaintYourCTF plugin, String[] args) {
		this.player = player;
		this.plugin = plugin;
		this.args = args;
	}
	
	public boolean run() {
		try {
			plugin.arena.execute("insert into " + args[1] + " values(" + args[2] + ", " + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + ")");
			player.sendMessage(ChatColor.GOLD + "You set the spawn in the arena " + args[1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
