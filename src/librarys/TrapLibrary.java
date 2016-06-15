package librarys;

import grid.Grid;
import grid.PitfallTrap;
import org.lwjgl.util.vector.Vector2f;

public class TrapLibrary {
	//Collection of Ground based traps
	public static PitfallTrap pit;
	
	
	//Collection of Unit based Traps
	
	//Collection of Other Traps
	
	public static void init(Grid gr) {
		pit = new PitfallTrap(new Vector2f(4f,4f), gr);
	}
	
	public static Object getTrap(int i) {
		if(i==0) {
			return pit;
		}
		return null;
	}
}
