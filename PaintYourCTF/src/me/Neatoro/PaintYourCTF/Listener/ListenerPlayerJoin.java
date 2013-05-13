package me.Neatoro.PaintYourCTF.Listener;

import java.sql.SQLException;

import me.Neatoro.PaintYourCTF.PaintYourCTF;
import me.Neatoro.PaintYourCTF.Voting.EndVoting;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerPlayerJoin implements Listener {
	
	PaintYourCTF plg;
	
	public ListenerPlayerJoin(PaintYourCTF plg) {
		this.plg = plg;
	}
	
	@EventHandler
	public void handler(PlayerJoinEvent e) {
		if(plg.getServer().getOnlinePlayers().length >= 4) {
			try {
				plg.rs = plg.arena.executeQuery("select * from arena");
				plg.getServer().broadcastMessage(ChatColor.GREEN + "----- Voting -----");
				while(plg.rs.next()) {
					plg.getServer().broadcastMessage(ChatColor.GREEN + "" + plg.rs.getInt(1) + ". " + plg.rs.getString(2));
				}
				if(plg.getServer().getOnlinePlayers().length == 4) {
					plg.getServer().getScheduler().runTaskLater(plg, new EndVoting(plg), 2400);
					plg.status = "Voting!";
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
