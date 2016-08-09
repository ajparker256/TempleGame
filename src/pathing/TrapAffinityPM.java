package pathing;

import java.awt.Point;
import java.util.ArrayList;

import grid.Tile;
import main.Main;

public class TrapAffinityPM extends PathModifier{
	
	public TrapAffinityPM(Squad s) {
		super(s);
		id = 4;
	}
	
	public int[] modify(Tile[] adjacentTiles) {
		int[] trapModifier = new int[4];
		ArrayList<Group> groups = squad.getGroups();
			ArrayList<Point> treasureLocs = Main.grids.get(groups.get(groups.size()-1).getFloor()).getTrapLocs();
				for(Point loc : treasureLocs) {
					
					int xRange = loc.x - groups.get(groups.size()-1).getNextLoc().x;
					int yRange = loc.y - groups.get(groups.size()-1).getNextLoc().y;
					int maxOdds = 200;
					if(Math.abs(xRange) <4 && Math.abs(yRange) <4) {
						//Left && Right
						if(xRange > 0 && adjacentTiles[0] != null) {
							//Max odds / distance 
							trapModifier[0] = maxOdds/Math.abs(xRange)*numberOfThisType;
						} else if(xRange < 0 && adjacentTiles[1] != null) {
							trapModifier[1] = maxOdds/Math.abs(xRange)*numberOfThisType;
						}
					
						//Down then up
						if(yRange<0 && adjacentTiles[2] != null) {
							trapModifier[2] = maxOdds/Math.abs(yRange)*numberOfThisType;
						} else if(yRange>0 && adjacentTiles[3] != null) {
							trapModifier[3] = maxOdds/Math.abs(yRange)*numberOfThisType;
						}
					}
				}
			
			
			return trapModifier;
		}

}
