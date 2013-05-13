package me.Neatoro.PaintYourCTF.Listener;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ListenerServerPing implements Listener {
	
	PaintYourCTF plugin;
	
	public ListenerServerPing(PaintYourCTF plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void handler(ServerListPingEvent e) {
		e.setMotd(plugin.status);
	}

}
