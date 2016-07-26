package traps;

import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import grid.Tile;

public class Trap extends Tile{
	
	protected int damage;
	protected int range;
	protected int defense;
	protected int maxHp;
	protected double critChance;
	protected int armorPen;
	protected double pierceChance;
	protected int bleed;
	protected int gatlingRampPerShot;
	protected int gatlingCap;
	protected int warmUpTime;
	protected double accuracy;
	protected double cooldown;
	protected double maxCd;
	
	public Trap(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
	}
	
	public void whenTriggered(Point p) {
		//Stub
	}
}
