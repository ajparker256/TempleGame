package entities;

import org.lwjgl.util.vector.Vector2f;
import grid.Grid;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.GuiLibrary;
import renderEngine.Loader;

public class Explorer extends Unit{
	
	Animation explorerWalk;
	
	public Explorer(Group group) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(group);
		this.velocity = velocity;
		explorerWalk = new Animation(location);
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
	
	public Animation getWalkingAnimation(Loader loader, int delay) {
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerStanding, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerWalkingL, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerStanding, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerWalkingR, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.setDelay(delay);
		return explorerWalk;
	}
	

	
	public void setLoc(Vector2f loc) {
		location = loc;
		explorerWalk.setLoc(loc);
	}
	
	
	
	
}
