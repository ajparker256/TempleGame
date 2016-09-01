package entities;

import java.util.ArrayList;
import java.util.List;

import librarys.AnimationLibrary;
import main.Main;
import pathing.Group;
import renderEngine.DisplayManager;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import grid.Tile;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;

//This is the parent object for all entities including Trap enemies such as Zombies and Explorers
public class Unit {
	
	protected static final int FIRST_FLOOR = 0;
	
	protected double hp;
	
	protected Vector2f location;
	
	protected Vector2f velocity;
	
	protected Vector2f size;
	
	protected boolean isAlive;
	protected int xInGrid;
	protected int yInGrid;
	protected Animation animation;

	protected GuiTexture idle;

	protected Vector2f tempVelocity;
	
	protected boolean kill;

	public Unit(Group group) {
		this.hp=20;
		this.kill=false;
		tempVelocity=new Vector2f(0, 0);
		location = new Vector2f(-2,-2);
		this.animation=new Animation(AnimationLibrary.explorerRunning,location,size);
		velocity=new Vector2f(0.1f,(float) (0.1f*DisplayManager.getAspectratio()));
		location = group.getLocation();
		isAlive = true;
		this.size = Grid.getUnitSize();	
	}
	
	//Move according to the velocity of the unit relative to time passed
	public void move(int milli,Grid grid) {
		Vector2f destination=grid.getTile(xInGrid, yInGrid).getLocation();
		 tempVelocity= new Vector2f(0, 0);
		if(!location.equals(destination)){
			if(location.x<destination.x){
				tempVelocity.x = velocity.x;
				setLoc(new Vector2f(getLoc().x+tempVelocity.x*milli/1000f, getLoc().y));
				if(location.x>destination.x){
					location.x=destination.x;
				}
			}else if(location.x>destination.x){
				tempVelocity.x = velocity.x*-1;
				setLoc(new Vector2f(getLoc().x+tempVelocity.x*milli/1000f, getLoc().y));
				if(location.x<destination.x){
					location.x=destination.x;
				}
				
			}else if(location.y<destination.y){
				tempVelocity.y = velocity.y;
				setLoc(new Vector2f(getLoc().x, getLoc().y+(tempVelocity.y*milli/1000f)));
				if(location.y>destination.y){
					location.y=destination.y;
				}
				
			}else if(location.y>destination.y){
			
				tempVelocity.y = -1*velocity.y;
				setLoc(new Vector2f(getLoc().x, getLoc().y+(tempVelocity.y*milli/1000f)));
				if(location.y<destination.y){
					location.y=destination.y;
					velocity.y=0;
				}
			}
		}
		
	}
	
	public boolean isKill() {
		return kill;
	}

	//This prints the unit at its location, with its design and with its size
	//Optimization strategy: Return the GuiTexture instead and render only one arrayList so as to avoid
	//Any excess memory usage.
	public GuiTexture render(Vector2f location) {
		animation.setLoc(location);
		return animation.getFrame();
	}
	
	public Vector2f getLocInGrid(Grid gr) {
		Vector2f locInGrid = new Vector2f((int)(location.x-gr.getLoc().x)/(int)gr.getTileCount().x, (int)(location.y - gr.getLoc().y)/(int)gr.getTileCount().y);
		return locInGrid;
	}
	
	//Use to rescale the Unit
	public void setSize(Vector2f s) {
		size = s;
	}
	
	public void setAlive(boolean b) {
		isAlive = b;
	}
	
	//Returns current health
	public double getHp() {
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
