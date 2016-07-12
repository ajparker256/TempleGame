package entities;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import grid.Tile;
import gui.GuiTexture;
import main.Main;

public class Squad {
	private Point backpedalTile;
	private int previousFloor;
	private ArrayList<Point> path;
	private ArrayList<Group> groups;
	private int squadId;
	public Squad(ArrayList<Group> groups, int squadId){
		path= new ArrayList<Point>();
		path.add(new Point(0,0));
		this.groups=groups;
		this.squadId = squadId;
		previousFloor = 0;
		backpedalTile=new Point();
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
		int groupWithTreasureFinder = -1;
		for(int j = 0; j<groups.size(); j++) {
			Group g = groups.get(j);
			for(int k = 0; k<g.getIds().size(); k++) {
				if(g.getIds().get(k) == 3) {
					groupWithTreasureFinder = j;
				}
			}
		}
		int[] treasureFinderOdds = new int[4];
		if(groupWithTreasureFinder != -1 && groups.get(groupWithTreasureFinder).getFloor() == groups.get(groups.size()-1).getFloor()) {
			ArrayList<Point> treasureLocs = Main.grids.get(groups.get(groupWithTreasureFinder).getFloor()).getTreasureLocs();
			for(Point loc : treasureLocs) {
				int xRange = loc.x - groups.get(groups.size()-1).getNextLoc().x;
				int yRange = loc.y - groups.get(groups.size()-1).getNextLoc().y;
				int maxOdds = 50;
				if(Math.abs(xRange) <3 && Math.abs(yRange) <3) {
					//Left && Right
					if(xRange > 0) {
					//Max odds / distance 
						treasureFinderOdds[0] = maxOdds/Math.abs(xRange);
					} else if(xRange < 0) {
						treasureFinderOdds[1] = maxOdds/Math.abs(xRange);
					}
					
					//Down then up
					if(yRange<0) {
						treasureFinderOdds[2] = maxOdds/Math.abs(yRange);
					} else if(yRange>0) {
						treasureFinderOdds[3] = maxOdds/Math.abs(yRange);
					}
				}
			}
		}
		
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
		}
		i = 0;
		
		rand *= total;
		
		//Evaluate which one random fell into
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
		System.out.println("ERROR CASE IN SQUAD PATH LOGIC!!!");
		return new Point(nextLoc.x, nextLoc.y+1);
	}
	
	//This is the place where the powers of the groups are gathered
	public void interaction() {
		//Total the attack found in the leading group to get group ATK
		//Get every type of interaction (ie. ladderman, priest, etc.) regardless of position
		//TODO add more if necessary, this should be the collab zone for info.
	}
	
	public void tick(int milli, Grid grid){
		boolean go=true;
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
