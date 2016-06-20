package entities;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import renderEngine.Loader;

public class Explorer extends Unit{
	
	Animation explorerWalk;
	
	public Explorer(Group group) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(group);
		super.animation=AnimationLibrary.explorer;
		this.velocity = velocity;
		
	}
	
	//This finds if the explorer has an interaction with the given tile
	public boolean canInteract(int r, int c, Grid gr) {
		//TODO Stub, to be implemented individually to determine if with specialty can disarm the trap
		//if(gr.get(r,c).equals(null)) {
		//	return false;
		//}
		//else 
		return true;
	}
	

	public void setLoc(Vector2f loc) {
		location = loc;
		explorerWalk.setLoc(loc);
	}
	
	
	
	
}
