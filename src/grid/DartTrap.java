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
	
	//AttackVelocity is often just to determine which wall the darts were placed on
	public DartTrap(Vector2f location, Vector2f attackVelocity) {
		damage = 10;
		this.location = location;
		this.attackVelocity = attackVelocity;
		reloadTime = 10000; //milliseconds or 10 seconds
	}
	
	public void trigger() {
		//TODO create pew pew animation. Then after the animation would hit, deal the damage
	}
	
}
