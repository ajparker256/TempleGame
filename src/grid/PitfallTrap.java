package grid;

import org.lwjgl.util.vector.Vector2f;

import entities.Unit;

import java.util.ArrayList;

public class PitfallTrap extends Tile{
	
	private final int HEATDEATHOFTHEUNIVERSE;

	public PitfallTrap(Vector2f loc, Grid gr) {
		super(loc, gr);
		this.passable = true;
		this.hp = 0;
		init();
		HEATDEATHOFTHEUNIVERSE = 3; // this is the amount of units needed on the trap to trigger it
	}
	
	public void update(){
		if(unitsContained.size() >= HEATDEATHOFTHEUNIVERSE)
			trigger();
	}
	
	public void trigger(){
		for(Unit u: unitsContained){
			//KILL THE UNIT IN HERE<<<<<<<<<<<<<<<<<<<<<<----------------
		}
		this.unitsContained.clear();
	}
	
}
