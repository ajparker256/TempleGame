package grid;

import gui.GuiTexture;
import librarys.StringLibrary;
import main.Main;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import buttons.Button;
import entities.Group;
import renderEngine.DisplayManager;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	
	private boolean isOn;
	
	private Button levelSelect;
	
	
	public static final Vector2f UNITSIZE = new Vector2f(0.2f,0.2f);

	//This is the boolean that determines if groups redirect to the path of the group that made it to the end
	private boolean goalReached;
	
	private Group goalReacher;
	
	//This is the location in relation to the page spawned.
	private Vector2f location;
	
	//This is the 2d array that contains all of the locations in the grid represented by tiles
	private Tile[][] grid;
	
	private Button gridClicked;
	
	private float size;
	
	private int floor;
	
	//makes a grid of boolean values with length r and height r along with a raw size of s at location l.
	public Grid(Vector2f location, float size, int rows, int floor) {
		this.location = location;
		
		isOn = false;
		
		float f = StringLibrary.size.x;
		
		levelSelect = new Button(new Vector2f(location.x+floor*StringLibrary.size.x*2-StringLibrary.getWidth(""+floor), 1),
					new Vector2f(location.x+floor*StringLibrary.size.x*2, 1-StringLibrary.size.y));
		
		goalReached = false;
		
		goalReacher = null;
		
		gridClicked = new Button(new Vector2f(location.x-size, location.y+3*size*(1.2f+rows)), new Vector2f(location.x-size+2*size*rows-.005f, location.y-2*size));
		
		this.size = size;
		
		this.floor = floor;
		
		grid = new Tile[rows][rows];
		for(int i=0;i<rows;i++){
			for(int k=0;k<rows;k++){
				grid[k][i]=new Dirt(k,i,size,location);
			}
		}
		grid[0][0]=new Blank(0,0,size,location);
	}
	
	public void renderFloorSelect() {
		StringLibrary.makeItFit(floor+" ", new Vector2f(location.x+floor*StringLibrary.getSize().x*2-StringLibrary.getWidth(""+floor), 1-StringLibrary.getSize().y), 2000);
	}
	
	public int getFloor() {
		return floor;
	}
	
	public boolean isLevelSelected(float mouseX, float mouseY) {
		return levelSelect.isClicked(mouseX, mouseY);
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
	
	public Button getGridButton() {
		return gridClicked;
	}
	
	public float getSize() {
		return size;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public void setIsOn(boolean b) {
		isOn = b;
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
		if(isOn){
			for(Tile[]line:grid){
				for(Tile tile:line){
					//UNCOMMENT BELOW FOR TRIPPY THINGS XD
					//if(tile.isOccupied() != -1)
					toRender.add(tile.drawTile());
				}
			}
		
		}
		return toRender;
		
	}
	
	public Tile[] getAdjacent(Vector2f locationInGrid/*, int id*/) {
		int absenceCounter = 0;
		//A collection of the adjacent tiles, with null if there is no tile there.
		//0 = Left, 1 = Right, 2 = Down, 3 = Up
		Tile[] adjacents = new Tile[4];
		//If not on the left border, get the tile to the left
		if((int)locationInGrid.x != 0) {
			Tile tile = getTile((int)locationInGrid.x-1, (int)locationInGrid.y);
			if(tile.isOccupied()==-1  /*|| tile.isOccupied()==id*/)
				adjacents[0] = tile;
			else{
				absenceCounter++;
			}
		} else {
			absenceCounter++;
		}
		
		//If not on the right border, get the tile to the right
		if((int)locationInGrid.x != grid[0].length - 1) {
			Tile tile = getTile((int)locationInGrid.x+1, (int)locationInGrid.y);
			if(tile.isOccupied()==-1 /*|| tile.isOccupied()==id*/)
				adjacents[1] = tile;
			else
				absenceCounter++;
		} else absenceCounter++;
		
		//If not on the bottom border of the grid, get the downwards tile
		if((int)locationInGrid.y != 0) {
			Tile tile = getTile((int)locationInGrid.x, (int)locationInGrid.y-1);
			if(tile.isOccupied() == -1  /*|| tile.isOccupied()==id*/)
				adjacents[2] = tile;
			else absenceCounter++;
		} else absenceCounter++;
		
		//If not on the top border, get the tile upwards of it
		if((int)locationInGrid.y != grid.length-1) {
			Tile tile = getTile((int)locationInGrid.x, (int)locationInGrid.y+1);
			if(tile.isOccupied() == -1  /*|| tile.isOccupied()==id*/)
				adjacents[3] = tile;
			else absenceCounter++;
		} else absenceCounter++;
		
		//If there is no where to go, stay where you are.
		if(absenceCounter == 4) {
			adjacents[0] = getTile((int)locationInGrid.x, (int)locationInGrid.y);
		}
		return adjacents;
	}
	

	

	
	
}