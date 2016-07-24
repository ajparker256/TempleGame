package librarys;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import shopItems.*;

public class ShopItemLibrary {
	
	private static ArrayList<ShopItem> allItems = new ArrayList<ShopItem>();
	
	public static void init(Vector2f size) {
		allItems.add(new ArrowTrapSI(new Vector2f(-2,-2), size));
		allItems.add(new TikiSI(new Vector2f(-2,-2), size));
		allItems.add(new CursedIdolSI(new Vector2f(-2,-2), size));
		allItems.add(new TreasureSI(new Vector2f(-2,-2), size));
		allItems.add(new ExitSI(new Vector2f(-2,-2), size));
		//Filler
		if(allItems.size()%2 != 0) {
			allItems.add(new BlankSI(new Vector2f(-2,-2), size));
		}
	}
	
	public static ArrayList<ShopItem> getItems() {
		return allItems;
	}
}
