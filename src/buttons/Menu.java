package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import gui.GuiTexture;

import librarys.StringLibrary;
import renderEngine.DisplayManager;

public class Menu extends Linkable{

	protected ArrayList<Button> options; //These are the hitboxes for the menu
	
	protected Vector2f location; //This is the bottom right loc.
	
	protected Vector2f size; //This is the all incapsulating size, nothing exceeds this

	//PRECONDITION: LABELS.WIDTH < SIZE.X!!!
	protected ArrayList<String> menuLabels; //These are the words that go on the menu, added to list via name fields of referenced pages
	
	protected Vector2f textSize = new Vector2f(.02f, .04f); //Text size used for the menu options, varies with specifics
	
	protected boolean isVertical;
	
	protected boolean isOn;
	
	//protected String title; inherited
	
	public Menu(Vector2f loc, Vector2f size, String[] menuLabels, String title) {
		location = loc;
		this.size = size;
		this.title = title;
		this.options = new ArrayList<Button>();
		this.menuLabels = new ArrayList<String>();
		for(String s : menuLabels) {
			this.menuLabels.add(s);
			System.out.println(s);
		}
		isVertical = size.y>size.x*DisplayManager.getAspectratio();
		setHitboxes();
		isOn = false;
	}
	
	public void addEntry(String newEntry) {
		menuLabels.add(newEntry);
		setHitboxes();
	}
	
	private void setHitboxes() {
		StringLibrary.setSize(textSize);
		if(isVertical)
			for(int i = 0; i<menuLabels.size(); i++) {
				options.add(new Button(new Vector2f(location.x+size.x/menuLabels.size()/2-StringLibrary.getWidth(menuLabels.get(i))/2,
													 location.y+i*size.y/menuLabels.size()+size.y/menuLabels.size()/2+StringLibrary.getSize().y/2),
										new Vector2f(location.x+size.x/menuLabels.size()/2+StringLibrary.getWidth(menuLabels.get(i))/2,
													 location.y+i*size.y/menuLabels.size()+size.y/menuLabels.size()/2-StringLibrary.getSize().y/2)));
			}
		
		/*else
			for(int i = 0; i<menuLabels.size(); i++) {
				options.add(new Button(new Vector2f(location.x+i*size.x/menuLabels.size()+size.x/menuLabels.size()-StringLibrary.getWidth(menuLabels.get(i))/2,
						 							 location.y+size.y/menuLabels.size()/2+StringLibrary.getSize().y/2),
										new Vector2f(location.x+i*size.x/menuLabels.size()+size.x/menuLabels.size()+StringLibrary.getWidth(menuLabels.get(i))/2,
													 location.y+size.y/menuLabels.size()/2-StringLibrary.getSize().y/2)));
			}*/
	}
	
	public void setOn(boolean b) {
		isOn = b;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	@Override
	public boolean isMenuOptionClicked(float mouseX, float mouseY) {
		for(Button b : options) {
			if(b.isClicked(mouseX, mouseY))
				return true;
		}
		return false;
	}
	
	public ArrayList<String> getEntries() {
		return menuLabels;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		StringLibrary.setSize(textSize);
		dynamicGuis.addAll(StringLibrary.makeItFitC(title, new Vector2f(location.x, location.y+size.y), size.x));
		if(isVertical)
		for(int i = 0; i<menuLabels.size(); i++) {
			dynamicGuis.addAll(StringLibrary.makeItFitC(menuLabels.get(i), new Vector2f(location.x,
					 location.y-StringLibrary.getSize().y+i*size.y/menuLabels.size()+size.y/menuLabels.size()/2+StringLibrary.getSize().y/2), size.x));
		}
		else
		for(int i = 0; i<menuLabels.size(); i++) {
			dynamicGuis.addAll(StringLibrary.makeItFitC(menuLabels.get(i), new Vector2f(location.x+i*size.x/menuLabels.size()+size.x/menuLabels.size()/2-StringLibrary.getWidth(menuLabels.get(i))/2,
					 location.y+size.y/menuLabels.size()/2+StringLibrary.getSize().y/2), size.x));
		}
	}
	
	@Override
	//Returns the name of the menu item clicked, so references the name field of the objects linked
	public String getClickedName(float mouseX, float mouseY) {
		int i = 0;
		for(Button b : options) {
			if(b.isClicked(mouseX, mouseY))
				return menuLabels.get(i);
			i++;
		}
		return null;
	}
}
