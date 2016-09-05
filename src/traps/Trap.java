package traps;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;
import upgrades.*;
import grid.Blank;
import grid.Grid;
import grid.Tile;
import librarys.TileLibrary;
import main.Main;
import pathing.Group;

public class Trap extends Tile{
	
	protected double damage;
	protected int range;
	protected int defense;
	protected double maxHp;
	protected double critChance;
	protected int armorPen;
	protected double pierceChance;
	protected int bleed;
	protected double warmUpTime;
	protected double accuracy;
	protected double cooldown;
	protected double maxCd;
	protected int level;	
	
	protected ArrayList<Upgrade> allUpgrades;
	protected ArrayList<Upgrade> onHit;
	protected ArrayList<Upgrade> onFire;
	protected ArrayList<Upgrade> onDeath;
	protected ArrayList<Upgrade> onInteract;
	protected ArrayList<Upgrade> onTrigger;
	
	public Trap(int x, int y, float size, int floor) {
		super(x, y, size, floor);
		allUpgrades = new ArrayList<Upgrade>();
		onHit = new ArrayList<Upgrade>();
		onFire = new ArrayList<Upgrade>();
		onDeath = new ArrayList<Upgrade>();
		onInteract = new ArrayList<Upgrade>();
		onTrigger = new ArrayList<Upgrade>();
		maxHp = 100;
		hp = maxHp;
	}
	
	//Wall of gets/sets
	public int getLevel() {
		return level;
	}
	
	public ArrayList<Upgrade> getAllUpgrades() {
		return allUpgrades;
	}
	
	public void setLevel(int i) {
		damage = i;
	}
	
	public double getMaxCd() {
		return maxCd;
	}
	
	public void setMaxCd(double i) {
		maxCd = i;
	}
	
	public double getCooldown() {
		return cooldown;
	}
	
	public void setCooldown(double i) {
		cooldown = i;
	}
	
	public double getAccuracy() {
		return accuracy;
	}
	
	public void setAccuracy(double i) {
		accuracy = i;
	}
	
	public int getBleedDamage() {
		return bleed;
	}
	
	public void setBleedDamage(int i) {
		bleed = i;
	}
	
	public double getWarmUpTime() {
		return warmUpTime;
	}
	
	public void setWarmUpTime(double i) {
		warmUpTime = i;
	}
	
	public double getRealDamage() {
		//If it misses, it does no damage
		if(accuracy < Math.random()) 
			return 0;
		//If it crits, it deals double damage
		else if(critChance > Math.random()) 
			return 2*damage;
		//Otherwise do normal damage
		else 
			return damage;
	}
	
	
	public double getPierceChance() {
		return pierceChance;
	}
	
	public void setPierceChance(double i) {
		pierceChance = i;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setDamage(int i) {
		damage = i;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int i) {
		defense = i;
	}
	
	public void setRange(int i) {
		range = i;
	}
	
	public double getMaxHp() {
		return maxHp;
	}
	
	public void setMaxHp(int i) {
		maxHp = i;
	}
	
	public double getCritChance() {
		return critChance;
	}
	
	public void setCritChance(double i) {
		critChance = i;
	}
	
	public int getArmorPen() {
		return armorPen;
	}
	
	public void setArmorPen(int i) {
		armorPen = i;
	}
	
	public void whenTriggered(Point p) {
		for(Upgrade u : onTrigger) {
			u.upgrade(this);
		}
	}
	
	@Override
	public void interact(Group g, Grid currentFloor) {
		for(Explorer e : g.getGroup()) {
			hp -= e.getDamage()*100/(double)(100+defense);
			System.out.println(e.getDamage()*100/(double)(100+defense));
		}
		if(hp <= 0) {
			for(Upgrade u : onDeath) {
				u.upgrade(this);
			}
			replaceWithBlank(currentFloor);
		}
	}
	
	protected int getWidthOfGrid() {
		if(floor<=Grid.getMaximumWidth()-Grid.getMinimumWidth()) {
			return floor+Grid.getMinimumWidth();
		}
		else return Grid.getMaximumWidth();
	}
	
	protected boolean isInBounds(Point locationOnGrid) {
		return locationOnGrid.x>=0 && locationOnGrid.y>=0 && locationOnGrid.x<getWidthOfGrid() && locationOnGrid.y<getWidthOfGrid();
	}
	
	public ArrayList<Point> getTriggerLocations() {
		return new ArrayList<Point>();
	}
	
	public void upgrade(Upgrade powerUp) {
		int triggerLoc = powerUp.getTriggerLoc();
		//This is for copying from read only to non-readonly
		allUpgrades.add(powerUp);
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
		level++;
	}

}
