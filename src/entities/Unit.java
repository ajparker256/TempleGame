package entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiRenderer;
import gui.GuiTexture;

//This is the parent object for all entities including Trap enemies such as Zombies and Explorers
public class Unit {
	
	//This is the amount of damage a unit can take before dying.
	private int hp;
	
	//This is a unit's location relative to the screen in pixels from the top left corner.
	protected Vector2f location;
	
	//This is a unit's speed in x and y on the screen.
	private Vector2f velocity;
	
	//This is the size of the unit on the screen in x and y
	private Vector2f size;
	
	//This dictates functions that happen while alive/while dead
	protected boolean isAlive;
	
	
	//Create a Unit that is alive, has hp, an id, and capabilities for moving/rendering
	public Unit(int h, Vector2f loc, Vector2f vel, Vector2f size) {
		hp = h;
		location = loc;
		velocity = vel;
		isAlive = true;
		this.size = size;		
	}
	
	//Move according to the velocity of the unit relative to time passed
	public void move(int milli) {
		setLoc(new Vector2f(getLoc().x+velocity.x*milli/1000, getLoc().y+(velocity.y*milli/1000)));
	}
	
	//This prints the unit at its location, with its design and with its size
	//Optimization strategy: Return the GuiTexture instead and render only one arrayList so as to avoid
	//Any excess memory usage.
	public void render(GuiRenderer g) {
		//STUB TODO either make an overall method, or just keep this for the invocation
		if(isAlive) {
			List<GuiTexture> d = new ArrayList<GuiTexture>();
			g.render(d);
		}
	}
	
	//Use to rescale the Unit
	public void setSize(Vector2f s) {
		size = s;
	}
	
	public void setAlive(boolean b) {
		isAlive = b;
	}
	
	//Returns current health
	public int getHp() {
		return hp;
	}
	
	public void setHp(int h) {
		hp = h;
	}
	
	//Returns current size
	public Vector2f getSize() {
		return size;
	}
	
	//Returns current location
	public Vector2f getLoc() {
		return location;
	}
	
	//Returns current velocity
	public Vector2f getVel() {
		return velocity;
	}
	
	public void setLoc(Vector2f loc) {
		location = loc;
	}
	
}
