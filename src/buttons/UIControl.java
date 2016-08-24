package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.PageLibrary;
import librarys.ShopItemLibrary;
import shopItems.ShopItem;
import upgrades.UpgradeRoller;

public class UIControl {
	
	private Shop trapShop;
	private RotationDialogueBox rotationMenu;
	private LinkedPageSystem infoPages;
	private FloorSelect floorSelect;
	private UpgradeRoller upgradeSelect;
	
	public UIControl() {
		trapShop = shopInit();
		infoPages = infoPageInit();
		floorSelect = floorSelectInit();
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
	
	private LinkedPageSystem infoPageInit() {
		Vector2f locationOfInfoPages = new Vector2f();
		Vector2f maxSizeOfInfoPages = new Vector2f();
		LinkedPageSystem infoPagesLocal = new LinkedPageSystem(locationOfInfoPages, maxSizeOfInfoPages);
		infoPagesLocal.populate((Menu)PageLibrary.categoriesMenu);
		return infoPagesLocal;
	}
	
	private FloorSelect floorSelectInit() {
		Vector2f locationOfBottomLeft = new Vector2f(-.4f, .8f);
		Vector2f totalSize = new Vector2f(.8f, .1f);
		FloorSelect floors = new FloorSelect(locationOfBottomLeft, totalSize);
		return floors;		
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		trapShop.render(dynamicGuis);
		infoPages.render(dynamicGuis);
		floorSelect.render(dynamicGuis);
	}
	
	public void doMouseEvents(float mouseX, float mouseY) {
		//TODO
	}

}
