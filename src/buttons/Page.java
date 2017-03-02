package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.StringLibrary;
import renderEngine.DisplayManager;

public class Page extends Linkable{
	
	protected Vector2f location; //Bottom Right
	
	protected Vector2f size; //All encapsulating

	protected String description; //AKA Summary of the item, written in centered makeItFitC style within the size brackets
	
	//protected String title; (Inherited) Goes at the top of the page as a header for the rest (Possibly bolded while others are not)
	
	protected Vector2f titleTextSize = new Vector2f(.02f, .04f);
	
	protected Vector2f descriptionTextSize = new Vector2f(.015f, .03f);
	
	protected float scrollDisp; //The displacement to move everything when scroll bar is added
	
	protected Vector2f imageSize;
	
	protected GuiTexture image; //If null, ignore it, otherwise its to show what is being talked about
	
	protected boolean isOn;
	
	public Page (Vector2f loc, Vector2f size, String title, String desc) {
		location = new Vector2f(loc.x, loc.y - 2 * StringLibrary.getSize().y);
		this.size = size;
		description = desc;
		this.title = title;
		scrollDisp = 0;
		isOn = false;
	}
	
	public Page (Vector2f loc, Vector2f size, String title, String desc, int imageId) {
		location = loc;
		this.size = size;
		description = desc;
		this.title = title;
		if(size.x*(float)DisplayManager.getAspectratio()<size.y)
			imageSize = new Vector2f(size.x/3, size.x/3*(float)DisplayManager.getAspectratio());
		else 
			imageSize = new Vector2f(size.y/(float)DisplayManager.getAspectratio()/3, size.y/3);
		this.image = new GuiTexture(imageId, new Vector2f(location.x+size.x/2, location.y+size.y-5*titleTextSize.y-imageSize.y/2), imageSize);
		scrollDisp = 0;
		isOn = false;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setOn(boolean b) {
		isOn = b;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		StringLibrary.setSize(titleTextSize);
		float edgeSpacing = (float) 60 / 11 * StringLibrary.getSize().x;
		dynamicGuis.addAll(StringLibrary.makeItFitC(title, new Vector2f(location.x, location.y+size.y-2*StringLibrary.getSize().y), size.x));
		StringLibrary.setSize(descriptionTextSize);
		if(image == null) {
			dynamicGuis.addAll(StringLibrary.makeItFitCInBoxWithScroll(description, new Vector2f(location.x + 2 * StringLibrary.getSize().x , 
					location.y-titleTextSize.y*2+size.y+scrollDisp -  2*StringLibrary.getSize().y), size.x - edgeSpacing * 3 / 4, size.y-titleTextSize.y*3/2, scrollDisp));
		} else {
			if(image.getPosition().y+image.getScale().y/2 < location.y+size.y)
				dynamicGuis.add(image);
			dynamicGuis.addAll(StringLibrary.makeItFitCInBoxWithScroll(description, 
					new Vector2f(location.x + 2 * StringLibrary.getSize().x, image.getPosition().y-image.getScale().y+scrollDisp), 
					size.x - edgeSpacing , size.y-titleTextSize.y*3/2-2*image.getScale().y, scrollDisp));
		}
	}
	
	public void scroll(float amount) {
		scrollDisp += amount;
		if(image != null) {
			image.setPosition(new Vector2f(image.getPosition().x, image.getPosition().y+amount));
		}
	}
}
