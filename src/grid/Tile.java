package grid;

import entities.Unit;
import gui.GuiTexture;

import java.util.ArrayList;

import librarys.TextureLibrary;

import org.lwjgl.util.vector.Vector2f;

// This is the superclass for all tiles contained in a grid

public class Tile {
	
	//the location of this tile in the grid, stores row then column (x then y)
	protected Vector2f locInGrid;
	
	//the units contained within this tile
	protected ArrayList<Unit> unitsContained;
	
	protected int hp;
	
	protected int MAXHP;
	
	protected boolean passable;
	
	protected Grid gr;
	
	protected Vector2f position;
	//creates a tile in location loc

	public Tile(Vector2f loc, Grid gr){
		
		this.locInGrid = loc;
		this.gr = gr;
		//this code declaring position is under the assumption that position for guitextures is
		//in the upper left of the image
		this.position = new Vector2f(
			gr.getLoc().x + gr.getGridUnit().x * this.locInGrid.y,
			gr.getLoc().y + gr.getGridUnit().y * this.locInGrid.x
		);

	}
	
	protected void init(){
		this.MAXHP = this.hp;
	}
	
	//returns the arraylist of units contained within the tile
	public ArrayList<Unit> getUnits(){
		return this.unitsContained;
	}
	
	public void add(Unit u){
		this.unitsContained.add(u);
	}
	
	public Vector2f getLoc(){
		return this.locInGrid;
	}
	
	//subtracts a pterodactyl amount of hp from the dirt
	public void dealDamage(int pterodactyl){
		if(this.hp - pterodactyl < 0)
			this.hp = 0;
		else
			this.hp -= pterodactyl;
	}
	
	public void repair(int trains){
		if(this.hp + trains > MAXHP)
			this.hp = MAXHP;
		else
			this.hp += trains;
	}
	
	public int getHp(){
		return this.hp;
	}
	
	public Grid getGrid(){
		return this.gr;
	}




}
