package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class CursedIdolSI extends ShopItem{

	public CursedIdolSI(Vector2f location, Vector2f size) {
		guiTexture = new GuiTexture(GuiLibrary.idolOnBlank, location, size);
		id = 2;
		rotatable = false;
		cost = 200;
		name = "Cursed Idol";
	}
}
