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
	
	public Explorer(int hp, Vector2f location, Vector2f size, Vector2f velocity) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(hp, location, size);
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
	
	//public void interact(int r, int c, Grid gr) {
	//	gr.get(r, c); //When grid becomes objects, have a trigger method that invokes the trap as default.
	//}
	
	//Returns number of rows and number of columns
	//public int[] getGridLocation(Grid gr) {
		//int r = (int)super.getLoc().x/gr.getGrid().length;
		//int c = (int)super.getLoc().y/gr.getGrid()[0].length;
		//int[] rc = {r, c};
		//return rc;
	//}
	
	public void setLoc(Vector2f loc) {
		location = loc;
		explorerWalk.setLoc(loc);
	}
	
	public void move(int milli,Grid grid) {
		Vector2f destination=grid.getTile(xInGrid, yInGrid).getLocation();
		Vector2f tempVelocity= new Vector2f();
		//If you aren't there yet, go somewhere
		if(!location.equals(destination)){
			//If the destination is to the right, go right
			if(location.x<destination.x){
				tempVelocity.x = velocity.x;
				setLoc(new Vector2f(getLoc().x+tempVelocity.x*milli/1000f, getLoc().y));
				//If you would overshoot, don't
				if(location.x>destination.x){
					location.x=destination.x;
					
				}
			//If the destination is to the left, go left
			}else if(location.x>destination.x){
				tempVelocity = new Vector2f(velocity.x*-1, 0);
				setLoc(new Vector2f(getLoc().x+tempVelocity.x*milli/1000f, getLoc().y));
				if(location.x<destination.x){
					location.x=destination.x;
					
				}
			//If the destination is above you, go up
			}else if(location.y<destination.y){
				tempVelocity.y = velocity.y;
				setLoc(new Vector2f(getLoc().x, getLoc().y+(tempVelocity.y*milli/1000f)));
				if(location.y>destination.y){
					location.y=destination.y;
					
				}
				
			//If the destination is below you, go down
			}else if(location.y>destination.y){
				tempVelocity.y = -1*velocity.y;
				setLoc(new Vector2f(getLoc().x, getLoc().y+(tempVelocity.y*milli/1000f)));
				if(location.y<destination.y){
					location.y=destination.y;
					velocity.y=0;
					
				}
			}
		}
		
	} 
	
	
}
