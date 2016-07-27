package traps;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import upgrades.*;
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
	
	protected int level;	
	protected ArrayList<Upgrade> onHit;
	protected ArrayList<Upgrade> onFire;
	protected ArrayList<Upgrade> onDeath;
	protected ArrayList<Upgrade> onInteract;
	protected ArrayList<Upgrade> onTrigger;
	
	public Trap(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
		onHit = new ArrayList<Upgrade>();
		onFire = new ArrayList<Upgrade>();
		onDeath = new ArrayList<Upgrade>();
		onInteract = new ArrayList<Upgrade>();
		onTrigger = new ArrayList<Upgrade>();
	}
	
	public void whenTriggered(Point p) {
		for(Upgrade u : onTrigger) {
			u.upgrade(this);
		}
	}
	
	
	public void upgrade(Upgrade powerUp) {
		int triggerLoc = powerUp.getTriggerLoc();
		
		//0=One Time 1 = On Hit 2 = On Fire 3 = On Interact 4 = On Trigger 5 = On Death
		if(triggerLoc == 0)
			powerUp.upgrade(this);
		if(triggerLoc == 1)
			onHit.add(powerUp);
		if(triggerLoc == 2)
			onFire.add(powerUp);
		if(triggerLoc == 3)
			onInteract.add(powerUp);
		if(triggerLoc == 4) 
			onTrigger.add(powerUp);
		if(triggerLoc == 5) 
			onDeath.add(powerUp);
	}
}
