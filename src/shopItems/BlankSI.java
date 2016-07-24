package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class BlankSI extends ShopItem{

	public BlankSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.blank, location, size);
		id = 0;
		rotatable = false;
		cost = -100;
		name = "Blank";
	}
}
