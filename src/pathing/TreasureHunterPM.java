package pathing;

import java.awt.Point;
import java.util.ArrayList;

import grid.Tile;
import main.Main;

public class TreasureHunterPM extends PathModifier{

	public TreasureHunterPM(Squad s) { 
		super(s);
		id = 3;
	}
	
	@Override
	public int[] modify(Tile[] adjacentTiles) {
		int[] treasureFinderOdds = new int[4];
		ArrayList<Group> groups	 = squad.getGroups();
			ArrayList<Point> treasureLocs = Main.grids.get(groups.get(groups.size()-1).getFloor()).getTreasureLocs();
				for(Point loc : treasureLocs) {
					
					int xRange = loc.x - groups.get(groups.size()-1).getNextLoc().x;
					int yRange = loc.y - groups.get(groups.size()-1).getNextLoc().y;
					int maxOdds = 50;
					if(Math.abs(xRange) <4 && Math.abs(yRange) <4) {
						//Left && Right
						if(xRange > 0 && adjacentTiles[0] != null) {
							//Max odds / distance 
							treasureFinderOdds[0] = maxOdds/Math.abs(xRange);
						} else if(xRange < 0 && adjacentTiles[1] != null) {
							treasureFinderOdds[1] = maxOdds/Math.abs(xRange);
						}
					
						//Down then up
						if(yRange<0 && adjacentTiles[2] != null) {
							treasureFinderOdds[2] = maxOdds/Math.abs(yRange);
						} else if(yRange>0 && adjacentTiles[3] != null) {
							treasureFinderOdds[3] = maxOdds/Math.abs(yRange);
						}
					}
				}
			
			
			return treasureFinderOdds;
		}
	}
	

