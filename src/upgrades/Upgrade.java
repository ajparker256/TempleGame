package upgrades;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.StringLibrary;

public class Upgrade {
	
	protected int numberOfEntries;
	
	protected GuiTexture rarityBorder;
	
	protected String explanation;
	
	public Upgrade(String explanation, int rarity) {
		numberOfEntries = rarity;
		rarityBorder = new GuiTexture(GuiLibrary.commonBackground, new Vector2f(-2,-2), new Vector2f(.05f,.05f));
		this.explanation = explanation;
	}
	
	public GuiTexture getTexture() {
		return rarityBorder;
	}
	
	public int getNumOfEntries() {
		return numberOfEntries;
	}
	
	public void setNumOfEntries(int i) {
		numberOfEntries = i;
	}
	
	public void setLoc(Vector2f loc) {
		rarityBorder.setPosition(loc);
	}
	
	public String toString() {
		return numberOfEntries+" entries. "+explanation;
	}
	
	public ArrayList<GuiTexture> render() {
		//Renders the rarity's background and displays its message on top
		ArrayList<GuiTexture> toBeRendered = new ArrayList<GuiTexture>();
		toBeRendered.add(rarityBorder);
		toBeRendered.addAll(StringLibrary.makeItFitC(explanation, new Vector2f(rarityBorder.getPosition().x-rarityBorder.getScale().x/2, rarityBorder.getPosition().y+rarityBorder.getScale().y/2), rarityBorder.getScale().x));
		return toBeRendered;
	}
	
	public void upgrade(Tile upgradingTrap) {
		//Stub
	}
}
