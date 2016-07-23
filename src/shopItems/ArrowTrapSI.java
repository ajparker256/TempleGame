package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class ArrowTrapSI extends ShopItem{

	public ArrowTrapSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.arrowTrap1, location, size);
		id = 5;
		rotatable = true;
		cost = 300;
		name = "Arrow Trap";
	}
}
