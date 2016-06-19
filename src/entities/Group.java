package entities;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import grid.Tile;

public class Group {

	//This is the group of explorers traveling together on a tile
	private ArrayList<Explorer> group;
	
	//This is the room on a tile for members to exist (generally 2-4 explorers or 1 big thing like a drill)
	private final int MAX_SIZE;
	
	public Group() {
		group = new ArrayList<Explorer>();
		MAX_SIZE = 4;
	}
	
	public int getMaxSize() {
		return MAX_SIZE;
	}
	
	public ArrayList<Explorer> getGroup() {
		return group;
	}
	
	public void add(Explorer e) {
		group.add(e);
	}
	
	public Vector2f getNextLoc(Grid currentFloor) {
		//If no one has reached the goal
		int totalOdds = 100;
		//These are the odds for each individual option (Capped to 4 since there are 4 options)
		//The order is 0 = Right 1 = Left 2 = Down 3 = Up
		int[] individualOdds = new int[4];
		if(!group.isEmpty()) {
			//And no one has reached the goal yet
			if(!currentFloor.getGoalReached()) {
				Tile[] adjacents = currentFloor.getAdjacent(group.get(0).getLocInGrid(currentFloor));
				//This is the number of possible adjacent locations there are. Used to determine baseline stats (4 = 25% 3 = 33% 2 = 50%)
				int numberOfOptions = 0;
				for(Tile currentTile : adjacents) {
					if(!currentTile.equals(null)) {
						
						//TODO Add logic here for each individual type or interaction
						//TODO Incorporate the types of exlorers involved in the party
						//Each interaction grants bonuses to individualOdds of the cooresponding location
						//Then you subtract what you awarded to the individual tile from the total
						//The remainder is added to all by dividing it by number of Options. 
						
						numberOfOptions++;
					}
				}
				//
			} 
			//If someone has found the goal
			else {
				//TODO send them onto the person who found the goal's path
			}
		}
		//Error case where the group is empty
		return null;
		
	}
	
}
