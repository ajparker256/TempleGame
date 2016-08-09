package pathing;

import grid.Tile;

public class DefaultPM extends PathModifier{
	
	public DefaultPM(Squad s) {
		super(s);
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
				individualOdds[i] += 10*numberOfThisType;
			} else if(currentTile.getId() == 1) {
				individualOdds[i] += 50*numberOfThisType;
			} else if(currentTile.getId() == 2) {
				individualOdds[i] += 300*numberOfThisType;
			} else if(currentTile.getId() == 4) {
				individualOdds[i] += 600*numberOfThisType;
			} else {
				individualOdds[i] += 200*numberOfThisType;
			}
			i++;
		}
		return individualOdds;
	}

}
