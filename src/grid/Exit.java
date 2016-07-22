package grid;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import pathing.Group;
import renderEngine.DisplayManager;

public class Exit extends Tile{
	
	public Exit(int x, int y, float size) {
		super(x, y, size, Main.grid.getLoc());
		this.guiTexture=(new GuiTexture(GuiLibrary.ladder,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));
		passable = true;
		canInteract = true;
		name = "Exit!";
		id = -2;
	}
	
	public void interact(Group g) {
		g.setFloor(g.getFloor()+1);
		Main.grids.get(g.getFloor()).setTile(x, y, new ExitTop(x, y, size));
	}
}
