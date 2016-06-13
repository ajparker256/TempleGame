package grid;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

//This makes a grid for laying out other objects. It is used for in game management
//of space. It allows for the organization of turrets and enemy locations for range.

public class Grid {
	
	//Conversion factor for the grid vs pixel amount
	public final Vector2f GRID_UNIT;
	
	//This is the size of the grid in pixels.
	private Vector2f size;
	
	//This is the location in relation to the page spawned.
	private Vector2f location;
	
	//This is the 2d array that contains all of the locations in the grid represented by tiles
	private Tile[][] gr;
	
	//This acts as a unifying number so that groups don't get the same id within the grid.
	private int idCount;
	
	//makes a grid of boolean values with length r and height r along with a raw size of s at location l.
	public Grid(Vector2f l, Vector2f s, int r) {
		size = s;
		location = l;
		gr = new Tile[r][r];
		idCount = 1;
		GRID_UNIT = new Vector2f(size.x/r, size.y/r);
	}
	
	//makes a grid of boolean values with length r and height c along with a raw size of s at location l.
	public Grid(Vector2f l, Vector2f s, int r, int c){
		size = s;
		location = l;
		gr = new Tile[r][c];
		idCount = 1;
		GRID_UNIT = new Vector2f(size.x/r, size.y/c);
	}
	
	//Changes the value of a piece of the array.
	public void change(int r, int c, Tile b) {
		gr[r][c] = b;
	}
	
	//Checks the value at r,c.
	public Tile get(int r, int c) {
		return gr[r][c];
	}
	
	//Gives grid value based off a vector.
	public Tile get(Vector2f r) {
		return gr[(int)r.x][(int)r.y];
	}
	
	//Returns the grid
	public Tile[][] getGrid() {
		return gr;
	}
	
	//Returns the size
	public Vector2f getSize() {
		return size;
	}
	
	//Returns the location
	public Vector2f getLoc() {
		return location;
	}
	
	public int getId() {
		return idCount;
	}
	
	public void render() {
		//TODO render the grid
	}
	
	//This is the A* Value system. Distance between start and point vs end and point.
	private static int value(Vector2f start, Vector2f goal, Vector2f loc) {
		
		//A* path evaluation
		return 0;
		
	}
	
	private Vector2f[] getAdjacent(Vector2f loc) {
		//An array of the locations that an object can move to assuming 4 way motion only
		//This gets the adjacent locations if possible and leaves null if they do not exist
		Vector2f[] adjacents = new Vector2f[4];
		//Left side
		if(loc.x!=0 && (gr[(int)loc.x-1][(int)loc.y] == 0 || gr[(int)loc.x-1][(int)loc.y] == 100)) {
			adjacents[0] = new Vector2f(loc.x-1, loc.y);
		}else {
			adjacents[0] = new Vector2f(-1, -1);
		}
		//Top side
		if(loc.y!=0 && (gr[(int)loc.x][(int)loc.y-1] == 0|| gr[(int)loc.x][(int)loc.y-1] == 100)) {
			adjacents[1] = new Vector2f(loc.x, loc.y-1);
		}else {
			adjacents[1] = new Vector2f(-1, -1);
		}
		//Right side
		if(loc.x!=gr.length-1 && (gr[(int)loc.x+1][(int)loc.y] == 0 || gr[(int)loc.x+1][(int)loc.y] == 100)) {
			adjacents[2] = new Vector2f(loc.x+1, loc.y);
		}else {
			adjacents[2] = new Vector2f(-1, -1);
		}
		//Bottom side
		if(loc.y!=gr[0].length-1 && (gr[(int)loc.x][(int)loc.y+1] == 0 || gr[(int)loc.x][(int)loc.y+1] == 100)) {
			adjacents[3] = new Vector2f(loc.x, loc.y+1);
		}else {
			adjacents[3] = new Vector2f(-1, -1);
		}
		
		return adjacents;
	}
	
	
	
	
}