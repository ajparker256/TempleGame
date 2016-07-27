package traps;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Blank;
import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import pathing.Group;
import renderEngine.DisplayManager;
import upgrades.Upgrade;

public class TreasureTrap extends Trap{
	
	private int reward;
	
	private int round;
	
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
		super(x, y, size, Main.grid.getLoc());
		id = 4;
		round = Main.round;
		this.floor = floor;
		Main.grids.get(floor).getTreasureLocs().add(new Point(x,y));
		super.passable=false;
		super.canInteract=true;
		this.reward=100;
		this.guiTexture=(new GuiTexture(GuiLibrary.treasureClosed,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Treasure";
		setPrice(200);
	}
	
	@Override
	public Tile copy() {
		TreasureTrap t = new TreasureTrap(x, y, size, floor);
		t.setTrapRefs(trapRefs);
		return t;
	}
	
	@Override
	public void interact(Group g) {
		for(Upgrade u : onInteract) {
			u.upgrade(this);
		}
		if(guiTexture.getTexture() == GuiLibrary.treasureClosed) {
			guiTexture = new GuiTexture(GuiLibrary.treasureOpen, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio()))) ;
		}
		
		if(reward<=0) {
			passable = true;
			canInteract = false;
			Main.grids.get(g.getFloor()).getTreasureLocs().remove(new Point(x, y));
			Blank blank = new Blank(super.x, super.y, super.size, Main.grid.getLoc());
			blank.setTrapRefs(trapRefs);
			Main.grids.get(g.getFloor()).setTile(super.x, super.y, blank);
		} else {
			reward --;
		}
	}
	
	public void getIncome() {
		Main.money += reward;
	}
	
	public void restore() {
		guiTexture = new GuiTexture(GuiLibrary.treasureClosed, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		reward = 100;
		Main.grids.get(floor).setTile(x, y, this);
	}
}
