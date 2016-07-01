package entities;

import java.util.ArrayList;

import main.Main;
import renderEngine.DisplayManager;

import org.lwjgl.util.vector.Vector2f;
import java.awt.Point;

import grid.Grid;
import grid.Tile;
import gui.GuiTexture;

public class Group {

	//This is the group of explorers traveling together on a tile
	private ArrayList<Explorer> group;
	
	//This is the room on a tile for members to exist (generally 2-4 explorers or 1 big thing like a drill)
	private final int MAX_SIZE;
	private Point nextLoc;
	private Vector2f location;
	private Vector2f velocity;
	private int nextPos;
	private int direction=0;

	public Group() {
		nextLoc=new Point(0,0);
		nextPos=0;
		location=new Vector2f(Main.grid.getTile(0,0).getLocation().x,Main.grid.getTile(0,0).getLocation().y);
		location.x=location.x-0.1f;
		velocity=new Vector2f(0.1f,(float)(0.1f*DisplayManager.getAspectratio()));
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
	
	public Point getNextLoc(Grid currentFloor) {
		double rand = Math.random();
		Tile[] moves = currentFloor.getAdjacent(new Vector2f(nextLoc.x, nextLoc.y));
		int total = 0;
		int[] individualOdds = new int[4];
		int i = 0;
		for(Tile currentTile : moves) {
			//System.out.println(currentTile+" This is the tile in moves["+i+"]");
			if(currentTile == null) {
				i++;
				continue;
				//Adds 10 odds if its a blank tile
			} else if(currentTile.getId() == 0) {
				individualOdds[i] += 10;
			} else if(currentTile.getId() == 1) {
				individualOdds[i] += 50;
			}
			total+=individualOdds[i];
			i++;
		}
		i = 0;
		
		rand *= total;
		
		for(int currentOdds : individualOdds) {
			if(currentOdds == 0) {
				i++;
				continue;
			}
			if(rand <= currentOdds) {
				return new Point(moves[i].getX(), moves[i].getY());
			} else {
				i++;
				rand -= currentOdds;
			}
		}
		/*if(rand < .5) {
			return new Point(nextLoc.x-1, nextLoc.y);
		}
		if(rand < .75) {
			return new Point(nextLoc.x, nextLoc.y-1);
		}*/
		System.out.println("ERROR CASE IN GROUP PATH LOGIC!!!");
		return new Point(nextLoc.x, nextLoc.y+1);
		/*
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
		*/
	}
	public void move(int milli,Grid grid) {
		
		Vector2f destination=grid.getTile(nextLoc.x, nextLoc.y).getLocation();
		Vector2f tempVelocity= new Vector2f();
		if(grid.getTile(nextLoc.x, nextLoc.y).canInteract()){
			for(Explorer e: group){
				e.interact(direction);
			}
			 grid.getTile(nextLoc.x, nextLoc.y).interact();
		}else{
		//If you aren't there yet, go somewhere
		if(!(location.x==destination.x&&location.y==destination.y)){
			//If the destination is to the right, go right
			if(location.x<destination.x){
				direction=2;
				tempVelocity.x = velocity.x;
				setLoc(new Vector2f(location.x+tempVelocity.x*milli/1000f, location.y));
				//If you would overshoot, don't
				if(location.x>destination.x){
					location.x=destination.x;
					direction=12;
				}
			//If the destination is to the left, go left
			}else if(location.x>destination.x){
				direction=4;
				tempVelocity = new Vector2f(velocity.x*-1, 0);
				setLoc(new Vector2f(location.x+tempVelocity.x*milli/1000f, location.y));
				if(location.x<destination.x){
					location.x=destination.x;
					direction=14;
					
				}
			//If the destination is above you, go up
			}else if(location.y<destination.y){
				direction=1;
				tempVelocity.y = velocity.y;
				setLoc(new Vector2f(location.x, location.y+(tempVelocity.y*milli/1000f)));
				if(location.y>destination.y){
					location.y=destination.y;
					direction=11;
				}
				
			//If the destination is below you, go down
			}else if(location.y>destination.y){
				direction=3;
				tempVelocity.y = -1*velocity.y;
				setLoc(new Vector2f(location.x, location.y+(tempVelocity.y*milli/1000f)));
				if(location.y<destination.y){
					location.y=destination.y;
					direction=13;
					
				}
			}
			
		}else {
			Point nextLocTemp=getNextLoc(Main.grid);
			Vector2f locationNext=grid.getTile(nextLocTemp.x, nextLocTemp.y).getLocation();
			if(location.x<locationNext.x){
			direction=2;
			
		}else if(location.x>locationNext.x){
			direction=4;

			
		}else if(location.y<locationNext.y){
			direction=1;
		
			

		}else if(location.y>locationNext.y){
			direction=3;

		}
		
			
			for(Explorer e: group){
				e.rotate(direction);
			}
			nextLoc=nextLocTemp;
		}
		
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

	public int getDirection() {
		return direction;
	}


	
}
