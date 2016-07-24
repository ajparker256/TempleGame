package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class ExitSI extends ShopItem{
	
	public ExitSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.ladder, location, size);
		id = -2;
		rotatable = false;
		cost = 0;
		name = "Exit";
	}

}
