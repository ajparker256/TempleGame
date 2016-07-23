package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class TikiSI extends ShopItem{

	public TikiSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.dirt0, location, size);
		id = 9;
		rotatable = true;
		cost = 300;
		name = "Tiki Trap";
	}
}
