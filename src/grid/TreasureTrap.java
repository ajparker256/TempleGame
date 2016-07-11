package grid;

import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import entities.Group;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.TextureLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class TreasureTrap extends Tile{
	
	private int reward;
	
	public TreasureTrap(int x, int y, float size, int floor) {
		super(x, y, size, Main.grid.getLoc());
		id = 4;
		Main.grids.get(floor).getTreasureLocs().add(new Point(x,y));
		super.passable=false;
		super.canInteract=true;
		this.reward=100;
		this.guiTexture=(new GuiTexture(GuiLibrary.treasureClosed,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Treasure";
		setPrice(200);
	}
	
	@Override
	public void interact(Group g) {
		if(guiTexture.getTexture() == GuiLibrary.treasureClosed) {
			guiTexture = new GuiTexture(GuiLibrary.treasureOpen, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio()))) ;
		}
		reward--;
		if(reward<=0) {
			passable = true;
			canInteract = false;
			Main.grids.get(g.getFloor()).getTreasureLocs().remove(new Point(x, y));
			Main.grids.get(g.getFloor()).setTile(x, y, new Blank(x,y,size, Main.grid.getLoc()));
		}
	}
}
