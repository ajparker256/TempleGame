package grid;

import gui.GuiTexture;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import entities.Group;
import renderEngine.DisplayManager;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	
	
	public static final Vector2f UNITSIZE = new Vector2f(0.2f,0.2f);

	//This is the boolean that determines if groups redirect to the path of the group that made it to the end
	private boolean goalReached;
	
	private Group goalReacher;
	
	//This is the location in relation to the page spawned.
	private Vector2f location;
	
	//This is the 2d array that contains all of the locations in the grid represented by tiles
	private Tile[][] grid;
	
	//makes a grid of boolean values with length r and height r along with a raw size of s at location l.
	public Grid(Vector2f location, float size, int rows) {
		this.location = location;
		
		goalReached = false;
		
		goalReacher = null;
		
		grid = new Tile[rows][rows];
		for(int i=0;i<rows;i++){
			for(int k=0;k<rows;k++){
				grid[k][i]=new Dirt(k,i,size,location);
			}
		}
	}
	
	public Vector2f getTileCount() {
		return new Vector2f(grid[0].length, grid.length);
	}

	public boolean getGoalReached() {
		return goalReached;
	}
	
	public Group getGoalReacher() {
		return goalReacher;
	}

	
	//Returns the location
	public Vector2f getLoc() {
		return location;
	}
	public Tile getTile(int x,int y){
		return grid[x][y];
	}
	public void setTile(int x, int y,Tile tile){
		grid[x][y]=tile;
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
	
	public Tile[] getAdjacent(Vector2f locationInGrid) {
		//A collection of the adjacent tiles, with null if there is no tile there.
		//0 = Left, 1 = Right, 2 = Down, 3 = Up
		Tile[] adjacents = new Tile[4];
		//If not on the left border, get the tile to the left
		if(locationInGrid.x != 0) {
			adjacents[0] = getTile((int)location.x-1, (int)location.y);
		}
		
		//If not on the right border, get the tile to the right
		if(locationInGrid.x != grid[0].length - 1) {
			adjacents[1] = getTile((int)location.x+1, (int)location.y);
		}
		
		//If not on the bottom border of the grid, get the downwards tile
		if(locationInGrid.y != 0) {
			adjacents[2] = getTile((int)location.x, (int)location.y-1);
		}
		
		//If not on the top border, get the tile upwards of it
		if(locationInGrid.y != grid.length-1) {
			adjacents[3] = getTile((int)location.x, (int)location.y+1);
		}
		
		return adjacents;
	}
	

	

	
	
}