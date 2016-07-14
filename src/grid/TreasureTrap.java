package grid;

import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import entities.Group;
import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class TreasureTrap extends Tile{
	
	private int reward;
	
	private int round;
	
	public TreasureTrap(float size, int floor) {
		super(size, Main.grid.getLoc());
		this.floor = floor;
		this.guiTexture=(new GuiTexture(GuiLibrary.treasureClosed,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Treasue";
		setPrice(200);
		this.id = 4;
		reward = 100;
	}
	
	public TreasureTrap(int x, int y, float size, int floor) {
		super(x, y, size, Main.grid.getLoc());
		id = 4;
		round = Main.round;
		this.floor = floor;
		Main.grids.get(floor).getTreasureLocs().add(new Point(x,y));
		super.passable=false;
		super.canInteract=true;
		this.reward=100;
		this.guiTexture=(new GuiTexture(GuiLibrary.treasureClosed,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Treasue";
		setPrice(200);
	}
	
	@Override
	public void interact(Group g) {
		if(guiTexture.getTexture() == GuiLibrary.treasureClosed) {
			guiTexture = new GuiTexture(GuiLibrary.treasureOpen, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio()))) ;
		}
		
		if(reward<=0) {
			passable = true;
			canInteract = false;
			Main.grids.get(g.getFloor()).getTreasureLocs().remove(new Point(x, y));
			Blank blank = new Blank(super.x, super.y, super.size, Main.grid.getLoc());
			blank.setTrapRefs(trapRefs);
			Main.grids.get(g.getFloor()).setTile(super.x, super.y, blank);
		} else {
			reward --;
		}
	}
	
	public void getIncome() {
		Main.money += reward;
	}
	
	public void restore() {
		guiTexture = new GuiTexture(GuiLibrary.treasureClosed, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		reward = 100;
		Main.grids.get(floor).setTile(x, y, this);
	}
}
