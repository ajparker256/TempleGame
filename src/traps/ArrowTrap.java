package traps;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import grid.Grid;
import grid.Tile;
import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;
import tools.MathM;
import upgrades.Upgrade;

public class ArrowTrap extends Trap implements TrapInterface{
	
	//protected int level; level is handled by the size of the upgrade arraylist
	protected int direction;
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
	

	public ArrowTrap(int x, int y, float size,int direction, int floor) {
		super(x, y, size, floor);
		super.passable=false;
		super.canInteract=true;
		this.direction=direction;
		System.out.println(direction);
		this.guiTexture=(new GuiTexture(GuiLibrary.arrowTrap1,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.guiTexture.setRotation(MathM.toDegrees(direction));
		this.name = "Arrow Trap";
		this.maxCd=1000;
		id = 5;
		rotatable = true;
		super.animation = new Animation(AnimationLibrary.crossBowFiring, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio())));
		super.animation.setDelay(25);
 
		
		damage = 5;
		range = 4;
		defense = 0;
		maxHp = 100;
		hp = 100;
		critChance = 0;
		armorPen = 0;
		bleed = 0;
		pierceChance = 0;
		warmUpTime = 0;
		accuracy = .9;
		cooldown = 0;
		maxCd = 1000;
		
	//	setTriggers(); TODO deal with this ... later
	}
	@Override
	public void whenTriggered(Point targetPoint){
		for(Upgrade u : onTrigger) {
			u.upgrade(this);
		}
		if(cooldown<=0){
			boolean fire = false;
			//UtargetPoint
			if(direction == 1 && targetPoint.y-y<range) {
				fire = true;
				for(int i = y+1; i<targetPoint.y; i++) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
				//Right
			} else if(direction == 2 && targetPoint.x-x<range) {
				fire = true;
				for(int i = x+1; i<targetPoint.x; i++) {
					if(Main.grids.get(floor).getTile(i, y).getId() != 0) {
						fire = false;
						break;
					}
				}
				//Down
			} else if(direction == 3 && y-targetPoint.y <range) {
				fire = true;
				for(int i = y-1; i>targetPoint.y; i--) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
				//Left
			} else if(direction == 4 && x-targetPoint.x<range) {
				fire = true;
				for(int i = x-1; i>targetPoint.x; i--) {
					if(Main.grids.get(floor).getTile(i, y).getId() != 0) {
						fire = false;
						break;
					}
				}
			} 
			if(fire) {
				for(Upgrade u : onFire) {
					u.upgrade(this);
				}
				double temtargetPointDamage = getRealDamage();
				Main.projectiles.add(new Projectile(direction,x,y,super.floor, temtargetPointDamage));
				isFiring = true;
				cooldown=maxCd; 
			}
		}
	}
	
	@Override
	public Tile copy() {
		ArrowTrap copiedArrowTrap = new ArrowTrap(x, y, size, direction, floor);
		copiedArrowTrap.setTrapRefs(trapRefs);
		for(Upgrade u: allUpgrades) {
			copiedArrowTrap.upgrade(u.getId());
		}
		return copiedArrowTrap;
	}
	

 	public ArrayList<Point> getTriggerLocations(){
		int i=1;
		ArrayList<Point> allTriggers = new ArrayList<Point>();
		switch(direction){
			case 1:while(y+i < getWidthOfGrid() && i <= range){
				allTriggers.add(new Point(x, y+i));
				i++;
			}
			break;
			case 2:while(x+i < getWidthOfGrid() && i <= range){
				allTriggers.add(new Point(x+i, y));
				i++;
			}
			break;
			case 3:while(y-i >= 0 && i <= range){
				allTriggers.add(new Point(x, y-i));
				i++;
			}
			break;
			case 4:while(x-i >= 0 && i <= range){
				allTriggers.add(new Point(x-i, y));
				i++;
			}
			break;
		}
		
		return allTriggers;
	}
 	
	@Override
	public void tick(double milli){
		cooldown-=milli;
	}
}