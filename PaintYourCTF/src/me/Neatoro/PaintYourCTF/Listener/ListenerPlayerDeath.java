package me.Neatoro.PaintYourCTF.Listener;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

import org.bukkit.event.Listener;

public class ListenerPlayerDeath implements Listener{
	public PaintYourCTF plugin;
	
	public ListenerPlayerDeath(PaintYourCTF plugin) {
		this.plugin = plugin;
	}
}
