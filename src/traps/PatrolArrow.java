package traps;

import java.awt.Point;
import java.util.ArrayList;

import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;
import tools.MathM;
import upgrades.Upgrade;
import entities.Projectile;
import grid.Tile;
import gui.Animation;
import gui.GuiTexture;
public class PatrolArrow extends Trap implements TrapInterface{
public PatrolArrow(int x, int y, float size, int floor) {
	super(x, y, size, floor);
	super.passable=false; 
	super.canInteract=true;
	this.guiTexture=(new GuiTexture(GuiLibrary.arrowTrap1,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
	this.name = "Arrow Trap";
	this.maxCd=1000;
	id = 5;
	rotatable = true;
	super.animation = new Animation(AnimationLibrary.crossBowFiring, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio())));
	super.animation.setDelay(25);
	//setTriggers();
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
	maxCd = 1;
}
@Override
public void whenTriggered(Point p){
	for(Upgrade u : onTrigger) {
		u.upgrade(this);
	}

}

public void trigger(){

}

@Override
public Tile copy() {
	Tile copiedArrowTrap = new PatrolArrow(x, y, size, floor);
	copiedArrowTrap.setTrapRefs(trapRefs);
	for(Upgrade u: allUpgrades) {
		copiedArrowTrap.upgrade(u.getId());
	}
	return copiedArrowTrap;
}


	public ArrayList<Point> getTriggerLocations(){
		ArrayList<Point> allTriggers = new ArrayList<Point>();
		for(int i=range*-1;i<=range;i++){
			for(int k=range*-1;k<=range;k++){
				allTriggers.add(new Point(k+i, y+k));
			}
		}
		return allTriggers;


}
	

@Override
public void tick(double milli){
	cooldown-=milli;
}
}
