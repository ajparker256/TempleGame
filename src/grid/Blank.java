package grid;

import gui.GuiTexture;
import librarys.GuiLibrary;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;

public class Blank extends Tile {
	//Location should be the grid's location!!!
	public Blank(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
		super.canInteract=false;
		super.passable=true;
		this.guiTexture=(new GuiTexture(GuiLibrary.blank,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		name = "Blank...";
		id = 0;
	}
}
