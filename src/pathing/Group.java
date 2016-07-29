package pathing;

import java.util.ArrayList;

import main.Main;
import renderEngine.DisplayManager;

import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;

import java.awt.Point;

import grid.Grid;
import grid.Tile;
import gui.GuiTexture;

public class Group {

	//This is the group of explorers traveling together on a tile
	private ArrayList<Explorer> group;
	private ArrayList<Integer> groupIds;
	//This is the room on a tile for members to exist (generally 2-4 explorers or 1 big thing like a drill)
	private final int MAX_SIZE;
	private Point nextLoc;
	private Vector2f location;
	private Vector2f velocity;
	private int nextPos;
	private int direction;
	private ArrayList<Point> path;
	private boolean busy;
	private boolean wait;
	private int squadId;
	private int floor;
	protected Point realLoc;
	protected float speed;
	private boolean flee;
	private float speedMod;
	
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

	public Group(int id) {
		this.flee=false;
		this.speedMod=1f;
		location=new Vector2f(Main.grid.getTile(0,0).getLocation().x,Main.grid.getTile(0,0).getLocation().y);
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
		MAX_SIZE = 4;
		path = new ArrayList<Point>();
		squadId = id;
		floor = Main.grid.getFloor();
		setNextLoc(nextLoc);
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
	
	public void setInitialLocation(Vector2f newLoc) {
		location = newLoc;
	}
	
	public Vector2f getInitialLocation() {
		return location;
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

	public boolean move(int milli,Grid grid) {
		if(grid.getTile(nextLoc.x, nextLoc.y).canInteract()){
			interact(grid);
			return true;
		}
		for(Explorer explorer:group){
			explorer.moveTo(nextLoc,milli);
		}
		Main.grids.get(floor).getTile(realLoc.x, realLoc.y).trigger(nextLoc.x,nextLoc.y);
		grid.getTile(nextLoc.x, nextLoc.y).setOccupied(squadId);
		realLoc=nextLoc;
		Vector2f destination=grid.getTile(nextLoc.x, nextLoc.y).getLocation();
		if(!(location.x==destination.x&&location.y==destination.y)){
			moveTo(grid, milli);
			return false;
		}
			busy=false;
			wait=true;
		return false;
		}
	private void interact(Grid grid){
		for(Explorer e: group){
			busy=true;
			e.interact();
		}
		 grid.getTile(nextLoc.x, nextLoc.y).interact(this);
	}
	private void moveTo(Grid grid, int milli){
		Vector2f destination=grid.getTile(nextLoc.x, nextLoc.y).getLocation();
		Vector2f tempVelocity= new Vector2f();
		if(flee){
			speedMod=2f;
		}else{
			speedMod=1f;
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

		

	private void setLoc(Vector2f location) {
		this.location=location;
		
	}
	public Vector2f getLocation(){
		return location;
	}
	public ArrayList<GuiTexture> render(){
		ArrayList<GuiTexture> toRender = new ArrayList<GuiTexture>();
		for(Explorer explorer:group){
			if(this.floor == Main.grid.getFloor())
			toRender.add(explorer.render());
		}
		return toRender;
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

	public void removeExplorer(Explorer explorer) {
		group.remove(explorer);
		groupIds.remove((Integer)explorer.getId());
	}
	public void setSpeed(float speed){
		this.speed=speed;
		this.velocity=new Vector2f(speed,(float)(speed*DisplayManager.getAspectratio()));
	}

	public void setFlee(boolean flee) {
		this.flee=flee;
		
	}

	public void rotate(Point point) {
		System.out.println(point);
		this.busy=false;
		Vector2f locationNext=Main.grid.getTile(point.x, point.y).getLocation();
		if(location.x<locationNext.x){
			direction=12;
		}else if(location.x>locationNext.x){
			direction=14;
			
		}else if(location.y<locationNext.y){
			direction=11;
		}else if(location.y>locationNext.y){
			direction=13;
		}
		int i=0;
			for(Explorer e: group){
				e.rotate(direction);
				i++;
			}
		
	}

	
}
