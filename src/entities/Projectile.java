package entities;

import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.TextureLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class Projectile {

	private int floor;
	private int y;
	private int x;
	private int direction;
	private int texture;
	private Vector2f location;
	private Vector2f size;

	public Projectile(int direction, int x, int y, int floor) {
		this.direction=direction;
		this.x=x;
		this.y=y;
		this.floor=floor;
		this.texture=GuiLibrary.arrow1;
		this.location=new Vector2f(Main.grid.getTile(x,y).getLocation());
		this.size=new Vector2f(1f,1f);
	}
	public GuiTexture render(){
		return new GuiTexture(texture,location,size);
	}
	public void tick(){
		Vector2f destination=grid.getTile(xInGrid, yInGrid).getLocation();
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
					velocity.y=0;
				}
			}
		}
		
	}
	private void setLoc(Vector2f location) {
		this.location=location;
		
	}
	public int getFloor(){
		return this.floor;
	}

}
