package traps;

import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.GuiLibrary;
import renderEngine.Loader;

public class DartTrap extends Tile{
	
	//This is the damage of the darts
	private int damage;
	
	//This is the location IN THE GRID
	private Vector2f location;
	
	//This is where the attack renders
	private Vector2f attackPosition;
	
	//This is the direction and speed of the projectile
	private Vector2f attackVelocity;
	
	//This is the reload timer
	private int reloadTime;
	
	private int range;
	
	private Animation arrows;
	
	private float size;
	
	//AttackVelocity is often just to determine which wall the darts were placed on
	public DartTrap(Vector2f location, float size, Vector2f locationRelativeToScreen, Vector2f attackVelocity, Loader loader){
		super((int)location.x, (int)location.y, size, locationRelativeToScreen);
		damage = 10;
		this.location = location;
		this.attackVelocity = attackVelocity;
		this.size = size;
		reloadTime = 10000; //milliseconds or 10 seconds
		range = 3; //Tiles
		attackPosition = location;
		this.guiTexture = new GuiTexture(loader.loadTexture("tiles/Dirt2"), locationRelativeToScreen, new Vector2f(size,size));
		
		//arrows = new Animation(position);
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow1, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow2, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow3, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow4, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow5, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow6, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow7, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow8, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow9, attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(GuiLibrary.arrow10, attackPosition, new Vector2f(size, size)));
		arrows.setDelay(10);
	}
	
	public void trigger(int milli) {
		if(Math.abs(arrows.getLoc().x-location.x)<range && Math.abs(arrows.getLoc().y-location.y)<range) {
			arrows.setLoc(new Vector2f(arrows.getLoc().x+attackVelocity.x*milli/1000, arrows.getLoc().y+attackVelocity.y*milli/1000));
			//TODO add collision mechanic of some sort to do damage.
		}
	}
	
	public Animation getAnimation() {
		return arrows;
	}
	//This location is its position on the grid.
	public boolean isTriggered(Vector2f locationOfUnit) {
		//The velocity of attack is only in one direction. This means that if x is zero then y must have a value
		if(attackVelocity.x!=0) {
			//If the attack is supposed to go to the right, check if enemies are to the right within RANGE tiles
			if(attackVelocity.x>0) {
				if(locationOfUnit.x-location.x<=range) {
					return true;
				}
			}
			//If the object is supposed to be to the left, and is within RANGE tiles then...
			else if(location.x-locationOfUnit.x<=range) {
				return true;
			}
		} else {
			//If the attack is supposed to go up, test if it is within RANGE tiles in that direction
			if(attackVelocity.y>0) {
				if(locationOfUnit.y-location.y<=range) {
					return true;
				}
			} else if(location.y - locationOfUnit.y <= range) {
				return true;
			}
		}
		return false;
	}
	
}
