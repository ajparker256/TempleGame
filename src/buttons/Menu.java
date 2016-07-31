package buttons;

import org.lwjgl.util.vector.Vector2f;

import librarys.StringLibrary;

public class Menu {

	protected Button[] options; //These are the hitboxes for the menu
	
	protected Vector2f location; //This is the bottom right loc.
	
	protected Vector2f size; //This is the all incapsulating size, nothing exceeds this

	protected String[] menuLabels; //These are the words that go on the menu
	
	protected Vector2f textSize = new Vector2f(.02f, .04f); //Text size used for the menu options, varies with specifics
	
	boolean isVertical;
	
	public Menu(Vector2f loc, Vector2f size, String[] menuLabels) {
		location = loc;
		this.size = size;
		this.menuLabels = menuLabels;
		isVertical = true;
		setHitboxes();
	}
	
	public void setHitboxes() {
		options = new Button[menuLabels.length];
		if(isVertical)
			for(int i = 0; i<menuLabels.length; i++) {
				options[i] = new Button(new Vector2f(location.x+size.x/menuLabels.length/2-StringLibrary.getWidth(menuLabels[i])/2,
													 location.y+i*size.y/menuLabels.length+size.y/menuLabels.length/2+StringLibrary.getSize().y/2),
										new Vector2f(location.x+size.x/menuLabels.length/2+StringLibrary.getWidth(menuLabels[i])/2,
													 location.y+i*size.y/menuLabels.length+size.y/menuLabels.length/2-StringLibrary.getSize().y/2));
			}
		else
			for(int i = 0; i<menuLabels.length; i++) {
				options[i] = new Button(new Vector2f(location.x+i*size.x/menuLabels.length+size.x/menuLabels.length-StringLibrary.getWidth(menuLabels[i])/2,
						 							 location.y+size.y/menuLabels.length/2+StringLibrary.getSize().y/2),
										new Vector2f(location.x+i*size.x/menuLabels.length+size.x/menuLabels.length+StringLibrary.getWidth(menuLabels[i])/2,
													 location.y+size.y/menuLabels.length/2-StringLibrary.getSize().y/2));
			}
	}
	
	public boolean isMenuOptionClicked(float mouseX, float mouseY) {
		for(Button b : options) {
			if(b.isClicked(mouseX, mouseY))
				return true;
		}
		return false;
	}
	
	public String getClickedName(float mouseX, float mouseY) {
		int i = 0;
		for(Button b : options) {
			if(b.isClicked(mouseX, mouseY))
				return menuLabels[i];
			i++;
		}
		return null;
	}
}
