package com.github.Gamecube762.Bukkit.SaferVoid;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.Gamecube762.Bukkit.SaferVoid.Handlers.PlayerEventHandler;

public class Main extends JavaPlugin{
	
	public Logger log;
	public FileConfiguration config;

	@Override
	public void onEnable() {
		this.log = this.getLogger();
		this.config = this.getConfig();
		this.saveDefaultConfig();
		this.config.options().copyDefaults(true);
		
		
		getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);
		
		getLogger().info("By: "+getDescription().getAuthors().toString());
	}
	
	@Override
	public void onDisable() { }

}
