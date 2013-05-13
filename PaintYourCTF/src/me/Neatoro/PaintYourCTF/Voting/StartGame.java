package me.Neatoro.PaintYourCTF.Voting;

import me.Neatoro.PaintYourCTF.PaintYourCTF;
import me.Neatoro.PaintYourCTF.Listener.ListenerPlayerGetDamage;

import org.bukkit.scheduler.BukkitRunnable;

public class StartGame extends BukkitRunnable {
	
	PaintYourCTF plg;
	
	public StartGame(PaintYourCTF plg) {
		this.plg = plg;
	}

	public void run() {
		plg.getServer().getPluginManager().registerEvents(new ListenerPlayerGetDamage(plg, plg.team1, plg.team2), plg);
	}

}
