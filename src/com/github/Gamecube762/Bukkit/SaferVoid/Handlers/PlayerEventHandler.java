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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
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
				int time = this.config.getInt(pathPrefix+"Fly.Timer");
				if (time < 0 & time != -1) {time *= -1; this.config.set(pathPrefix+"Fly.Timer", time);}//make time positive unless -1(no shut-off timer)

				if (!p.getAllowFlight()) p.setAllowFlight(true);
				if (!p.isFlying()) p.setFlying(true);
				
				if(!p.isFlying()|p.getGameMode()!=GameMode.CREATIVE|time!=-1){ //auto flight off timer to prevent flight abuse (disabled if in creative mode)
					VoidFlight.CreateRunable(plugin, p, p.getWorld(), time*20);
				}
			}
		}
		
		
	}//method end
	
	
	
	
	@EventHandler
	public void PlayerFlightChange(PlayerToggleFlightEvent e) {	
		//add code here to prevent flight abuse
	}
	
}
