package grid;

import org.lwjgl.util.vector.Vector2f;

public class DartTrap {
	
	//This is the damage of the darts
	private int damage;
	
	//This is the location IN THE GRID
	private Vector2f location;
	
	//This is the direction and speed of the projectile
	private Vector2f attackVelocity;
	
	//This is the reload timer
	private int reloadTime;
	
	private int range;
	
	//AttackVelocity is often just to determine which wall the darts were placed on
	public DartTrap(Vector2f location, Vector2f attackVelocity) {
		damage = 10;
		this.location = location;
		this.attackVelocity = attackVelocity;
		reloadTime = 10000; //milliseconds or 10 seconds
		range = 3; //Tiles
	}
	
	public void trigger() {
		//TODO create pew pew animation. Then after the animation would hit, deal the damage
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
