package traps;

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
	
	public Trap(float size, Vector2f location) {
		super(size, location);
	}
}
