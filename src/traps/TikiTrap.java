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

public class TikiTrap extends Trap{
	
	//protected int level; level is controlled by arraylist.size() of centralized upgrades arraylist
	protected int direction;
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
	
	public TikiTrap(int x, int y, float size) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.cooldown=0;
		this.direction = 1;
		this.guiTexture=(new GuiTexture(GuiLibrary.tikiTrap,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Tiki Trap";
		this.maxCd=100000000;
		id = 9;
		range = 1;
		rotatable = false;
		setTriggers();
	}
	
	@Override
	public void whenTriggered(Point p){
		for(Upgrade u : onTrigger) {
			u.upgrade(this);
		}
		if(cooldown<=0){
			boolean fire = false;
			//Up
			if(direction == 1 && p.y-y<range) {
				fire = true;
				for(int i = y+1; i<p.y; i++) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
			} else if(direction == 2 && p.x-x<range) {
				fire = true;
				for(int i = x+1; i<p.x; i++) {
					if(Main.grids.get(floor).getTile(i, y).getId() != 0) {
						fire = false;
						break;
					}
				}
			} else if(direction == 3 && y-p.y <range) {
				fire = true;
				for(int i = y-1; i>p.y; i--) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
			} else if(direction == 4 && x-p.x<range) {
				fire = true;
				for(int i = x-1; i>p.x; i--) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
			} 
			if(fire) {
				Main.projectiles.add(new Projectile(direction,x,y,super.floor));
				isFiring = true;
				for(Upgrade u : onFire) {
					u.upgrade(this);
				}
				cooldown=maxCd; 
			}
		}
	}
	
	public void trigger(){
	
	}
	
	@Override
	public GuiTexture drawTile() {
		if(isFiring) {
			GuiTexture temp = firing.getFrameNoLoop();
			if(temp == null) {
				return guiTexture;
			} else {
				isFiring = false;
				return temp;
			}
		} 
		return guiTexture;
	}
	
	private void setTriggers(){
		
		int i=1;
		switch(direction){
		case 1:while(y+i<=9){
			Main.grids.get(floor).getTile(x, y+i).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 2:while(x+i<=9){
			Main.grids.get(floor).getTile(x+i, y).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 3:while(y-i>=0){
			Main.grids.get(floor).getTile(x, y-i).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 4:while(x-i>=0){
			Main.grids.get(floor).getTile(x-i, y).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
	}
		
	}
	@Override
	public void tick(long milli){
		cooldown-=milli;
	}
}