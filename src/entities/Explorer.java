package entities;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;
import renderEngine.Loader;

public class Explorer extends Unit{
	private int position;
	private Group group;
	private Vector2f unitSize;
	
	public Explorer(Group group) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(group);
		this.group=group;
		unitSize=new Vector2f(0.045f,(float) (0.045f*DisplayManager.getAspectratio()));
		position=group.getPosition();
		super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		super.animation.setDelay(25);
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
	


	
	public GuiTexture render(Vector2f location) {
		
		switch(position){
		case 1:location=new Vector2f(location.x-0.025f+0.01f,location.y+0.04f);
		break;
		case 2:location=new Vector2f(location.x+0.025f+0.01f,location.y+0.04f);
		break;
		case 3:location=new Vector2f(location.x-0.025f+0.01f,location.y-0.04f);
		break;
		case 4:location=new Vector2f(location.x+0.025f+0.01f,location.y-0.04f);
		break;
		}
	
		
	switch(group.getDirection()){
	case 11:
		idle.setPosition(location);
		super.animation.resetCount();
		return idle;
	case 12:
		idle.setPosition(location);
		super.animation.resetCount();
		return idle;
	case 13:
		idle.setPosition(location);
		super.animation.resetCount();
		return idle;
	case 14:
		idle.setPosition(location);
		super.animation.resetCount();
		return idle;
	case 1:
		animation.setLoc(location);
		return animation.getFrame();
	
	case 2:animation.setLoc(location);
		return animation.getFrame();
	case 3:animation.setLoc(location);
	return animation.getFrame();
	case 4:animation.setLoc(location);
	return animation.getFrame();
	}
	return animation.getFrame();
	}

	public void rotate(int direction) {
		
	

		switch(group.getDirection()){
		case 11:super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerY,location,unitSize);
		break;
			
			

		case 12:super.idle=new GuiTexture(GuiLibrary.explorerStanding1,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		break;
			
		case 13:super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerY,location,unitSize);
		break;
			
		case 14:super.idle=new GuiTexture(GuiLibrary.explorerStanding1,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		break;
		case 1:super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerY,location,unitSize);
		break;
			
			

		case 2:super.idle=new GuiTexture(GuiLibrary.explorerStanding1,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		break;
			
		case 3:super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerY,location,unitSize);
		break;
			
		case 4:super.idle=new GuiTexture(GuiLibrary.explorerStanding1,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		break;
		
		}
		super.animation.setDelay(25);
		
		
	}
	
	
}
