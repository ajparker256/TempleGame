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
	private Vector2f location;
	
	//This is a unit's speed in x and y on the screen.
	private Vector2f velocity;
	
	//This is the size of the unit on the screen in x and y
	private Vector2f size;
	
	//This is a number used to identify what type of unit it is on the grid.
	private int id;
	
	//This dictates functions that happen while alive/while dead
	protected boolean isAlive;
	
	//This is the image identity for the unit
	protected int design;
	
	
	//Create a Unit that is alive, has hp, an id, and capabilities for moving/rendering
	public Unit(int h, Vector2f loc, Vector2f vel, Vector2f size, int i, int texture) {
		hp = h;
		location = loc;
		velocity = vel;
		id = i;
		isAlive = true;
		design = texture;
		this.size = size;		
	}
	
	//Move according to the velocity of the unit relative to time passed
	public void move(int milli) {
		location = new Vector2f(location.x+(velocity.x*milli/1000), location.y+(velocity.y*milli/1000));
	}
	
	//This prints the unit at its location, with its design and with its size
	//Optimization strategy: Return the GuiTexture instead and render only one arrayList so as to avoid
	//Any excess memory usage.
	public void render(GuiRenderer g) {
		if(isAlive) {
			List<GuiTexture> d = new ArrayList<GuiTexture>();
			d.add(new GuiTexture(design, location, size));
			g.render(d);
		}
	}
	
	//Use to rescale the Unit
	public void setSize(Vector2f s) {
		size = s;
	}
	
	//Returns current health
	public int getHp() {
		return hp;
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
	
	//Returns current id number
	public int getId(){
		return id;
	}
}
