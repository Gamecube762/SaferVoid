package com.github.Gamecube762.Bukkit.SaferVoid.Runnables;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VoidFlight extends BukkitRunnable{

	private Player p;
	private World w;
	private World pw;
	
	public VoidFlight(Player player, World world){
		this.p = player;//player
		this.w = world;//world this started in
		this.pw = this.p.getWorld();//gets player world
	}
	
    public void run() {
    	this.pw = this.p.getWorld();//ran again to update current world
    	if (this.pw==this.w & p.getGameMode()!=GameMode.CREATIVE){
    		p.setFlying(false);
    		p.setAllowFlight(false);
    	}
    }
    
    public static void CreateRunable(Plugin plugin, Player p, World pw, int ticks){
		@SuppressWarnings("unused")
		BukkitTask flyOff = new VoidFlight(p, pw).runTaskLater(plugin, ticks);
    }
}
