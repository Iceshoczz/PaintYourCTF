package me.Neatoro.PaintYourCTF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import me.Neatoro.PaintYourCTF.Commands.CommandCreateArena;
import me.Neatoro.PaintYourCTF.Listener.ListenerPlayerJoin;
import me.Neatoro.PaintYourCTF.Manager.SettingsManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class PaintYourCTF extends JavaPlugin {
	
	public SettingsManager sm;
	public Statement spawns;
	public Statement arena;
	public ResultSet rs;
	public Vector<Vector<Player>> votes;
	private Scoreboard scoreboard = null;
	
	private Team t1 = null;
	private Team t2 = null;
	public Vector<Player> team1 = new Vector<Player>();
	public Vector<Player> team2 = new Vector<Player>();
	
	public String map;
	
	public String status;
	
	public void onEnable() {
		registerEvents();
		loadManager();
		try {
			sm.setup();
			spawns = loadSpawns().createStatement();
			spawns.setQueryTimeout(30);
			arena = loadArena().createStatement();
			arena.setQueryTimeout(30);
			sm.setupDatabase();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		status = "Joinable!";
		System.out.println("-----------------------");
		System.out.println("[PaintYourCTF] enabled!");
		System.out.println("-----------------------");
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new ListenerPlayerJoin(this), this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("ctf")) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("createarena") && args[1] != "") {
						return new CommandCreateArena(p, this, args).run();
					}
				}
			}
		}
		return false;
	}

	public void onDisable() {
		System.out.println("------------------------");
		System.out.println("[PaintYourCTF] disabled!");
		System.out.println("------------------------");
	}
	
	public void loadManager() {
		this.sm = new SettingsManager(this);
	}
	
	public Connection loadSpawns() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:spawn.db");
		return con;
	}
	
	public Connection loadArena() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:arena.db");
		return con;
	}
	
	public void setUpScoreboard() {
		this.scoreboard = this.getServer().getScoreboardManager().getNewScoreboard();
		Objective ob = scoreboard.registerNewObjective("score", "dummy");
		ob.setDisplayName("Scorestreak Points:");
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.t1 = scoreboard.registerNewTeam("team1");
		this.t2 = scoreboard.registerNewTeam("team2");
		t1.setDisplayName("Team 1");
		t2.setDisplayName("Team 2");
		t1.setPrefix("Team 1");
		t2.setPrefix("Team 2");
		t1.setAllowFriendlyFire(false);
		t2.setAllowFriendlyFire(false);
	}
	
	public void setUpScoreboardPlayers() {
		for(int i = 0; i < team1.size(); i++) {
			team1.get(i).setScoreboard(scoreboard);
			t1.addPlayer(team1.get(i));
		}
		for(int i = 0; i < team2.size(); i++) {
			team2.get(i).setScoreboard(scoreboard);
			t2.addPlayer(team2.get(i));
		}
	}
	
	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}
	
}
