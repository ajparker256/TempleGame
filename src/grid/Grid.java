package grid;

import gui.GuiTexture;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	
	
	//This is the size of the grid in pixels.
	
	//This is the location in relation to the page spawned.
	private Vector2f location;
	
	//This is the 2d array that contains all of the locations in the grid represented by tiles
	private Tile[][] grid;
	
	//makes a grid of boolean values with length r and height r along with a raw size of s at location l.
	public Grid(Vector2f location, float size, int rows) {
		this.location = location;
		grid = new Tile[rows][rows];
		for(int i=0;i<rows;i++){
			for(int k=0;k<rows;k++){
				grid[k][i]=new Tile(k,i,size,location);
			}
		}
	}
	
	//Changes the value of a piece of the array.

	

	
	//Returns the location
	public Vector2f getLoc() {
		return location;
	}
	

	

	
	public ArrayList<GuiTexture> render() {
		ArrayList<GuiTexture> toRender= new ArrayList<GuiTexture>();
		for(Tile[]line:grid){
			for(Tile tile:line){
				toRender.add(tile.drawTile());
			}
		}
		return toRender;
	}
	
	//This is the A* Value system. Distance between start and point vs end and point.

	

	
	
	
	
	
	
	
}