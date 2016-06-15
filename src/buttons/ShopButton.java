package buttons;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.TrapLibrary;

public class ShopButton extends Button{

	//This references the trap that this button is holding in the shop. Accessed via the TrapLibrary Class.
	private static int id;
	
	public ShopButton(Vector2f TopLeft, Vector2f BottomRight, GuiTexture g, int id) {
		super(TopLeft, BottomRight, g);
		this.id = id;
	}
	
}
