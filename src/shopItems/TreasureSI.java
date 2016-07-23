package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class TreasureSI extends ShopItem{
	
	public TreasureSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.treasureClosed, location, size);
		id = 4;
		rotatable = false;
		cost = 500;
		name = "Treasure";
	}

}
