package pathing;

import grid.Tile;

public class PathModifier {

		protected Squad squad;
		
		protected int id;
	
		PathModifier(Squad squad) {
			id = -1;
			squad.getPathMods().add(this);
			this.squad = squad;
		}
		
		public int getId() {
			return id;
		}
		
		public int[] modify(Tile[] adjacentTiles) {
			//Stub
			return null;
		}
		
		public void cleanUp() {
			squad.getPathMods().remove(this);
		}
}
