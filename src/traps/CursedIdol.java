package traps;

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

public class CursedIdol extends Trap{

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
		super(x, y, size, Main.grids.get(floor).getLoc(), floor);
		super.passable=false;
		super.canInteract=true;
		this.value = 100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.idolOnBlank,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Idol!";
		this.setPrice(100);
		id = 2;
	}
	
	//TODO make this into its functional self, currently money is added to player but SHOULD be going towards the explorers
	//ALSO the curse is not implemented, needs some thought there too.
	@Override
	public void interact(Group g){
		value--;
		if(value%5 == 0)
			Main.money++;
		if(value<=0){
			Blank blank = new Blank(super.x, super.y, super.size, Main.grids.get(floor).getLoc(), floor);
			blank.setTrapRefs(trapRefs);
			Main.grids.get(g.getFloor()).setTile(super.x, super.y, blank);		
		}
	}
}
