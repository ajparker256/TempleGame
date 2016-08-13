package traps;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import grid.Tile;
import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;
import tools.MathM;
import upgrades.Upgrade;

public class TikiTrap extends Trap implements TrapInterface{
	
	//protected int level; level is controlled by arraylist.size() of centralized upgrades arraylist
	protected Animation firing;
	protected boolean isFiring;
	
	
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
	
	public TikiTrap(int x, int y, float size, int floor) {
		super(x, y, size, Main.grids.get(floor).getLoc(), floor);
		super.passable=false;
		super.canInteract=true;
		this.guiTexture=(new GuiTexture(GuiLibrary.tikiTrap,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Tiki Trap";
		this.maxCd=100;
		id = 9;
		range = 1;
		rotatable = false;
		setTriggers();
		
		damage = 1;
		range = 1;
		defense = 10;
		maxHp = 200;
		critChance = 0;
		armorPen = 10;
		pierceChance = 0;
		bleed = 0;
		warmUpTime = 0;
		accuracy = .9;
		cooldown = 0;
		maxCd = 100;
	}
	
	@Override
	public void whenTriggered(Point p){
		for(Upgrade u : onTrigger) {
			u.upgrade(this);
		}
		if(cooldown<=0){
			boolean fire = true;
			//Up
			double tempDamage = getRealDamage();
		
			if(fire) {

				if(x-1>=0)
				Main.projectiles.add(new Fire(1,x-1,y,super.floor, tempDamage));
				if(x+1<=Main.grids.get(floor).getWidth()-1)
				Main.projectiles.add(new Fire(1,x+1,y,super.floor, tempDamage));
				if(y-1>=0)
				Main.projectiles.add(new Fire(1,x,y-1,super.floor, tempDamage));
				if(y+1<=Main.grids.get(floor).getWidth()-1)
				Main.projectiles.add(new Fire(1,x,y+1,super.floor, tempDamage));
				
				isFiring = true;
				for(Upgrade u : onFire) {
					u.upgrade(this);
				}
				cooldown=maxCd; 
			}
		}
	}
	
	@Override
	public GuiTexture drawTile() {
		return guiTexture;
	}
	
	public void setTriggers(){
		

		for(int j=1;j<=4;j++){
			int i=1;
		switch(j){
		case 1:while(y+i<Main.grids.get(floor).getWidth()&&i<=range){
			Main.gridsReadOnly.get(floor).getTile(x, y+i).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 2:while(x+i<Main.grids.get(floor).getWidth()&&i<=range){
			Main.gridsReadOnly.get(floor).getTile(x+i, y).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 3:while(y-i>=0&&i<=range){
			Main.gridsReadOnly.get(floor).getTile(x, y-i).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 4:while(x-i>=0&&i<=range){
			Main.gridsReadOnly.get(floor).getTile(x-i, y).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		}
		}	
	}
	
	@Override
	public Tile copy() {
		Tile copiedTiki = new TikiTrap(x, y, size, floor);
		copiedTiki.setTrapRefs(trapRefs);
		for(Upgrade u: allUpgrades) {
			copiedTiki.upgrade(u.getId());
		}
		return copiedTiki;
	}
	
	@Override
	public void tick(double milli){
		cooldown-=milli;
	}
}