package pathing;

import grid.Tile;

public class PathModifier {

		protected Squad squad;
	
		PathModifier(Squad squad) {
			squad.getPathMods().add(this);
			this.squad = squad;
		}
		
		public int[] modify(Tile[] adjacentTiles) {
			//Stub
			return null;
		}
		
		public void cleanUp() {
			squad.getPathMods().remove(this);
		}
}
