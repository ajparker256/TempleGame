package entities;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import pathing.Group;
import pathing.Squad;
import renderEngine.DisplayManager;
import tools.MathM;

public class Projectile {

	protected int floor;
	protected int y;
	protected int x;
	protected int direction;
	protected int texture;
	protected Vector2f location;
	protected Vector2f size;
	protected Vector2f velocity;
	protected boolean kill;
	protected GuiTexture image;
	protected double damage;
	protected Vector2f destination;

	public Projectile(int direction, Point coordinates, int floor, double damage) {
		kill=false;
		this.direction=direction;
		this.x=coordinates.x;
		this.y=coordinates.y;
		this.damage = damage;
		this.floor=floor;
		this.texture=GuiLibrary.arrow5;
		this.size=new Vector2f(0.1f,0.1f);
		this.velocity=new Vector2f(0.5f,0.5f);
		this.image = new GuiTexture(texture, location, size);
		this.image.setRotation(MathM.toDegrees(direction));

	}
	
	public int getFloor(){
		return floor;
	}
	
	public boolean canRender(int currentFloor) {
		return currentFloor == floor;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis){
		System.out.println(location);
		dynamicGuis.add(image);
	}
	
	protected void checkLoc(Grid gridFloor) {
		if(location == null || location.x == -2) {
			location = gridFloor.getTile(x, y).getLocation();
			image.setPosition(location);
		}
		destination=gridFloor.getTile(x, y).getLocation();
	}
	
	public boolean tick(long milli, ArrayList<Squad> squads, Grid gridFloor){
		checkLoc(gridFloor);
		if(kill){
			return false;
		}
		if(location.equals(gridFloor.getTile(x, y).getLocation())){
			switch(direction){
			case 1:
				this.y=y+1;
			break;
			case 2:
				this.x=x+1;
			break;
			case 3:
				this.y=y-1;
			break;
			case 4: 
				this.x=x-1;
			break;
		}
		move(milli);
	
	for(Squad squad: squads){
		for(Group group: squad.getGroups()){
			if(group.getRealLoc().equals(new Point(x,y))){
				group.damage(new boolean[]{true,true,true,true}, damage);
				this.kill = true;
				return false;	//this.kill=true;
			}
		}
	}
			if(x>=gridFloor.getWidth()||x<0||y>=gridFloor.getWidth()||y<0||(!gridFloor.getTile(x, y).isPassable())){
				this.kill = true;
				return false; 	//this.kill=true;
			}
		}
		return true;
	}
	public void move(long milli){
		
		Vector2f tempVelocity= new Vector2f(0, 0);
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
				}
			}
		}
		
	}
	
	public void checkForCollisions(Squad currentSquad) {
		for(Group currentGroup : currentSquad.getGroups()) {
			if(currentGroup.getRealLoc().equals(new Point(x, y))) {
				currentGroup.damage(new boolean[]{true,true,true,true}, damage);
			}
		}
	}

	private Vector2f getLoc() {
		return this.location;
	}
	private void setLoc(Vector2f location) {
		this.location=location;
		this.image.setPosition(location);
	}
	
	public boolean isKill() {
		return kill;
	}
}
