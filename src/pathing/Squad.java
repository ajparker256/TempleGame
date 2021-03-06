package pathing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;
import grid.Grid;
import grid.Tile;
import gui.GuiTexture;

public class Squad {
	private Grid previousFloor;
	private ArrayList<Point> path;
	private ArrayList<Group> groups;
	private HashMap<Integer, PathModifier> modifications;
	private boolean rotating;
	private Point toBeRemoved;
	private boolean isNotFirstTime;
	public Squad(ArrayList<Group> groups, int squadId){
		float speedBonus=0;
		for(Group group:groups){
			for(Explorer explorer:group.getGroup()){
				speedBonus+=explorer.getSpeedProvided();
			}
		}
		for(Group group:groups){
			for(Explorer explorer:group.getGroup()){
				explorer.addSpeedBonus(speedBonus);
			}
		}
		rotating=true;
		modifications = new HashMap<Integer, PathModifier>(10, .8f);
		path= new ArrayList<Point>();
		path.add(new Point(0,0));
		this.groups=groups;
		DefaultPM defaultAI = new DefaultPM(this);
		defaultAI.addModifier(modifications);
		for(Group g : groups) {
			for(Explorer e : g.getGroup()) {
				if(e.getId() == 3) {
					TreasureHunterPM treasureGreed = new TreasureHunterPM(this);
					treasureGreed.addModifier(modifications);;
				}
			}
		}
		toBeRemoved = new Point(-1,-1);
		isNotFirstTime = false;
	}
	
	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public ArrayList<GuiTexture> render(int currentlyViewedFloor) {
		ArrayList<GuiTexture> toBeRendered = new ArrayList<GuiTexture>();
		for(Group g : groups) {
			toBeRendered.addAll(g.render(currentlyViewedFloor));
		}
		return toBeRendered;
	}
	
	public Point getNextLoc(Grid currentFloor) {
		System.out.println("Squad is finding nextLoc");
		Point nextLoc = path.get(0);
		double rand = Math.random();
		Tile[] moves = currentFloor.getAdjacent(new Vector2f(nextLoc.x, nextLoc.y));
		
		//Guarentees that the units don't miss the exit
		for(Tile t : moves) {
			if(t!=null && t.getId() == -2) {
				return new Point(t.getX(), t.getY());
			}
		}
		
		int total = 0;
		int[] individualOdds = new int[4];

		Collection<PathModifier> allMods = modifications.values();
		Iterator<PathModifier> iterator = allMods.iterator();
		for(int j = 0; j<modifications.size(); j++) {
			int x = 0;
			int[] changes = iterator.next().modify(moves, currentFloor);
			for(int k : changes) {
				total += k;
				individualOdds[x] += k;
				x++;
			} 
		}
	
		rand *= total;
		int i = 0;
		for(int currentOdds : individualOdds) {
			if(rand < currentOdds) {
				return new Point(moves[i].getX(), moves[i].getY());
			} else {
				i++;
				rand -= currentOdds;
			}
		}
		System.out.println("ERROR CASE IN SQUAD PATH LOGIC!!!");
		return new Point(nextLoc.x, nextLoc.y+1);
	}
	
	//This is the place where the powers of the groups are gathered
	public void interaction() {
		//Total the attack found in the leading group to get group ATK
		//Get every type of interaction (ie. ladderman, priest, etc.) regardless of position
		//TODO add more if necessary, this should be the collab zone for info.
	}
	
	public HashMap<Integer, PathModifier> getPathMods() {
		return modifications;
	}
	
	public int getFurthestFloor() {
		int indexOfFirstGroup = 0;
		return groups.get(indexOfFirstGroup).getFloor();
	}
	
	
	public void deathTriggers(Explorer e) {
		//Removes any path modifiers that were added by the dead explorers
		PathModifier toBeRemoved = modifications.get(e.getId());
		if(toBeRemoved != null) {
			toBeRemoved.cleanUp();
		}
	}
	
	private void flee() {
		
		toBeRemoved = path.remove(0);
		for(Group group:groups){
			group.setFlee(true);
		}
	}
	
	//given floor is from the front of the squad
	public void tick(long milli, Grid givenFloor){
		if(previousFloor == null) {
			previousFloor = givenFloor;
		}
		ArrayList<Group> dead = new ArrayList<Group>();
		for(Group currentGroup : groups) {	
			tickExplorers(milli, currentGroup);
			dead.add(gatherDead(currentGroup));
			gatherNewPathModifiers(currentGroup);
		}
		if(!dead.isEmpty()) {
			for(Group deadGroup : dead) {
				int placement = groups.indexOf(deadGroup);
				for(int i = 0; i < placement; i++) {
					groups.get(i);
				}
			}
			
			groups.removeAll(dead);
			
		}

		if(moveUntilCannot(milli, givenFloor)){
			if(rotating){
				Point tempNextLoc=getNextLoc(givenFloor);
				System.out.println("X: " + tempNextLoc.x + " Y: " + tempNextLoc.y);
				if(isNotFirstTime) {
					if(!path.get(0).equals(tempNextLoc)) {
						path.add(0,tempNextLoc);
					} else {
						flee();
					}
					
				} else {
					isNotFirstTime = true;
				}
				//Flee code
				if(path.size()>groups.size()+2){
					if(Mouse.isButtonDown(1)){
						path.remove(0);
						flee();
					}else{
						toBeRemoved = new Point(-1,-1);
						for(Group group:groups){
							group.setFlee(false);
						}
					}
				}
				
				for(Group group: groups){
					//This removes the status of occupied from the tail end of the squad
					//+1 is the tile the last person is currently leaving, +2 is the one that is out of use
					if(groups.size()+2<path.size() && !group.getFlee() && previousFloor.getTile(path.get(groups.size()+2).x, path.get(groups.size()+2).y).getOccupied() > -2) {
						previousFloor.getTile(path.get(groups.size()+2).x, path.get(groups.size()+2).y).setOccupied(-1);
					} else if(group.getFlee() && toBeRemoved.x != -1) {
						previousFloor.getTile(toBeRemoved.x, toBeRemoved.y).setOccupied(-1); 
						toBeRemoved = new Point();
					}else break;
				}
			
				int i=0;
				for(Group group:groups){
					rotating=true;
					if(i<path.size()){
						if(i+1<path.size()){
							group.rotate(path.get(i+1));
						}else 
							group.rotate(path.get(i));
						}
					i++;
					rotating=false;
				}
				
			}else{
				rotating=true;
				int i=0;
				for(Group group: groups){
					i++;
					if(i<path.size()){
						group.setNextLoc(path.get(i)); //PATH GETS SET HERE
					}
				}
					
			}
			assignPreviousFloor(givenFloor);
		}
		
	}
	
	private void tickExplorers(long milli, Group currentGroup) {
			for (Explorer explorer: currentGroup.getGroup()){
				explorer.tick(milli);
			}
	}
	
	private Group gatherDead(Group currentGroup) {
		for(int i=0;i<currentGroup.getGroup().size();i++){
			Explorer explorer=currentGroup.getGroup().get(i);
			if(explorer.isKill()) {
				deathTriggers(explorer);
				currentGroup.removeExplorer(explorer);
			}
		}
		if(currentGroup.getGroup().size()==0){
			return currentGroup;
		}
		return null;
	}
	
	private void gatherNewPathModifiers(Group currentGroup) {
		ArrayList<PathModifier> newMods = currentGroup.collectPathModifiers();
		if(newMods != null) {
			for(PathModifier pm : newMods) {
				pm.addModifier(modifications);
			}
		}
	}
	
	private boolean moveUntilCannot(long milli, Grid givenFloor) {
		boolean go = true;
		boolean cannotStillMove;
		for(Group currentGroup : groups) {
			if(currentGroup.getFloor() == givenFloor.getFloor()) {
				cannotStillMove = currentGroup.move(milli, givenFloor);
			} else {
				cannotStillMove = currentGroup.move(milli, previousFloor);
			}
			if(cannotStillMove){
				for(Group group2: groups){
					group2.setIdle();
				}
				go = false;
				return go;
			}
			if(currentGroup.isBusy()){
				go=false;
			}
		}
		return go;
	}
	
	private void assignPreviousFloor(Grid currentFloor) {
		if(groups.get(groups.size()-1).getFloor() == currentFloor.getFloor()) {
			previousFloor = currentFloor;
		}
	}
	
	public void findNextLoc(boolean canGo) {
		//TODO
	}
}