package grid;

import org.lwjgl.util.vector.Vector2f;

import gui.Animation;
import gui.GuiTexture;
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
		
		arrows = new Animation(position);
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows1"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows2"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows3"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows4"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows5"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows6"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows7"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows8"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows9"), attackPosition, new Vector2f(size, size)));
		arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows10"), attackPosition, new Vector2f(size, size)));
		arrows.setDelay(10);
	}
	
	public void trigger() {
		//TODO create pew pew animation. Then after the animation would hit, deal the damage
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
