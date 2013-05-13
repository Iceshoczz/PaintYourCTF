package me.Neatoro.PaintYourCTF.Voting;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.scheduler.BukkitRunnable;

public class EndVoting extends BukkitRunnable {
	
	PaintYourCTF plg;
	
	public EndVoting(PaintYourCTF plg) {
		this.plg = plg;
	}

	public void run() {
		int id = 0;
		int votes = -1;
		for(int i = 0; i < plg.votes.size(); i++) {
			if(votes < plg.votes.get(i).size()) {
				id = i;
				votes = plg.votes.get(i).size();
			}
		}
		try {
			ResultSet rs = plg.arena.executeQuery("select name from arena where id='" + id + "'");
			plg.map = rs.getString(1);
			plg.getServer().getScheduler().runTask(plg, new SetTeams(plg));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
