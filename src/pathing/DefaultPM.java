package pathing;

import java.awt.Point;

import grid.Tile;

public class DefaultPM extends PathModifier{
	
	public DefaultPM(Squad squad) {
		super(squad);
		id = 0;
	}
	
	@Override
	public int[] modify(Tile[] adjacentTiles) {
		int i = 0;
		int[] individualOdds = new int[4];
		for(Tile currentTile : adjacentTiles) { 
			if(currentTile == null) {
				i++;
				continue;
				//Adds 10 odds if its a blank tile
			} else if(currentTile.getId() == 0) {
				individualOdds[i] += 10;
			} else if(currentTile.getId() == 1) {
				individualOdds[i] += 50;
			} else if(currentTile.getId() == 2) {
				individualOdds[i] += 300;
			} else if(currentTile.getId() == 4) {
				individualOdds[i] += 600;
			}
			i++;
		}
		return individualOdds;
	}

}
