package grid;

import entities.Unit;
import gui.GuiTexture;

import java.util.ArrayList;

import librarys.TextureLibrary;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;

// This is the superclass for all tiles contained in a grid

public class Tile {
	
	//the location of this tile in the grid, stores column then row (x then y)
	//the units contained within this tile
	public boolean passable;
	protected int x;
	protected int y;
	protected Vector2f position;
	protected int texture;
	protected GuiTexture guiTexture;
	protected String name;
	private int price;
	protected float size;
	protected Vector2f location;
	protected boolean canInteract;
	
	//creates a tile in location loc, give location in column then row
	public Tile(int x, int y, float size, Vector2f location){
		this.canInteract=false;
		this.location=location;
		this.size=size;
		this.x=x;
		this.y=y;
		this.position=new Vector2f (location.x+((x-size/2)*size*2),(float) (location.y+((y-size/2)*(size*2*DisplayManager.getAspectratio()))));
		name = "Default_Name";
		//Should be overwritten in every case
		price = -1;
	}
	

	public GuiTexture drawTile(){
		return guiTexture;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int newPrice) {
		price = newPrice;
	}
	public Vector2f getLocation(){
		return position;
	}
	
	public void setLocation(Vector2f loc) {
		location = loc;
		this.position=new Vector2f (location.x+((x-size/2)*size*2),(float) (location.y+((y-size/2)*(size*2*DisplayManager.getAspectratio()))));
		guiTexture.setPosition(position);
	}

	public void interact() {
		// TODO Auto-generated method stub
		
	}
	
	public Vector2f getPosition()  {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Tile copy() {
		return new Tile(x, y, size, location);
	}

	public boolean canInteract() {
		
		return canInteract;
	}
	public float getSize(){
		return size;
	}
	




}
