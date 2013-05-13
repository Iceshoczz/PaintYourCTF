package me.Neatoro.PaintYourCTF.Manager;

import java.io.File;
import java.sql.SQLException;

import me.Neatoro.PaintYourCTF.PaintYourCTF;

public class SettingsManager {
	
	PaintYourCTF plugin;
	
	boolean ne = false;
	
	public SettingsManager(PaintYourCTF plugin) {
		this.plugin = plugin;
	}

	public void setup() {
		File f1 = new File("arena.db");
		if(!f1.exists()) {
			ne = true;
		}
	}
	
	public void setupDatabase() throws SQLException {
		if(ne == true) {
			plugin.arena.execute("create table arena(id integer primary key autoincrement, name)");
		}
	}

}
