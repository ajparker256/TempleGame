package buttons;

import java.util.ArrayList;

import gui.GuiTexture;

public abstract class Linkable {

	//Objects that extend this are able to be linked in the linked page system
	
	protected String title;
	
	public String getTitle() {
		return title;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		//Stub
	}
	
	public void addEntry(String s) {
		//Stub
	}
	
	public String getClickedName(float mouseX, float mouseY) {
		//Stub
		return null;
	}
	
	public boolean isMenuOptionClicked(float mouseX, float mouseY) {
		return false;
	}
	
}
