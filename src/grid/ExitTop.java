package grid;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class ExitTop extends Tile{

	public ExitTop(int x, int y, float size) {
		super(x,y,size, Main.grid.getLoc());
		this.guiTexture=(new GuiTexture(GuiLibrary.ladderTop,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));
		passable = false;
		canInteract = false;
		id = -3;
	}
	
}
