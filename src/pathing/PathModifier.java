package pathing;

import java.util.HashMap;

import grid.Grid;
import grid.Tile;

public abstract class PathModifier {

		protected Squad squad;
		
		protected int id;
		
		protected int numberOfThisType;
	
		PathModifier(Squad s) {
			id = -1;
			squad = s;
			numberOfThisType = 1;
		}
		
		public void addModifier(HashMap<Integer, PathModifier> modifications) {
			if(modifications.containsKey(id)) {
				modifications.get(id).setNumberOfThisType(modifications.get(id).getNumberOfThisType()+1);
			} else {
				modifications.put(id, this);
			}
		}
		
		public int getNumberOfThisType() {
			return numberOfThisType;
		}
		
		public void setNumberOfThisType(int i) {
			numberOfThisType = i;
		}
		
		public int getId() {
			return id;
		}
		
		public abstract int[] modify(Tile[] adjacentTiles, Grid currentFloor);
		
		public void cleanUp() {
			HashMap<Integer, PathModifier> mods = squad.getPathMods();
			if(mods.containsKey(id)) {
				if(mods.get(id).getNumberOfThisType()<=1) {
					mods.remove(id);
				} else {
					mods.get(id).setNumberOfThisType(mods.get(id).getNumberOfThisType()-1);
				}
			}
			squad.getPathMods().remove(id);
		}
}
