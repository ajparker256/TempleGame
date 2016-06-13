package entities;

import org.lwjgl.util.vector.Vector2f;

//This is the parent object for all entities including Trap enemies such as Zombies and Explorers
public class Unit {
	
	//This is the amount of damage a unit can take before dying.
	private int hp;
	
	//This is a unit's location relative to the screen in pixels from the top left corner.
	private Vector2f location;
	
	//This is a unit's speed in x and y on the screen.
	private Vector2f velocity;
	
	//This is a number used to identify what type of unit it is on the grid.
	private int id;
	
	public Unit(int h, Vector2f loc, Vector2f vel, int i) {
		hp = h;
		location = loc;
		velocity = vel;
		id = i;
	}
	
	public void move(int milli) {
		location = new Vector2f(location.x+(velocity.x*milli/1000), location.y+(velocity.y*milli/1000));
	}
	
	public void render() {
		//TODO AJ help... explain gui
	}
	
	public int getHp() {
		return hp;
	}
	
	public Vector2f getLoc() {
		return location;
	}
	
	public Vector2f getVel() {
		return velocity;
	}
	
	public int getId(){
		return id;
	}
}
