package entities;

import org.lwjgl.util.vector.Vector2f;
import grid.Grid;

public class Explorer extends Unit{
	
	Explorer(int hp, Vector2f location, Vector2f velocity, int id) {
		//Hit points, location in Pixels, Velocity in Pixels, id to recognize later
		super(hp, location, velocity, id);
	}
	
	//This finds if the explorer has an interaction with the given tile
	public boolean canInteract(int r, int c, Grid gr) {
		//TODO Stub, to be implemented individually
		if(gr.get(r,c) == 0) {
			return false;
		}
		else return true;
	}
	
	public void interact(int r, int c, Grid gr) {
		gr.get(r, c); //When grid becomes objects, have a trigger method that invokes the trap as default.
	}
	
	//Returns number of rows and number of columns
	public int[] getGridLocation(Grid gr) {
		int r = (int)super.getLoc().x/gr.getGrid().length;
		int c = (int)super.getLoc().y/gr.getGrid()[0].length;
		int[] rc = {r, c};
		return rc;
	}
}
