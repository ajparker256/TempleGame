package pathing;

import java.util.ArrayList;

import renderEngine.DisplayManager;

import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;

import java.awt.Point;

import grid.Grid;
import gui.GuiTexture;
import main.Main;

public class Group {

	//This is the group of explorers traveling together on a tile
	private ArrayList<Explorer> group;
	private ArrayList<Integer> groupIds;
	private ArrayList<PathModifier> collectedModifiers;
	//This is the room on a tile for members to exist (generally 2-4 explorers or 1 big thing like a drill)
	private static final int MAX_SIZE = 4;
	private Point nextLoc;
	private Vector2f location;
	private Vector2f velocity;
	private int nextPos;
	private int direction;
	private boolean busy;
	private boolean wait;
	private int squadId;
	private int floor;
	protected Point realLoc;
	protected float speed;
	private boolean flee;
	private float speedMod;
	private float speedBonus;
	
	public int getSquadId() {
		return squadId;
	}
	
	public Point getRealLoc() {
		return realLoc;
	}

	public void setRealLoc(Point realLoc) {
		this.realLoc = realLoc;
	}

	public boolean getWait() {
		return wait;
	}

	public Group(int id, Vector2f loc) {
		collectedModifiers = new ArrayList<PathModifier>();
		this.speedBonus=0;
		this.flee=false;
		this.speedMod=1f;
		location=new Vector2f(loc.x, loc.y);
		location.y-=0.1f;
		wait=false;
		busy=false;
		nextLoc=new Point(0,0);
		realLoc=nextLoc;
		nextPos=0;
		groupIds = new ArrayList<Integer>();
		speed=0.1f;
		velocity=new Vector2f(speed,(float)(speed*DisplayManager.getAspectratio()));
		group = new ArrayList<Explorer>();
		squadId = id;
		floor = 0; 
		setNextLoc(nextLoc);
	}
	


	public boolean move(long milli, Grid currentFloor) {
		boolean isCurrentlyInteracting = true;
		
		if(currentFloor.getTile(nextLoc.x, nextLoc.y).canInteract()){
			interact(currentFloor);
			return isCurrentlyInteracting;
		}
		System.out.println("WE DID IT FAM!");
		for(Explorer explorer:group){
			explorer.moveTo(currentFloor.getTile(nextLoc.x, nextLoc.y).getLocation(),milli);
		}
		currentFloor.getTile(realLoc.x, realLoc.y).trigger(nextLoc.x,nextLoc.y, currentFloor);
		if(currentFloor.getTile(nextLoc.x, nextLoc.y).getOccupied()>-2)
			currentFloor.getTile(nextLoc.x, nextLoc.y).setOccupied(squadId);
		realLoc=nextLoc;
		//destination is set so the group can start moving towards it
		Vector2f destination=currentFloor.getTile(nextLoc.x, nextLoc.y).getLocation();
		//Does movement if not at destination
		if(location.x != destination.x || location.y != destination.y){
			moveTowards(milli, currentFloor);
			return false;
		}
		//if done with everything resets busy to false so squad can move on
		busy=false;
		wait=true;
		
		return !isCurrentlyInteracting;
	}
	
	//Interacts with the tile with both the explorers and the group
	private void interact(Grid currentFloor){
		for(Explorer e: group){
			busy=true;
			e.interact();
		}
		 currentFloor.getTile(nextLoc.x, nextLoc.y).interact(this, currentFloor);
	}
	
	private void moveTowards(long milli, Grid grid){
		Vector2f destination=grid.getTile(nextLoc.x, nextLoc.y).getLocation();
		Vector2f tempVelocity= new Vector2f();
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
		//calls render on all the explorers
	public ArrayList<GuiTexture> render(int currentlyViewedFloor){
		ArrayList<GuiTexture> toRender = new ArrayList<GuiTexture>();
		for(Explorer explorer:group){
			if(currentlyViewedFloor ==  floor)
			toRender.add(explorer.render());
		}
		return toRender;
	}




//to hit is an array that gives what members of the squad to hit, d is damage
	public void damage(boolean[] toHit,double d) {
		for(Explorer explorer:group){
			if(toHit[0]&&explorer.getPosition()==1){
				explorer.damage(d);
			}
			if(toHit[1]&&explorer.getPosition()==2){
				explorer.damage(d);
			}
			if(toHit[2]&&explorer.getPosition()==3){
				explorer.damage(d);
			}
			if(toHit[3]&&explorer.getPosition()==4){
				explorer.damage(d);
			}
		}
		
	}

//changes direction variables based on current location and next location
	public void rotate(Point locationNext) {
		this.busy=false;
		
		if(realLoc.x<locationNext.x){
			direction=12;
		}else if(realLoc.x>locationNext.x){
			direction=14;
			
		}else if(realLoc.y<locationNext.y){
			direction=11;
		}else if(realLoc.y>locationNext.y){
			direction=13;
		}
			for(Explorer e: group){
				e.rotate(direction);
			}
		
	}
	public void setLoc(Vector2f location) {
		this.location=location;
		
	}
	public Vector2f getLocation(){
		return location;
	}
	public int getPosition() {
		nextPos++;
		return nextPos;
	}
	public int getDirection() {
		return direction;
	}

	public boolean isBusy() {
		if(this.busy){
			return true;
		}
		for(Explorer explorer:group){
			if(explorer.isBusy()){
				return true;
			}
		}
		return false;
	}

	public void setWait(boolean wait) {
		this.wait=wait;
		
	}
	
	public boolean getFlee() {
		return flee;
	}

	public void setNextLoc(Point point) {

			this.nextLoc=point;
	}
	public void setIdle(){
		for(Explorer explorer: group){
			explorer.setIdle();
		}
	}
	public void removeExplorer(Explorer explorer) {
		group.remove(explorer);
		groupIds.remove((Integer)explorer.getId());
	}
	public void setFloor(int i) {
		floor = i;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getMaxSize() {
		return MAX_SIZE;
	}
	
	public ArrayList<Explorer> getGroup() {
		return group;
	}
	
	public void add(Explorer e) {
		group.add(e);
		groupIds.add(e.getId());
	}
	
	public Point getNextLoc() {
		return nextLoc;
	}
	
	public ArrayList<Integer> getIds() {
		return groupIds;
	}
	public void setSpeed(float speed){
		this.speed=speed;
		this.velocity=new Vector2f(speed,(float)(speed*DisplayManager.getAspectratio()));
	}

	public void setFlee(boolean flee) {
		this.flee=flee;
		
	}
	
	public void addPathModifier(PathModifier pm) {
		collectedModifiers.add(pm);
	}
	
	public ArrayList<PathModifier> collectPathModifiers() {
		ArrayList<PathModifier> modifiers = new ArrayList<PathModifier>();
		modifiers.addAll(collectedModifiers);
		collectedModifiers.clear();
		return modifiers;
	}


	
}
