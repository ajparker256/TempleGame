package entities;

import java.util.ArrayList;

import main.Main;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import grid.Tile;
import gui.GuiTexture;

public class Group {

	//This is the group of explorers traveling together on a tile
	private ArrayList<Explorer> group;
	
	//This is the room on a tile for members to exist (generally 2-4 explorers or 1 big thing like a drill)
	private final int MAX_SIZE;
	private int xInGrid=0;
	private int yInGrid=0;
	private Vector2f location;
	private Vector2f velocity;
	private int nextPos;

	
	public Group() {
		nextPos=0;
		location=Main.grid.getTile(0,0).getLocation();
		velocity=new Vector2f(0,0);
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
	public void move(int milli,Grid grid) {
		if(grid.getTile(xInGrid, yInGrid).passable){
		Vector2f destination=grid.getTile(xInGrid, yInGrid).getLocation();
		Vector2f tempVelocity= new Vector2f();
		//If you aren't there yet, go somewhere
		if(!location.equals(destination)){
			//If the destination is to the right, go right
			if(location.x<destination.x){
				tempVelocity.x = velocity.x;
				setLoc(new Vector2f(location.x+tempVelocity.x*milli/1000f, location.y));
				//If you would overshoot, don't
				if(location.x>destination.x){
					location.x=destination.x;
					
				}
			//If the destination is to the left, go left
			}else if(location.x>destination.x){
				tempVelocity = new Vector2f(velocity.x*-1, 0);
				setLoc(new Vector2f(location.x+tempVelocity.x*milli/1000f, location.y));
				if(location.x<destination.x){
					location.x=destination.x;
					
				}
			//If the destination is above you, go up
			}else if(location.y<destination.y){
				tempVelocity.y = velocity.y;
				setLoc(new Vector2f(location.x, location.y+(tempVelocity.y*milli/1000f)));
				if(location.y>destination.y){
					location.y=destination.y;
					
				}
				
			//If the destination is below you, go down
			}else if(location.y>destination.y){
				tempVelocity.y = -1*velocity.y;
				setLoc(new Vector2f(location.x, location.y+(tempVelocity.y*milli/1000f)));
				if(location.y<destination.y){
					location.y=destination.y;
					velocity.y=0;
					
				}
			}
		}
	} else{
		 grid.getTile(xInGrid, yInGrid).interact();
	}
		
	}

	private void setLoc(Vector2f location) {
		this.location=location;
		
	}
	public Vector2f getLocation(){
		return location;
	}
	public ArrayList<GuiTexture> render(){
		ArrayList<GuiTexture> toRender = new ArrayList<GuiTexture>();
		for(Explorer explorer:group){
			toRender.add(explorer.render(location));
		}
		return toRender;
	}

	public int getPosition() {
		nextPos++;
		return nextPos;
	}

	
}
