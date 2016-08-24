package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class ArrowPatrolSI extends ShopItem{

	public ArrowPatrolSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.arrowTrap1, location, size);
		id = 10;
		rotatable = false;
		cost = 0;
		name = "Arrow Patrol";
	}
}
