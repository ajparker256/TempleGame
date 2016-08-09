package pathing;

import grid.Tile;

public class PathModifier {

		protected Squad squad;
		
		protected int id;
	
		PathModifier(Squad s) {
			id = -1;
			squad = s;
		}
		
		public int getId() {
			return id;
		}
		
		public int[] modify(Tile[] adjacentTiles) {
			//Stub
			return null;
		}
		
		public void cleanUp() {
			squad.getPathMods().remove(id);
		}
}
