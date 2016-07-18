package entities;

import java.util.ArrayList;

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
	protected Vector2f unitSize;
	protected ArrayList<Integer> animationx;
	protected ArrayList<Integer> animationy;
	protected int idlex;
	protected int idley;
	protected int lastDirection;
	protected int floor;
	protected int id;
	protected int damage;
	
	public Explorer(Group group) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(group);
		this.id = -1;
		this.lastDirection=2;
		this.group=group;
		unitSize=new Vector2f(0.045f,(float) (0.045f*DisplayManager.getAspectratio()));
		position=group.getPosition();
		super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		super.animation.setDelay(25);
		this.velocity = velocity;
		this.idley=GuiLibrary.explorerStanding;
		this.idlex=GuiLibrary.explorerStanding1;
		this.animationx=AnimationLibrary.explorerX;
		this.animationy=AnimationLibrary.explorerY;
		floor = Main.grids.get(0).getFloor();
		damage = 1;
		
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
	
	public int getId() {
		return id;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int d) {
		damage = d;
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

	protected void rotate(int direction) {
		switch(group.getDirection()){
		case 1:
			switch(lastDirection){
			case 1:
				switch(position){
				case 1:position=1;
				break;
				case 2:position=2;
				break;
				case 3:position=3;
				break;
				case 4:position=4;
				break;
				}
				
				
				break;
			case 2:
				switch(position){
				case 1:position=3;
				break;
				case 2:position=1;
				break;
				case 3:position=4;
				break;
				case 4:position=2;
				break;
				}
			
			
				break;
			case 3:
				switch(position){
				case 1:position=3;
				break;
				case 2:position=4;
				break;
				case 3:position=1;
				break;
				case 4:position=2;
				break;
				}
				
				
				
				break;
			case 4: 
				switch(position){
				case 1:position=2;
				break;
				case 2:position=4;
				break;
				case 3:position=1;
				break;
				case 4:position=3;
				break;
				}
				

				
				break;
		}
			
			
			
		super.idle=new GuiTexture(idley,location,unitSize);
		super.animation=new Animation(animationy,location,unitSize);
		break;
			
			

		case 2:
			switch(lastDirection){
			case 1:
				switch(position){
				case 1:position=2;
				break;
				case 2:position=4;
				break;
				case 3:position=1;
				break;
				case 4:position=3;
				break;
				}
				
				
				break;
			case 2:
				switch(position){
				case 1:position=1;
				break;
				case 2:position=2;
				break;
				case 3:position=3;
				break;
				case 4:position=4;
				break;
				}
			
			
				break;
			case 3:
				switch(position){
				case 1:position=3;
				break;
				case 2:position=1;
				break;
				case 3:position=4;
				break;
				case 4:position=2;
				break;
				}
				
				
				
				break;
			case 4: 
				switch(position){
				case 1:position=2;
				break;
				case 2:position=1;
				break;
				case 3:position=4;
				break;
				case 4:position=3;
				break;
				}
				
				
				
				break;
		}
		super.idle=new GuiTexture(idlex,location,unitSize);
		super.animation=new Animation(animationx,location,unitSize);
		break;
			
		case 3:
			switch(lastDirection){
			case 1:
				switch(position){
				case 1:position=3;
				break;
				case 2:position=4;
				break;
				case 3:position=1;
				break;
				case 4:position=2;
				break;
				}
				
				
				break;
			case 2:
				switch(position){
				case 1:position=2;
				break;
				case 2:position=4;
				break;
				case 3:position=1;
				break;
				case 4:position=3;
				break;
				}
			
			
				break;
			case 3:
				switch(position){
				case 1:position=1;
				break;
				case 2:position=2;
				break;
				case 3:position=3;
				break;
				case 4:position=4;
				break;
				}
				
				
				
				break;
			case 4: 
				switch(position){
				case 1:position=3;
				break;
				case 2:position=1;
				break;
				case 3:position=4;
				break;
				case 4:position=2;
				break;
				}
				
				
				
				break;
		}
			super.idle=new GuiTexture(idley,location,unitSize);
		super.animation=new Animation(animationy,location,unitSize);
		break;
			
		case 4:
			switch(lastDirection){
			case 1:
				switch(position){
				case 1:position=3;
				break;
				case 2:position=1;
				break;
				case 3:position=4;
				break;
				case 4:position=2;
				break;
				}
				
				
				break;
			case 2:
				switch(position){
				case 1:position=2;
				break;
				case 2:position=1;
				break;
				case 3:position=4;
				break;
				case 4:position=3;
				break;
				}
			
			
				break;
			case 3:
				switch(position){
				case 1:position=2;
				break;
				case 2:position=4;
				break;
				case 3:position=1;
				break;
				case 4:position=3;
				break;
				}
				
				
				
				break;
			case 4: 
				switch(position){
				case 1:position=1;
				break;
				case 2:position=2;
				break;
				case 3:position=3;
				break;
				case 4:position=4;
				break;
				}
				
				
				
				break;
		}
			super.idle=new GuiTexture(idlex,location,unitSize);
		super.animation=new Animation(animationx,location,unitSize);
		break;
		
		}
		super.animation.setDelay(25);
		lastDirection=direction;
		
	}
	protected void interact() {
setIdle();

		
	}

	public void setIdle() {
		switch(group.getDirection()){
		case 11:super.idle=new GuiTexture(idley,location,unitSize);
		super.animation=new Animation(animationy,location,unitSize);
		break;
			
			

		case 12:super.idle=new GuiTexture(idlex,location,unitSize);
		super.animation=new Animation(animationx,location,unitSize);
		break;
			
		case 13:super.idle=new GuiTexture(idley,location,unitSize);
		super.animation=new Animation(animationy,location,unitSize);
		break;
			
		case 14:super.idle=new GuiTexture(idlex,location,unitSize);
		super.animation=new Animation(animationx,location,unitSize);
		break;
		case 1:super.idle=new GuiTexture(idley,location,unitSize);
		super.animation=new Animation(animationy,location,unitSize);
		break;
			
			

		case 2:super.idle=new GuiTexture(idlex,location,unitSize);
		super.animation=new Animation(animationx,location,unitSize);
		break;
			
		case 3:super.idle=new GuiTexture(idley,location,unitSize);
		super.animation=new Animation(animationy,location,unitSize);
		break;
			
		case 4:super.idle=new GuiTexture(idlex,location,unitSize);
		super.animation=new Animation(animationx,location,unitSize);
		break;
		
		}
		super.animation.setDelay(25);
	}
	public int getPosition(){
		return position;
	}

	public void damage(int damageTaken) {
		super.hp-=damageTaken;
		if(hp<=0){
			super.kill=true;
		}
		
	}
	
	

}
