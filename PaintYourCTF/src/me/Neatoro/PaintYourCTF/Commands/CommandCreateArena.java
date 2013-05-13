package me.Neatoro.PaintYourCTF.Commands;

import java.sql.SQLException;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandCreateArena {
	
	Player player; 
	PaintYourCTF plugin;
	String[] args;
	
	public CommandCreateArena(Player player, PaintYourCTF plugin, String[] args) {
		this.player = player;
		this.plugin = plugin;
		this.args = args;
	}
	
	public boolean run() {
		try {
			plugin.arena.execute("insert into arena(name) values('" + args[1] + "')");
			plugin.spawns.execute("create table " + args[1] + "(id integer, x integer, y integer, z integer)");
			player.sendMessage(ChatColor.GOLD + "You created the arena " + args[1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
