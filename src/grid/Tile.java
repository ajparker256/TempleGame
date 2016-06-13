package grid;

import entities.Unit;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;

// This is the superclass for all tiles contained in a grid

public class Tile {
	
	//the location of this tile in the grid
	private Vector2f locInGrid;
	
	//the units contained within this tile
	private ArrayList<Unit> unitsContained;
	
	protected int hp;
	
	protected int MAXHP;
	
	protected boolean passable;
	
	//creates a tile in location loc
	public Tile(Vector2f loc){
		
		this.locInGrid = loc;
		
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


}
