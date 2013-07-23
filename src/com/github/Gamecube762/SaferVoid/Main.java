package com.github.Gamecube762.SaferVoid;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.Gamecube762.SaferVoid.Handlers.PlayerEventHandler;

public class Main extends JavaPlugin{
	
	public Logger log;
	public FileConfiguration config;
	public ConfigManager CM;

	@Override
	public void onEnable() {
		this.log = this.getLogger();
		this.config = this.getConfig();
		this.CM = new ConfigManager(this);
		
		
		getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);
		
		getLogger().info("By: "+getDescription().getAuthors().toString());
	}
	
	@Override
	public void onDisable() { }
	
	public Main getThis() {return this;}

}
