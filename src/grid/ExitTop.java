package grid;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class ExitTop extends Tile{

	public ExitTop(int x, int y, float size, int floor) {
		super(x,y,size, Main.grids.get(floor).getLoc(), floor);
		setOccupied(-3);
		this.guiTexture=(new GuiTexture(GuiLibrary.ladderTop,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));
		passable = true;
		canInteract = false;
		id = -3;
	}
	@Override
	public void damage(double damage) {
		//Invulnerable
	}
	
}
