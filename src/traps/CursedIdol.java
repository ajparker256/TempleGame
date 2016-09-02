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
import pathing.TrapAffinityPM;
import renderEngine.DisplayManager;
import upgrades.Upgrade;

public class CursedIdol extends Trap implements TrapInterface{

	//This is the upgrading power given when picked up by the explorers
	private int value;
	
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
	
	
	public CursedIdol(int x, int y, float size, int floor) {
		super(x, y, size, floor);
		super.passable=false;
		super.canInteract=true;
		this.value = 100;
		this.texture=1;
		this.hp = 200;
		this.guiTexture=(new GuiTexture(GuiLibrary.idolOnBlank,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Idol!";
		id = 2;
	}
	
	@Override
	public void interact(Group g, Grid currentFloor){
		
		if(isDead()){
			replaceWithBlank(currentFloor);
			TrapAffinityPM attractedToDanger = new TrapAffinityPM(Main.squads.get(g.getSquadId()));
			attractedToDanger.addModifier(Main.squads.get(g.getSquadId()).getPathMods());
			for(Upgrade u : onDeath) {
				u.upgrade(this);
			}
		} else {
			hp--;
		}
	}
	
	@Override
	public Tile copy() {
		CursedIdol newCursedIdol = new CursedIdol(x, y, size, floor);
		newCursedIdol.setTrapRefs(trapRefs);
		for(Upgrade u : allUpgrades) {
			newCursedIdol.upgrade(u.getId());
		}
		return newCursedIdol;
	}

	@Override
	public ArrayList<Point> getTriggerLocations() {
		return null;
	}
}
