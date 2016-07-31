package buttons;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;

public class Page {
	
	protected Vector2f location; //Bottom Right
	
	protected Vector2f size; //All encapsulating

	String description; //AKA Summary of the item, written in centered makeItFitC style within the size brackets
	
	String title; //Goes at the top of the page as a header for the rest (Possibly bolded while others are not)
	
	GuiTexture image; //If null, ignore it, otherwise its to show what is being talked about
}
