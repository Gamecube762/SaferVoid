package com.github.Gamecube762.SaferVoid;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
	
	public Main main;
	public FileConfiguration config;
	
	public ConfigManager(Main m) {
		this.main = m;
		this.config = this.main.getConfig();
		
		this.config.options().copyDefaults(true);
		this.main.saveDefaultConfig();
	}

	public FileConfiguration getDefaultConfig() {
		return this.main.getConfig();
	}
	
	public FileConfiguration reloadConfig() {
		this.config = this.main.getConfig();
		return this.config;
	}
	
	public void saveConfig() {
		this.main.saveConfig();
	}
	
}
