package librarys;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;
import shopItems.*;

public class ShopItemLibrary {
	
	private static ArrayList<ShopItem> allItems = new ArrayList<ShopItem>();
	
	public static Vector2f size = new Vector2f(.03f, .03f*(float)DisplayManager.getAspectratio());
	
	public static void init() {
		allItems.clear();
		allItems.add(new ArrowTrapSI(new Vector2f(-2,-2), size));
		allItems.add(new TikiSI(new Vector2f(-2,-2), size));
		allItems.add(new CursedIdolSI(new Vector2f(-2,-2), size));
		allItems.add(new TreasureSI(new Vector2f(-2,-2), size));
		allItems.add(new ExitSI(new Vector2f(-2,-2), size));
		allItems.add(new ArrowPatrolSI(new Vector2f(-2,-2), size));
		allItems.add(new BlankSI(new Vector2f(-2, -2), size));
		//Filler
		if(allItems.size()%2 != 0) {
			allItems.add(new BlankSI(new Vector2f(-2,-2), size));
		}
	}
	
	public static ShopItem getItem(int id) {
		if(id == -2) 
			return new ExitSI(new Vector2f(-2,-2), size);
		if(id == 0) 
			return new BlankSI(new Vector2f(-2,-2), size);
		if(id == 2)
			return new CursedIdolSI(new Vector2f(-2,-2), size);
		if(id == 4)
			return new TreasureSI(new Vector2f(-2,-2), size);
		if(id == 5) 
			return new ArrowTrapSI(new Vector2f(-2,-2), size);
		if(id == 9)
			return new TikiSI(new Vector2f(-2,-2), size);
		if(id == 10)
			return new ArrowPatrolSI(new Vector2f(-2,-2), size);
		return null;
	}
	
	public static ArrayList<ShopItem> getItems() {
		return allItems;
	}
}
