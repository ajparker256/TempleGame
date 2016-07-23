package grid;

import gui.Animation;
import gui.GuiTexture;

import java.awt.Point;
import java.util.ArrayList;

import main.Main;
import pathing.Group;

import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;
import renderEngine.DisplayManager;

// This is the superclass for all tiles contained in a grid

public class Tile {
	
	//the location of this tile in the grid, stores column then row (x then y)
	//the units contained within this tile
	public boolean passable;
	protected int x;
	protected int y;
	protected Vector2f position;
	protected int texture;
	protected GuiTexture guiTexture;
	protected String name;
	private int price;
	protected float size;
	protected Vector2f location;
	protected boolean canInteract;
	protected int id;
	protected int occupied;
	protected int floor;
	protected ArrayList<Point> trapRefs;
	protected int hp;
	protected boolean rotatable;
	protected Animation animation;
	
	
	public Tile(float size, Vector2f location) {
		this.canInteract = false;
		this.location = location;
		this.size = size;
		this.hp=100;
		trapRefs=new ArrayList<Point>();
		name = "Default_Name";
		price = -1;
		id = -1;
		occupied = -1;
	}
	
	//creates a tile in location loc, give location in column then row
	public Tile(int x, int y, float size, Vector2f location){
		this.hp=100;
		this.canInteract=false;
		this.location=location;
		trapRefs=new ArrayList<Point>();
		this.size=size;
		this.x=x;
		this.y=y;
		this.position=new Vector2f (location.x+((x-size/2)*size*2),(float) (location.y+((y-size/2)*(size*2*DisplayManager.getAspectratio()))));
		name = "Default_Name";
		//Should be overwritten in every case below this point
		price = -1;
		id = -1;
		occupied = -1;
	}
	
	public ArrayList<Point> getTrapRefs() {
		return trapRefs;
	}
	
	public void setTrapRefs(ArrayList<Point> newRefs) {
		trapRefs = newRefs;
	}
	
	public void upgrade(int i) {
		//STUB
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public boolean isRotatable() {
		return rotatable;
	}
	
	public void setRotatable(boolean b) {
		rotatable = b;
	}
	
	public void getIncome() {
		//STUB
	}
	
	public int getId() {
		return id;
	}
	
	public int isOccupied() {
		return occupied;
	}
	
	public void setOccupied(int i) {
		occupied = i;
	}
	
	public GuiTexture drawTile(){
		if(this.animation!=null){
			return animation.getFrame();
		}
		return guiTexture;

	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int newPrice) {
		price = newPrice;
	}
	public Vector2f getLocation(){
		return position;
	}
	
	public void setLocation(Vector2f loc) {
		location = loc;
		this.position=new Vector2f (location.x+((x-size/2)*size*2),(float) (location.y+((y-size/2)*(size*2*DisplayManager.getAspectratio()))));
		guiTexture.setPosition(position);
	}

	public void setSize(float size) {
		this.size = size;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Vector2f getPosition()  {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Tile copy() {
		Tile t = new Tile(x, y, size, location);
		t.setTrapRefs(trapRefs);
		return t;
	}

	public boolean canInteract() {
		
		return canInteract;
	}
	public float getSize(){
		return size;
	}


	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public String toString() {
		return name;
	}
	public void trigger(int x, int y){
		for(Point point:trapRefs){
			Main.grids.get(floor).getTile(point.x,point.y).whenTriggered(new Point(this.x, this.y));
		}
	}

	protected void whenTriggered(Point p) {

	}
	public void addTrapRef(Point point){
		trapRefs.add(point);
	}

	public void tick(long milli) {
		
	}
	
	public void interact(Group g){
		int bonusDamage = 0;
		for(Explorer e : g.getGroup()) {
			if(e.getId() == 2) {
				bonusDamage += 5*e.getDamage();
			} else {
				bonusDamage += e.getDamage();
			}
		}
		hp-=(bonusDamage);
		if(hp<=0){
			Blank blank = new Blank(this.x, this.y, this.size, Main.grid.getLoc());
			blank.setTrapRefs(trapRefs);
			Main.grids.get(g.getFloor()).setTile(this.x, this.y, blank);
		}
	}

	public boolean isPassable() {
		return passable;
	}




}
