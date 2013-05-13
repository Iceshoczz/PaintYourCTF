package me.Neatoro.PaintYourCTF.Voting;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportArena extends BukkitRunnable {
	
	PaintYourCTF plg;
	
	public TeleportArena(PaintYourCTF plg) {
		this.plg = plg;
	}

	public void run() {
		try {
			ResultSet rs1 = plg.spawns.executeQuery("select * from " + plg.map + " where id=1");
			ResultSet rs2 = plg.spawns.executeQuery("select * from " + plg.map + " where id=2");
			int x1 = rs1.getInt(2);
			int y1 = rs1.getInt(2);
			int z1 = rs1.getInt(2);
			int x2 = rs2.getInt(2);
			int y2 = rs2.getInt(2);
			int z2 = rs2.getInt(2);
			Location team1 = new Location(plg.getServer().getOnlinePlayers()[0].getWorld(), x1, y1, z1);
			Location team2 = new Location(plg.getServer().getOnlinePlayers()[0].getWorld(), x2, y2, z2);
			for(int i = 0; i < plg.team1.size(); i++) {
				Player p = plg.team1.get(i);
				p.teleport(team1);
				p.setCustomName(ChatColor.BLUE + p.getDisplayName());
				p.setCustomNameVisible(true);
			}
			for(int i = 0; i < plg.team2.size(); i++) {
				Player p = plg.team2.get(i);
				p.teleport(team2);
				p.setCustomName(ChatColor.RED + p.getDisplayName());
				p.setCustomNameVisible(true);
			}
			plg.getServer().broadcastMessage(ChatColor.RED + "Let the games begin!");
			plg.getServer().getScheduler().runTask(plg, new StartGame(plg));
			plg.status = "Running!";
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
