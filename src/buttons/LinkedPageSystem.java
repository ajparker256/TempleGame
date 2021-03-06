package buttons;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.PageLibrary;
import renderEngine.DisplayManager;

public class LinkedPageSystem {
	
	private HashMap<String, Linkable> screens;
	
	private Vector2f location; //Bottom Left
	
	private Vector2f size; //All encompassing
	
	private GuiTexture background;
	
	private GuiTexture backIcon;
	
	private Button backButton;
	
	private ArrayList<String> history; //Used for back button to go back to original linked page
	
	private String currentScreenId;
	
	private double lastInteractionTime;
	
	private double interactionDelay;
	
	public LinkedPageSystem(Vector2f location, Vector2f size) {
		screens = new HashMap<String, Linkable>();
		history = new ArrayList<String>();
		this.location = location;
		this.size = size;
		background = new GuiTexture(GuiLibrary.blankBackground, new Vector2f(location.x + size.x/2, location.y+size.y/2), new Vector2f(size.x/2, size.y/2)); //ADD BACKGROUND IMAGE FOR SHOP AND SUCH HERE!!!
		float iconSize = .02f;
		backIcon = new GuiTexture(GuiLibrary.ladderTop, new Vector2f(location.x+iconSize, location.y+size.y-iconSize*(float)DisplayManager.getAspectratio()), new Vector2f(iconSize, iconSize*(float)DisplayManager.getAspectratio()));
		backButton = new Button(new Vector2f(location.x, location.y+size.y), new Vector2f(location.x+backIcon.getScale().x*2, location.y+size.y-backIcon.getScale().y*2));
		lastInteractionTime = 0;
		interactionDelay = 200;
	
	}
	
	public void setCurrentScreenId(String name) {
		currentScreenId = name;
	}
	
	
	
	private void backPressed(float mouseX, float mouseY) {
		if(backButton.isClicked(mouseX, mouseY) && !history.isEmpty()) {
			currentScreenId = history.remove(history.size()-1);
		}
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
		if(lastInteractionTime + interactionDelay <= System.currentTimeMillis()) {
			backPressed(mouseX, mouseY);
			if(screens.get(currentScreenId).isMenuOptionClicked(mouseX, mouseY)) {
				String newName = menuSelection(mouseX, mouseY);
				if(newName != null) {
					history.add(currentScreenId);
					currentScreenId = newName;
				}
			}
			lastInteractionTime = System.currentTimeMillis();
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
	
	public void addNewMenu(Menu newMenu) {
		screens.put(newMenu.getTitle(), newMenu);
	}
	
	public void addLinkToMenu(String titleOfMenu, String linkTitle) {
		screens.get(titleOfMenu).addEntry(linkTitle);
	}
	
	public void populate(Menu initialMenu) {
		ArrayList<String> derivativeLinks = new ArrayList<String>();
		for(String s : initialMenu.getEntries()){
			derivativeLinks.add(s);
		}
		addNewMenu((Menu)initialMenu);
		setCurrentScreenId(initialMenu.getTitle());
		ArrayList<String> moreLinks = new ArrayList<String>();
		while(!derivativeLinks.isEmpty()) {
			for(String key : derivativeLinks) {
				if(PageLibrary.getLinkable(key) instanceof Menu) {
					ArrayList<String> links = ((Menu) PageLibrary.getLinkable(key)).getEntries();
					for(String link : links) {
						if(!derivativeLinks.contains(link)) {
							moreLinks.add(link);
						}
					}
					addNewMenu((Menu)PageLibrary.getLinkable(key));
				} else if(PageLibrary.getLinkable(key) instanceof Page) {
					addNewPage((Page)PageLibrary.getLinkable(key));
				} else {
					System.out.println("ERROR IN POPULATION OF LEFT MENU");
				}
			}
			derivativeLinks.clear();
			derivativeLinks.addAll(moreLinks);
			moreLinks.clear();
		}
	}
	
	

}
