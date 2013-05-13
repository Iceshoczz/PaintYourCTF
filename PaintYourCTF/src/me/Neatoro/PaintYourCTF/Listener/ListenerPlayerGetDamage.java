package me.Neatoro.PaintYourCTF.Listener;

import java.util.Vector;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ListenerPlayerGetDamage implements Listener {
	public PaintYourCTF plugin;
	public Vector<Player> team1;
	public Vector<Player> team2;
	
	public ListenerPlayerGetDamage(PaintYourCTF plugin, Vector<Player> team1, Vector<Player> team2) {
		this.plugin = plugin;
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public void setTeams(Vector<Player> team1, Vector<Player> team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		Entity thrower = event.getDamager();
		if(thrower instanceof Snowball) {
			Snowball sb = (Snowball) thrower;
			LivingEntity le = sb.getShooter();
			Entity damaged = event.getEntity();
			if(damaged instanceof Player && le instanceof Player) {
				Player p1 = (Player) le;
				Player p2 = (Player) damaged;
				int pt1 = 0;
				int pt2 = 0;
				for(int i = 0; i < team1.size(); i++){
					if(pt1 != 0 && pt2 != 0) {
						break;
					}
					Player pteam1 = team1.get(i);
					if(pteam1.equals(p1)) {
						pt1 = 1;
					}
					if(pteam1.equals(p2)) {
						pt2 = 1;
					}
				}
				for(int i = 0; i < team2.size(); i++){
					if(pt1 != 0 && pt2 != 0) {
						break;
					}
					Player pteam2 = team2.get(i);
					if(pteam2.equals(p1)) {
						pt1 = 2;
					}
					if(pteam2.equals(p2)) {
						pt2 = 2;
					}
				}
				if(pt1 != pt2) {
					event.setDamage(5);
				} else {
					event.setCancelled(true);
				}
			}
		}
	}

}
