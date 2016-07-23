package shopItems;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;

public class ShopItem {
	
	protected String name;
	protected int id;
	protected GuiTexture guiTexture;
	protected int cost;
	protected boolean rotatable;
	
	public ShopItem() {
	}
	
	public GuiTexture drawTile() {
		return guiTexture;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return name;
	}

}
