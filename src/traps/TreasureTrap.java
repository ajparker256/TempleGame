package traps;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Blank;
import grid.Grid;
import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import pathing.Group;
import renderEngine.DisplayManager;
import upgrades.Upgrade;

public class TreasureTrap extends Trap implements TrapInterface{
	
	private int reward;
	
	//Inherited traits that can be set/modified.
/*	protected int damage;
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
	protected ArrayList<Upgrade> onTrigger;*/
	
	public TreasureTrap(int x, int y, float size, int floor) {
		super(x, y, size, floor);
		id = 4;
		this.floor = floor;
		super.passable=false;
		super.canInteract=true;
		this.reward=100;
		this.guiTexture=(new GuiTexture(GuiLibrary.treasureClosed,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Treasure";
	}
	
	@Override
	public Tile copy() {
		TreasureTrap t = new TreasureTrap(x, y, size, floor);
		t.setTrapRefs(trapRefs);
		return t;
	}
	
	@Override
	public void interact(Group g, Grid currentFloor) {
		for(Upgrade u : onInteract) {
			u.upgrade(this);
		}
		if(guiTexture.getTexture() == GuiLibrary.treasureClosed) {
			guiTexture = new GuiTexture(GuiLibrary.treasureOpen, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio()))) ;
		}
		
		if(isDead()) {
			//To be implemented correctly elsewhere : Main.grids.get(g.getFloor()).getTreasureLocs().remove(new Point(x, y));
			replaceWithBlank(currentFloor);
		} else {
			hp--;
		}
	}
	
	public void getIncome() {
		Main.money += reward;
	}

	@Override
	public ArrayList<Point> getTriggerLocations() {
		return null;
	}
}
