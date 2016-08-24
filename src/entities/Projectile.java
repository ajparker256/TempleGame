package entities;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

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

	public Projectile(int direction, int x, int y, int floor, double damage) {
		kill=false;
		this.direction=direction;
		this.x=x;
		this.y=y;
		this.damage = damage;
		this.floor=floor;
		this.texture=GuiLibrary.arrow5;
		this.location=new Vector2f(Main.grids.get(Main.gridToBeRendered).getTile(x,y).getLocation());
		this.size=new Vector2f(0.1f,0.1f);
		this.velocity=new Vector2f(0.5f,0.5f);
		this.image = new GuiTexture(texture, location, size);
		this.image.setRotation(MathM.toDegrees(direction));

	}
	
	public boolean canRender() {
		return Main.gridToBeRendered == floor;
	}
	
	public GuiTexture render(){
			return image;
	}
	public boolean tick(long milli){
	if(kill){
		return false;
	}
		move(milli);
		if(location.equals(Main.grids.get(Main.gridToBeRendered).getTile(x, y).getLocation())){
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
			case 4: this.x=x-1;
			break;
			}
	for(Squad squad:Main.squads){
		for(Group group: squad.getGroups()){
			if(group.getRealLoc().equals(new Point(x,y))){
				group.damage(new boolean[]{true,true,true,true}, damage);
				this.kill = true;
				return false;	//this.kill=true;
			}
		}
	}
			if(x>=Main.grids.get(floor).getWidth()||x<0||y>=Main.grids.get(floor).getWidth()||y<0||(!Main.grids.get(Main.gridToBeRendered).getTile(x, y).isPassable())){
				this.kill = true;
				return false; 	//this.kill=true;
			}
		}
		return true;
	}
	public void move(long milli){
		Vector2f destination=Main.grids.get(Main.gridToBeRendered).getTile(x, y).getLocation();
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

	private Vector2f getLoc() {
		return this.location;
	}
	private void setLoc(Vector2f location) {
		this.location=location;
		this.image.setPosition(location);
	}
	public int getFloor(){
		return this.floor;
	}
	public boolean isKill() {
		return kill;
	}
}
