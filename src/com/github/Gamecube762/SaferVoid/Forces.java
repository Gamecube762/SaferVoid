package com.github.Gamecube762.SaferVoid;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Forces{
	
	public static void bounceUp(Player p) {
		Vector Vec = p.getVelocity();
		Vector newVec = Vec;
		if (Vec.getY() < 0) {newVec = Reverse(Vec, false, true, false);}//reverse Y if player is moving down
		if (newVec.getY() <= 0.56) {newVec.setY(newVec.getBlockY()+0.7);}//if player isnt moving fast enough, add some extra force
		p.setVelocity(newVec);
	}
	
	public static void bounceDown(Player p) {
		Vector Vec = p.getVelocity();
		Vector newVec = Vec;
		if (Vec.getY() > 0) {newVec = Reverse(Vec, false, true, false);}//reverse Y if player is moving down
		if (newVec.getY() <= 0.56) {newVec.setY(newVec.getBlockY()+0.7);}//if player isnt moving fast enough, add some extra force
		p.setVelocity(newVec);
	}
	
	public static Vector Reverse(Vector Vec, boolean X, boolean Y, boolean Z) {
		if (X) {Vec.setX(Vec.getX()*-1);}
		if (Y) {Vec.setY(Vec.getY()*-1);}
		if (Z) {Vec.setZ(Vec.getZ()*-1);}
		return Vec;
	}
	
	public static Vector Double(Vector Vec, boolean X, boolean Y, boolean Z) {
		if (X) {Vec.setX(Vec.getX()*2);}
		if (Y) {Vec.setY(Vec.getY()*2);}
		if (Z) {Vec.setZ(Vec.getZ()*2);}
		return Vec;
	}
	public static Vector Half(Vector Vec, boolean X, boolean Y, boolean Z) {
		if (X) {Vec.setX(Vec.getX()*0.5);}
		if (Y) {Vec.setY(Vec.getY()*0.5);}
		if (Z) {Vec.setZ(Vec.getZ()*0.5);}
		return Vec;
	}
	
	
	
	public int booleantoint(boolean b) {if (b) {return 1;} return 0;}
}
