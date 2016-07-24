package shopItems;

import gui.GuiTexture;

public class ShopItem implements Comparable<ShopItem>{
	
	protected String name;
	protected int id;
	protected int image;
	protected GuiTexture guiTexture;
	protected int cost;
	protected boolean rotatable;
	
	public ShopItem() {
	}
	
	public int compareTo() {
		return 0;
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

	@Override
	public int compareTo(ShopItem other) {
		return this.cost-other.getCost();
	}

}
