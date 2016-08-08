package explorerTypes;

import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import entities.Unit;
import grid.Grid;
import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import pathing.Group;
import renderEngine.DisplayManager;

public class Explorer extends Unit{
	private int position;
	private Group group;
	protected Vector2f unitSize;
	protected int idlex;
	protected int idley;
	protected int lastDirection;
	protected int floor;
	protected int id;
	protected int damage;
	protected int delay;
	protected int direction;
	private float speedMod;
	private boolean busy;
	private boolean flee;
	private float speedBonus;
	protected float providedSpeedBonus;
	
	public Explorer(Group group) {
		//Hit points, location in Pixels, Velocity in Pixels, Size relative to screen, id to recognize later, an identity code
		super(group);
		this.providedSpeedBonus=0;
		this.speedBonus=0;
		this.delay=25;
		this.id = -1;
		this.lastDirection=1;
		this.group=group;
		unitSize=new Vector2f(0.045f,(float) (0.045f*DisplayManager.getAspectratio()));
		position=group.getPosition();
		super.idle=new GuiTexture(GuiLibrary.test1,location,unitSize);
		super.animation=new Animation(AnimationLibrary.test,location,unitSize);
		super.animation.setDelay(delay);
		floor = Main.grids.get(0).getFloor();
		damage = 1;
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
	
	public void setFloor(int i) {
		floor = i;
	}
	
	public int getFloor() {
		return floor;
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

	
	public GuiTexture render() {

	super.animation.setIfFrozen(Main.state == 2); //If the game is paused, freeze the animation for walking

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
		switch(group.getDirection()%10){
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
			
			
			
		super.idle.setRotation(0);
		super.animation.setRotation(0);
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
			super.idle.setRotation(90);
			super.animation.setRotation(90);
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
			super.idle.setRotation(180);
			super.animation.setRotation(180);
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
			super.idle.setRotation(270);
			super.animation.setRotation(270);
		break;
		
		}
		super.animation.setDelay(delay);
		lastDirection=direction%10;
		
	}
	public void interact() {
		setIdle();
	}

	public void setIdle() {
		switch(group.getDirection()){
		case 11:	super.idle.setRotation(0);
		super.animation.setRotation(0);
		break;
			
			

		case 12:super.idle.setRotation(90);
		super.animation.setRotation(90);
		break;
			
		case 13:super.idle.setRotation(180);
		super.animation.setRotation(180);
		break;
			
		case 14:super.idle.setRotation(270);
		super.animation.setRotation(270);
		break;
		case 1:super.idle.setRotation(0);
		super.animation.setRotation(0);
		break;
			
			
		case 2:super.idle.setRotation(90);
		super.animation.setRotation(90);
		break;
			
		case 3:super.idle.setRotation(180);
		super.animation.setRotation(180);
		break;
			
		case 4:super.idle.setRotation(270);
		super.animation.setRotation(270);
		break;
		
		}
		super.animation.setDelay(delay);
	}
	public int getPosition(){
		return position;
	}

	public void damage(double d) {
		super.hp-=d;
		if(hp<=0){
			super.kill=true;
		}
		
	}

	public void setDelay(int delay) {
		this.delay=delay;
		animation.setDelay(delay);
		
	}


	public void moveTo(Point nextLoc, int milli){
		Vector2f destination=Main.grids.get(floor).getTile(nextLoc.x, nextLoc.y).getLocation();
		Vector2f tempVelocity= new Vector2f();
		switch(position){
		case 1:destination=new Vector2f(destination.x-0.025f+0.01f,destination.y+0.04f);
		break;
		case 2:destination=new Vector2f(destination.x+0.025f+0.01f,destination.y+0.04f);
		break;
		case 3:destination=new Vector2f(destination.x-0.025f+0.01f,destination.y-0.04f);
		break;
		case 4:destination=new Vector2f(destination.x+0.025f+0.01f,destination.y-0.04f);
		break;
		}

		speedMod=1f;
		speedMod+=speedBonus;
		if(flee){
			speedMod*=2f;
		}
		Vector2f modVelocity=new Vector2f(velocity.x*speedMod,velocity.y*speedMod);
		if(location.x<destination.x){
			direction=2;
			busy=true;
			tempVelocity.x = modVelocity.x;
			setLoc(new Vector2f(location.x+tempVelocity.x*milli/1000f, location.y));
			//If you would overshoot, don't
			if(location.x>destination.x){
				busy=false;
				location.x=destination.x;
				direction=12;
			}
		//If the destination is to the left, go left
		}else if(location.x>destination.x){
			direction=4;
			busy=true;
			tempVelocity = new Vector2f(modVelocity.x*-1, 0);
			setLoc(new Vector2f(location.x+tempVelocity.x*milli/1000f, location.y));
			if(location.x<destination.x){
				busy=false;
				location.x=destination.x;
				direction=14;
				
			}
		//If the destination is above you, go up
		}else if(location.y<destination.y){
			direction=1;
			busy=true;
			tempVelocity.y = modVelocity.y;
			setLoc(new Vector2f(location.x, location.y+(tempVelocity.y*milli/1000f)));
			if(location.y>destination.y){
				busy=false;
				location.y=destination.y;
				direction=11;
			}
			
		//If the destination is below you, go down
		}else if(location.y>destination.y){
			direction=3;
			busy=true;
			tempVelocity.y = -1*modVelocity.y;
			setLoc(new Vector2f(location.x, location.y+(tempVelocity.y*milli/1000f)));
			if(location.y<destination.y){
				busy=false;
				location.y=destination.y;
				direction=13;
			}
		}


	}

	public boolean isBusy() {
		return busy;
	}

	public float getSpeedProvided() {
		return providedSpeedBonus;
	}

	public void addSpeedBonus(float speedBonus) {
		this.speedBonus+=speedBonus;
		
	}
	
	

}
