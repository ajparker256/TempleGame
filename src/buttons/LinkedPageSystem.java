package buttons;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

public class LinkedPageSystem {
	
	private HashMap<String, Linkable> screens;
	
	//TODO find a way of dealing with Menu's on one hand, but then only pages on the other.
	
	private Vector2f location; //Bottom Left
	
	private Vector2f size; //All encompassing
	
	private ArrayList<String> history; //Used for back button to go back to original linked page
	
	public LinkedPageSystem(Vector2f location, Vector2f size) {
		screens = new HashMap<String, Linkable>();
		history = new ArrayList<String>();
	}
	
	public void addNewPage(Page newPage) {
		screens.put(newPage.getTitle(), newPage);
	}
	
	public void addNewMenu(Menu newMenu) {
		screens.put(newMenu.getTitle(), newMenu);
	}
	
	public void addLinkToMenu(String titleOfMenu, Linkable link) {
		screens.get(titleOfMenu); //TODO add link to the menu, recalc locs and all that or maybe redraw the menu completely
	}
	
	

}
