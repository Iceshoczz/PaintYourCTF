package me.Neatoro.PaintYourCTF.Voting;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SetTeams extends BukkitRunnable {
	
	PaintYourCTF plg;
	
	public SetTeams(PaintYourCTF plg) {
		this.plg = plg;
	}

	public void run() {
		Player[] online = plg.getServer().getOnlinePlayers();
		boolean currentteam = true;
		for(Player p : online) {
			if(currentteam == true) {
				plg.team1.add(p);
				p.getInventory().clear();
				p.getInventory().addItem(new ItemStack(277, 1));
				currentteam = false;
			} else {
				plg.team2.add(p);
				p.getInventory().clear();
				p.getInventory().addItem(new ItemStack(277, 1));
				currentteam = true;
			}
		}
		plg.getServer().getScheduler().runTask(plg, new TeleportArena(plg));
	}

}
