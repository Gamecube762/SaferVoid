package com.github.Gamecube762.Bukkit.SaferVoid.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.github.Gamecube762.Bukkit.SaferVoid.Forces;
import com.github.Gamecube762.Bukkit.SaferVoid.Runnables.VoidFlight;

public class PlayerEventHandler implements Listener {
	
	public static String pathPrefix = "Settings.Void";
	public static String VoidLevel = ".VoidLevel";
	public static String pathSuffix;
	
	public FileConfiguration config;
	public Plugin plugin;
	
	public PlayerEventHandler(Plugin pl){
		this.plugin = pl;
		this.config = this.plugin.getConfig();
	}
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location pLoc = p.getLocation();
		
		if ( p.hasPermission("sv.void.bounce") ) {
			if (pLoc.getY() <= this.config.getDouble(pathPrefix+"Bounce"+VoidLevel) ) {
				Forces.bounceUp(p);
				p.setFallDistance(0);//so they dont die when they land from the fall distance adding up
			}
		}
			
		if ( p.hasPermission("sv.void.teleport") ) {
			if (pLoc.getY() <= this.config.getDouble(pathPrefix+"Teleport"+VoidLevel) ) {
				boolean a = this.config.getBoolean( pathPrefix + "Teleport." + p.getWorld().getName() );
				if (a) {
					World w = Bukkit.getWorld( this.config.getString(pathPrefix + "Teleport." + p.getWorld().getName() + ".w") );
					if (w==null) {a=false;p.sendMessage("false");}
					else {
						double x = this.config.getDouble(pathPrefix + "Teleport." + p.getWorld().getName() + "x");
						double y = this.config.getDouble(pathPrefix + "Teleport." + p.getWorld().getName() + "y");
						double z = this.config.getDouble(pathPrefix + "Teleport." + p.getWorld().getName() + "z");
						p.setFallDistance(0);
						p.teleport(new Location(w, x, y, z));
					}
				} else {
					p.setFallDistance(0);
					p.teleport(p.getWorld().getSpawnLocation());
				}
			}
		}
		
		if (p.hasPermission("sv.void.fix")) {
			if (pLoc.getY() <= this.config.getDouble(pathPrefix+"Fix"+VoidLevel) ) {
				Location t = new Location(pLoc.getWorld(), pLoc.getX(), 1.0D, pLoc.getZ());
				p.setFallDistance(0);
				p.teleport(t);
				pLoc.getWorld().getBlockAt(t).getRelative(BlockFace.DOWN).setTypeId( this.config.getInt( pathPrefix + "Fix" + ".BlockID" ) );
			}
		}
		
		if (p.hasPermission("sv.void.fly")) {
			if (pLoc.getY() <= this.config.getDouble(pathPrefix+"Fly"+VoidLevel) ) {
				if(!p.getAllowFlight()){
					p.setAllowFlight(true);
					p.setFlying(true);
					if(p.getGameMode()!=GameMode.CREATIVE){ //auto flight off timer to prevent flight abuse (disabled if in creative mode)
						VoidFlight.CreateRunable(plugin, p, p.getWorld(), this.config.getInt(pathPrefix+"Fly.Timer")*20);
					}
				}
			}
		}
		
		
	}//method end
	
	
	
	
	@EventHandler
	public void PlayerSwapWorld(PlayerChangedWorldEvent e) {	
		//add code here
	}
	
}
