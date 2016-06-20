package entities;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.Loader;

public class Explorer extends Unit{
	private int position;
	Animation explorerWalk;
	
	public Explorer(Group group) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(group);
		position=group.getPosition();
		System.out.println(position);
		super.animation=new Animation(AnimationLibrary.explorer,location,size);
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
		explorerWalk.setLoc(loc);
	}
	
	public GuiTexture render(Vector2f location) {
		
		switch(position){
		case 1:location=new Vector2f(location.x-0.02f,location.y+0.05f);
		break;
		case 2:location=new Vector2f(location.x+0.02f,location.y+0.05f);
		break;
		case 3:location=new Vector2f(location.x-0.02f,location.y-0.05f);
		break;
		case 4:location=new Vector2f(location.x+0.02f,location.y-0.05f);
		break;
			
		}
		animation.setLoc(location);
		return animation.getFrame();
	}
	
	
}
