package pathing;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;
import grid.Grid;
import grid.Tile;
import gui.GuiTexture;
import main.Main;

public class Squad {
	private Point backpedalTile;
	private int previousFloor;
	private ArrayList<Point> path;
	private ArrayList<Group> groups;
	private ArrayList<PathModifier> modifications;
	public Squad(ArrayList<Group> groups, int squadId){
		modifications = new ArrayList<PathModifier>();
		path= new ArrayList<Point>();
		path.add(new Point(0,0));
		this.groups=groups;
		previousFloor = 0;
		backpedalTile=new Point();
		DefaultPM defaultAI = new DefaultPM(this);
		for(Group g : groups) {
			for(Explorer e : g.getGroup()) {
				if(e.getId() == 3) {
					TreasureHunterPM treasureGreed = new TreasureHunterPM(this);
				}
			}
		}
	}
	
	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public ArrayList<GuiTexture> render() {
		ArrayList<GuiTexture> toBeRendered = new ArrayList<GuiTexture>();
		for(Group g : groups) {
			toBeRendered.addAll(g.render());
		}
		return toBeRendered;
	}
	
	public Point getNextLoc(Grid currentFloor) {
		Point nextLoc= path.get(0);
		double rand = Math.random();
		Tile[] moves = currentFloor.getAdjacent(new Vector2f(nextLoc.x, nextLoc.y)/*, squadId*/);
		int total = 0;
		int[] individualOdds = new int[4];
		int i = 0;
		//TODO add a win-condition event here, where they backtrack to start and head for the stairs
		//For each tile type, add the appropriate odds and total them
		/*boolean[] groupsWithTreasureFinder = new boolean[groups.size()];
		int num = 0;
		int treasureFloor = -1;
		for(int j = 0; j<groups.size(); j++) {
			Group g = groups.get(j);
			boolean hasTreasureFinder = false;
			for(int k = 0; k<g.getGroup().size(); k++) {
				if(g.getGroup().get(k).getId() == 3) {
					hasTreasureFinder = true;
					num++;
					treasureFloor = g.getFloor();
				}
			}
			groupsWithTreasureFinder[j] = hasTreasureFinder;
		}*/
		for(int j = 0; j<modifications.size(); j++) {
			int x = 0;
			int[] changes = modifications.get(j).modify(moves);
			for(int k : changes) {
				total += k;
				individualOdds[x] += k;
				x++;
			}
		}
		/*
		for(Tile currentTile : moves) { 
			if(currentTile == null) {
				i++;
				continue;
				//Adds 10 odds if its a blank tile
			} else if(currentTile.getId() == -2) {
				return new Point(currentTile.getX(), currentTile.getY());
			} else if(currentTile.getId() == 0) {
				individualOdds[i] += 10;
			} else if(currentTile.getId() == 1) {
				individualOdds[i] += 50;
			} else if(currentTile.getId() == 2) {
				individualOdds[i] += 300;
			} else if(currentTile.getId() == 4) {
				individualOdds[i] += 600;
			}
			individualOdds[i] += treasureFinderOdds[i];
			total+=individualOdds[i];
			i++;
		}*/
		rand *= total;
		
		//Evaluate which one random fell into
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
	
	public ArrayList<PathModifier> getPathMods() {
		return modifications;
	}
	
	public void deathTriggers(Explorer e) {
		for(int i = 0; i<modifications.size(); i++) {
			if(modifications.get(i).getId()==e.getId()) {
				modifications.get(i).cleanUp();
				break;
			}
		}
	}
	
	public void tick(int milli, Grid grid){
		ArrayList<Group> toRemove=new ArrayList<Group>(); 
		for(Group group:groups){
			for(int i=0;i<group.getGroup().size();i++){
				Explorer explorer=group.getGroup().get(i);
				if(explorer.isKill()) {
					deathTriggers(explorer);
					group.removeExplorer(explorer);
				}
			}
			if(group.getGroup().size()==0){
				toRemove.add(group);
			}
		}
		groups.removeAll(toRemove);
		boolean go=true;
	if(!groups.isEmpty())	{
		for(Group group:groups){
			if(group.move(milli, Main.grids.get(group.getFloor()))){
				for(Group group2: groups){
					group2.setIdle();
				}
				go=false;
				break;
			}
			if(group.isBusy()){
				go=false;
			}
			
			}
		if(go){
			Point tempNextLoc=(getNextLoc(Main.grids.get(groups.get(0).getFloor())));
			path.add(0,tempNextLoc);
			if(path.size()>2)
				if(path.get(2).equals(tempNextLoc)){
					if(backpedalTile!=tempNextLoc){
						backpedalTile=tempNextLoc;
						path.remove(0);
						path.remove(0);
					}
				}
			int i=0;
			//boolean fl = true;
			for(Group group: groups){
				i++;
				if(i<path.size()){
					group.setNextLoc(path.get(i));
					group.setWait(false);
					//This removes the status of occupied from the tail end of the squad
					//+1 is the tile the last person is currently leaving, +2 is the one that is out of use
					
					if(i == groups.size() && i+2<path.size()) 
						Main.grids.get(previousFloor).getTile(path.get(i+2).x, path.get(i+2).y).setOccupied(-1);
				} else break;
				}
			previousFloor = groups.get(groups.size()-1).getFloor();
			}
		}
	}
}
