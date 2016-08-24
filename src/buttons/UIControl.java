package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import librarys.ShopItemLibrary;
import shopItems.ShopItem;

public class UIControl {
	
	private Shop trapShop;
	private RotationDialogueBox rotationMenu;
	private LinkedPageSystem infoPages;
	private FloorSelect floorSelect;
	
	public UIControl() {
		trapShop = shopInit();
	}
	
	private Shop shopInit() {
		ShopItemLibrary.init();
		Vector2f maxSize = new Vector2f(.25f, .5f);
		Vector2f locationOfShop = new Vector2f(.5f, -.1f);
		return new Shop(locationOfShop, maxSize, populateShop());
	}
	
	private ShopItem[][] populateShop() {
		ShopItem[][] traps = new ShopItem[2][ShopItemLibrary.getItems().size()/2];
		ArrayList<ShopItem> items = ShopItemLibrary.getItems();
		int k = 0;
		for(int i = 0; i<traps.length; i++) {
			for(int j = 0; j<traps[0].length; j++) {
				traps[i][j] = items.get(k);
				k++;
			}
		}
		return traps;
	}

}
