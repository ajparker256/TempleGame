package buttons;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;

public class PageChangeButton extends Button{
	
	//This is the page that this button takes the user to
	int page;
	
	public PageChangeButton(Vector2f TopLeft, Vector2f BottomRight, GuiTexture g, int i) {
		super(TopLeft, BottomRight, g);
		page = i;
	}
	
	public void getEvent() {
		//TODO Change the page to the given page int value.
	}
}
