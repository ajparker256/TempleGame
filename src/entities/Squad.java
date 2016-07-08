package entities;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import grid.Tile;
import main.Main;

public class Squad {

	private ArrayList<Point> path;
	private ArrayList<Group> groups;
	private int squadId;
	public Squad(ArrayList<Group> groups, int squadId){
		path= new ArrayList<Point>();
		path.add(new Point(0,0));
		this.groups=groups;
		this.squadId = squadId;
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
		for(Tile currentTile : moves) {
			if(currentTile == null) {
				i++;
				continue;
				//Adds 10 odds if its a blank tile
			} else if(currentTile.getId() == 0) {
				individualOdds[i] += 10000;
			} else if(currentTile.getId() == 1) {
				individualOdds[i] += 50;
			} else if(currentTile.getId() == 2) {
				individualOdds[i] += 300;
			}
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
	public void tick(int milli, Grid grid){
		boolean go=true;
		for(Group group:groups){
		
			group.move(milli, grid);
			if(group.isBusy()){
				go=false;
				break;
			}
			
			}
		if(go){
			Point tempNextLoc=(getNextLoc(Main.grid));

			//if(tempNextLoc.equals(path.get(0))){
				//path.remove(0);
				//path.remove(0);
			//}else{
			path.add(0,tempNextLoc);
			//}
			int i=0;
			for(Group group: groups){
				i++;
				if(i<path.size()){
					group.setNextLoc(path.get(i));
					group.setWait(false);
					//This removes the status of occupied from the tail end of the squad
					if(i == groups.size() && i+2<path.size()) 
						Main.grid.getTile(path.get(i+2).x, path.get(i+2).y).setOccupied(-1);
				}
			}
		}
		}
	
}
