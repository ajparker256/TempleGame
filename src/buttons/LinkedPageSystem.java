package buttons;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import renderEngine.DisplayManager;

public class LinkedPageSystem {
	
	private HashMap<String, Linkable> screens;
	
	//TODO find a way of dealing with Menu's on one hand, but then only pages on the other.
	
	private Vector2f location; //Bottom Left
	
	private Vector2f size; //All encompassing
	
	private GuiTexture background;
	
	private GuiTexture backIcon;
	
	private Button backButton;
	
	private ArrayList<String> history; //Used for back button to go back to original linked page
	
	private String currentScreenId;
	
	public LinkedPageSystem(Vector2f location, Vector2f size) {
		screens = new HashMap<String, Linkable>();
		history = new ArrayList<String>();
		this.location = location;
		this.size = size;
		background = new GuiTexture(0, location, size); //ADD BACKGROUND IMAGE FOR SHOP AND SUCH HERE!!!
		float iconSize = .05f;
		backIcon = new GuiTexture(0, new Vector2f(location.x+iconSize/2, location.y+size.y-iconSize/2*(float)DisplayManager.getAspectratio()), new Vector2f(iconSize, iconSize*(float)DisplayManager.getAspectratio()));
		backButton = new Button(new Vector2f(location.x, location.y+size.y), new Vector2f(location.x+backIcon.getScale().x*2, location.y+size.y-backIcon.getScale().y*2));
	}
	
	private void goBack() {
			currentScreenId = history.remove(history.size()-1);
	}
	
	private void backPressed(float mouseX, float mouseY) {
		if(backButton.isClicked(mouseX, mouseY) && !history.isEmpty());
			goBack();
	}
	
	public void addNewPage(Page newPage) {
		screens.put(newPage.getTitle(), newPage);
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		dynamicGuis.add(background);
		if(!history.isEmpty()) {
			dynamicGuis.add(backIcon);
		}
		screens.get(currentScreenId).render(dynamicGuis);
	}
	
	public void checkForMouseEvents(float mouseX, float mouseY) {
		backPressed(mouseX, mouseY);
		String newName = menuSelection(mouseX, mouseY);
		if(newName != null) {
			history.add(currentScreenId);
			currentScreenId = newName;
		}
	}
	
	public String menuSelection(float mouseX, float mouseY) {
		Linkable current = screens.get(currentScreenId);
		if(current.isMenuOptionClicked(mouseX, mouseY)) { //If it is a normal page, the menu option will return false
			return current.getClickedName(mouseX, mouseY);
		}
		System.out.println("Error in menu selection logic of LinkedPageSystem");
		return null;		
	}
	
	public void addNewPages(ArrayList<Page> newPages) {
		for(Page p : newPages) {
			addNewPage(p);
		}
	}
	
	public void addNewMenu(Menu newMenu) {
		screens.put(newMenu.getTitle(), newMenu);
	}
	
	public void addLinkToMenu(String titleOfMenu, String linkTitle) {
		screens.get(titleOfMenu).addEntry(linkTitle); //TODO add link to the menu, recalc locs and all that or maybe redraw the menu completely
	}
	
	

}
