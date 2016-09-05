package grid;

import gui.GuiTexture;
import librarys.StringLibrary;
import pathing.Group;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import buttons.Button;
import renderEngine.DisplayManager;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	
	private ArrayList<Point> treasureLocs;
	
	private ArrayList<Point> trapLocs;
	
	private boolean isOn;
	
	private Button levelSelect;
	
	private static final int MINIMUM_WIDTH = 5;
	private static final int MAXIMUM_WIDTH = 9;
	private static final Vector2f UNIT_SIZE = new Vector2f(0.2f,0.2f);
	private static final float SIZE = .05f;
	
	//This is the boolean that determines if groups redirect to the path of the group that made it to the end
	private boolean goalReached;
	
	private Group goalReacher;
	
	//This is the location in relation to the page spawned.
	private Vector2f location;
	
	//This is the 2d array that contains all of the locations in the grid represented by tiles
	private Tile[][] grid;
	
	private Button gridClicked;
	
	
	private int floor;
	
	public Grid(float siz, int rows, int floor) {
		
		treasureLocs = new ArrayList<Point>();
		
		trapLocs = new ArrayList<Point>();
	
		//D.x + SIZE.x/2 - A.x*floor/2, D.y+SIZE.y/2-A.x*AspectRatio*floor/2
		//Where D = bottomleftcorner of screen and A = SIZE of a single tile (in total)
		//D + SIZE/2 cancels since SIZE/2 = 1 and d = -1
		this.location = new Vector2f(-SIZE * (rows) + SIZE, -SIZE*(float)DisplayManager.getAspectratio()*(rows-1f));
		
		isOn = false;
		
		float totalLength = 0;
		StringLibrary.setSize(new Vector2f(.02f,.04f));
		for(int i = floor-1; i>=0; i--) {
			totalLength+=StringLibrary.getWidth(i+" ");
		}
		levelSelect = new Button(new Vector2f(location.x+totalLength*2, .9f),
					new Vector2f(location.x+totalLength*2+StringLibrary.getWidth(floor+" "), .9f-StringLibrary.size.y));
		
		goalReached = false;
		
		goalReacher = null;
		
		gridClicked = new Button(new Vector2f(location.x-SIZE, location.y+rows*SIZE*2*(float)DisplayManager.getAspectratio()-2*SIZE), new Vector2f(location.x-SIZE+2*SIZE*rows-.005f, location.y-2*SIZE));
		
		this.floor = floor;
		
		grid = new Tile[rows][rows];
		for(int i=0;i<rows;i++){
			for(int k=0;k<rows;k++){
				grid[k][i]=new Dirt(k,i,SIZE, floor);
			}
		}
		for(int i = 0; i<10+floor*5; i++) {
			int randX = (int)(Math.random() * grid[0].length);
			int randY = (int)(Math.random() * grid.length);
			if(floor<7) {
				int level = (int)((floor+1)*Math.random())+1;
				grid[randY][randX].upgrade(level);
				
			} else {
				int level = (int)(7*Math.random())+1;
				grid[randY][randX].upgrade(level);
			}			
		
		}
	}
	
	public static int getMinimumWidth() {
		return MINIMUM_WIDTH;
	}
	
	public static int getMaximumWidth() {
		return MAXIMUM_WIDTH;
	}
	
	public static Vector2f getUnitSize() {
		return UNIT_SIZE;
	}
	
	public static float getTileSize() {
		return SIZE;
	}
	
	public ArrayList<GuiTexture> renderFloorSelect() {
		ArrayList<GuiTexture> iDerped = new ArrayList<GuiTexture>();
		float totalLength = 0;
		StringLibrary.setSize(new Vector2f(.02f,.04f));
		for(int i = floor-1; i>=0; i--) {
			totalLength+=StringLibrary.getWidth(i+" ");
		}
		iDerped.addAll(StringLibrary.makeItFit(floor+" ", new Vector2f(location.x+totalLength*2, .9f), 2000));
		return iDerped;
	}
	
	public ArrayList<Point> getTreasureLocs() {
		return treasureLocs;
	}
	
	public ArrayList<Point> getTrapLocs() {
		return trapLocs;
	}
	
	public Grid copy() {
		Grid g = new Grid(SIZE, grid.length, floor);
		for(int i=0;i<grid.length;i++){
			for(int k=0;k<grid[0].length;k++){
				g.setTile(k, i, grid[k][i].copy());
			}
		}
		return g;
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
	
	public boolean isClicked(float mouseX, float mouseY) {
		return gridClicked.isClicked(mouseX, mouseY);
	}
	
	public float getSize() {
		return SIZE;
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
	
	public int getWidth() {
		return grid.length;
	}
	
	public void setTile(int x, int y,Tile tile){
		grid[x][y]=tile;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
	
			for(Tile[]line:grid){
				for(Tile tile:line){
					//UNCOMMENT BELOW FOR TRIPPY THINGS XD (Used for testing occupied tiles)
					//Reveals occupied tiles 
					//if(tile.isOccupied() != -1)
					//Reveals booby trapped tiles below
					//if(!tile.getTrapRefs().isEmpty())
					dynamicGuis.add(tile.drawTile());
				}
			}
		
	}
	
	public Tile[] getAdjacent(Vector2f locationInGrid/*, int id*/) {
		int absenceCounter = 0;
		//A collection of the adjacent tiles, with null if there is no tile there.
		//0 = Left, 1 = Right, 2 = Down, 3 = Up
		Tile[] adjacents = new Tile[4];
		//If not on the left border, get the tile to the left
		if((int)locationInGrid.x != 0) {
			Tile tile = getTile((int)locationInGrid.x-1, (int)locationInGrid.y);
			if(tile.id == -2) {
				adjacents[0] = tile;
			} else if(tile.isOccupied()==-1  /*|| tile.isOccupied()==id*/) {
				adjacents[0] = tile;
			} else{
				absenceCounter++;
			}
		} else {
			absenceCounter++;
		}
		
		//If not on the right border, get the tile to the right
		if((int)locationInGrid.x != grid[0].length - 1) {
			Tile tile = getTile((int)locationInGrid.x+1, (int)locationInGrid.y);
			if(tile.id == -2) {
				adjacents[1] = tile;
			} else if(tile.isOccupied()==-1 /*|| tile.isOccupied()==id*/) {
				adjacents[1] = tile;
			} else
				absenceCounter++;
		} else absenceCounter++;
		
		//If not on the bottom border of the grid, get the downwards tile
		if((int)locationInGrid.y != 0) {
			Tile tile = getTile((int)locationInGrid.x, (int)locationInGrid.y-1);
			if(tile.id == -2) {
				adjacents[2] = tile;
			} else if(tile.isOccupied() == -1  /*|| tile.isOccupied()==id*/) {
				adjacents[2] = tile;
			}else absenceCounter++;
		} else absenceCounter++;
		
		//If not on the top border, get the tile upwards of it
		if((int)locationInGrid.y != grid.length-1) {
			Tile tile = getTile((int)locationInGrid.x, (int)locationInGrid.y+1);
			if(tile.id == -2) {
				adjacents[3] = tile;
			} else if(tile.isOccupied() == -1  /*|| tile.isOccupied()==id*/) {
				adjacents[3] = tile;
			} else absenceCounter++;
		} else absenceCounter++;
		
		//If there is no where to go, stay where you are.
		if(absenceCounter == 4) {
			adjacents[0] = getTile((int)locationInGrid.x, (int)locationInGrid.y);
		}
		return adjacents;
	}
	
	public void addTrapRefsForTrap(ArrayList<Point> locationsThatRefToOrigin
			, Point originTrap) {
		if(locationsThatRefToOrigin != null) {
			for(Point locOnGrid : locationsThatRefToOrigin) {
				this.getTile(locOnGrid.x, locOnGrid.y).addTrapRef(originTrap);
			}
		}
	}
}