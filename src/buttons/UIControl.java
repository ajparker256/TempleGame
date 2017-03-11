package buttons;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import grid.SandTemple;
import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.PageLibrary;
import librarys.ShopItemLibrary;
import librarys.StringLibrary;
import librarys.TileLibrary;
import renderEngine.DisplayManager;
import shopItems.ShopItem;
import traps.Trap;
import upgrades.UpgradeRoller;

public class UIControl {
	
	private int money;
	private int maxMoneyDigits;
	private Shop trapShop;
	private RotationDialogueBox rotationMenu;
	private LinkedPageSystem infoPages;
	private FloorSelect floorSelect;
	private UpgradeRoller upgradeRoller;
	private SandTemple temple;
	private WaveStarter waveStarter;
	private Vector2f locationOfMoney;
	private Vector2f sizeOfMoney;
	//TODO make a money object that takes size, loc, etc. and isolates the func.
	
	public UIControl() {
		money = 100000;
		maxMoneyDigits = 7;
		locationOfMoney = new Vector2f(0.6f,-0.9f);
		sizeOfMoney = new Vector2f(.025f, .05f);
		rotationMenu = rotationMenuInit();
		infoPages = infoPageInit();
		temple = templeInit();
		floorSelect = floorSelectInit();
		trapShop = shopInit();
		upgradeRoller = upgradeRollerInit();
		waveStarter = waveStarterInit();
	}
	
	private RotationDialogueBox rotationMenuInit() {
		return new RotationDialogueBox();
	}
	
	private Shop shopInit() {
		ShopItemLibrary.init();
		Vector2f maxSize = new Vector2f(.25f, .5f);
		Vector2f locationOfShop = new Vector2f(.5f, -.1f);
		Shop toBeMade = new Shop(locationOfShop, maxSize, populateShop());
		return toBeMade;
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
		Vector2f locationOfInfoPages = new Vector2f(-.9f, -.8f);
		Vector2f maxSizeOfInfoPages = new Vector2f(.4f, 1.6f);
		PageLibrary.init(new Vector2f(locationOfInfoPages.x, locationOfInfoPages.y), maxSizeOfInfoPages);
		LinkedPageSystem infoPagesLocal = new LinkedPageSystem(locationOfInfoPages, maxSizeOfInfoPages);
		infoPagesLocal.populate((Menu)PageLibrary.categoriesMenu);
		return infoPagesLocal;
	}
	
	private FloorSelect floorSelectInit() {
		Vector2f locationOfBottomLeft = new Vector2f(-.4f, .85f);
		Vector2f totalSize = new Vector2f(.8f, .1f);
		FloorSelect floorSelect = new FloorSelect(locationOfBottomLeft, totalSize);
		return floorSelect;		
	}
	
	private UpgradeRoller upgradeRollerInit() {
		return new UpgradeRoller();
	}
	
	private SandTemple templeInit() {
		return new SandTemple();
	}
	
	private WaveStarter waveStarterInit() {
		Vector2f locationBL = new Vector2f(.7f, -.85f);
		Vector2f totalSize = new Vector2f(.2f, .2f);
		return new WaveStarter(locationBL, totalSize);
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		floorSelect.render(dynamicGuis);
		temple.render(dynamicGuis);
		trapShop.render(dynamicGuis);
		infoPages.render(dynamicGuis);
		rotationMenu.render(dynamicGuis);
		upgradeRoller.render(dynamicGuis);
		renderUpgradeOption(dynamicGuis);
		waveStarter.render(dynamicGuis);
		renderMoney(dynamicGuis);
	}
	
	private void renderUpgradeOption(ArrayList<GuiTexture> dynamicGuis) {
		if(trapShop.isOn() && temple.getCurrentFloor().getTile(trapShop.getGridLoc().x, trapShop.getGridLoc().y).getId()>=TileLibrary.getFirstTrapId()) {
			trapShop.renderUpgradeOption(dynamicGuis);
		}
	}
	
	private void renderMoney(ArrayList<GuiTexture> dynamicGuis) {
		StringLibrary.setSize(sizeOfMoney);
		float width = StringLibrary.getWidth(money + "");
		float horizConst = (float)1.7;
		dynamicGuis.add(new GuiTexture(GuiLibrary.blankBackground, 
				new Vector2f(locationOfMoney.x + width / 2 + (maxMoneyDigits - (int)Math.log10(money) + horizConst) * StringLibrary.getWidth("0")/2,
						 locationOfMoney.y - sizeOfMoney.y/2), 
				new Vector2f(width/2, sizeOfMoney.y)));
		
		dynamicGuis.addAll(StringLibrary.makeItFitC(money+"", locationOfMoney, 1-locationOfMoney.x));
	}
	
	public void doMouseEvents(float mouseX, float mouseY, ArrayList<GuiTexture> dynamicGuis) {
		floorSelectLogic(mouseX, mouseY);
		shopExitLogic(mouseX, mouseY);
		
		shopInitiationLogic(mouseX, mouseY);
		shopSelectionLogic(mouseX, mouseY, dynamicGuis);
		trapShop.scrollLogic(mouseX, mouseY);
		
		rotationMenuLogic(mouseX, mouseY);
		
		upgradeRollerInitiationLogic(mouseX, mouseY, dynamicGuis);
		upgradeSelectionLogic(mouseX, mouseY);
		
		infoPages.checkForMouseEvents(mouseX, mouseY);
		
		waveStarterLogic(mouseX, mouseY);
	}
	
	private void floorSelectLogic(float mouseX, float mouseY) {
		if(floorSelect.canInteract()) {
			floorSelect.scrollIfClicked(mouseX, mouseY);
			if(temple.isEditState()) {
				int cost = floorSelect.tryToBuyFloor(mouseX, mouseY, money);
				if(cost>0) {
					temple.addFloor();
					money -= cost;
				}
			}
			temple.setFloor(floorSelect.changeFloor(mouseX, mouseY));
			floorSelect.setLastInteractTimeToNow();
		}
	}
	
	private void shopInitiationLogic(float mouseX, float mouseY) {
		if(temple.getCurrentFloor().isClicked(mouseX, mouseY) && !rotationMenu.isOn() && trapShop.getLastTimeClosed()+250 < System.currentTimeMillis() && trapShop.getLastTimeClicked()+500<System.currentTimeMillis() && !upgradeRoller.isOn() && temple.isEditState()) {
			int x = (Mouse.getX()-(int)((temple.getCurrentFloor().getLoc().x-temple.getCurrentFloor().getSize()+1f)*DisplayManager.WIDTH/2))/((int)(temple.getCurrentFloor().getSize()*DisplayManager.WIDTH));
			int y = (Mouse.getY()-(int)((temple.getCurrentFloor().getLoc().y-temple.getCurrentFloor().getSize()+1f)*DisplayManager.HEIGHT/2))/((int)(temple.getCurrentFloor().getSize()*DisplayManager.HEIGHT*DisplayManager.getAspectratio()));
			int width = temple.getCurrentFloor().getWidth();
			if(width>x && width>y) {
				trapShop.setGridLoc(new Point(x, y), temple.getCurrentFloor());
				trapShop.setOn(true);
				trapShop.setLastTimeClicked(System.currentTimeMillis());
			}
		}
	}
	
	private void shopSelectionLogic(float mouseX, float mouseY, ArrayList<GuiTexture> dynamicGuis) {
		if(trapShop.isOn() && trapShop.shopIsClicked(mouseX, mouseY)) {
			Tile oldTile=temple.getCurrentFloor().getTile((int)trapShop.getGridLoc().x, (int)trapShop.getGridLoc().y);
			int selection = trapShop.getShopItem(mouseX, mouseY);
			if(selection != -1) {
				ShopItem selectedItem = ShopItemLibrary.getItem(selection);
				if(selectedItem.getCost()<=money && oldTile.getId() != selectedItem.getId() && !selectedItem.isRotatable()) {
					temple.addTrapToCurrentFloor(trapShop.getGridLoc(), selection);
					trapShop.setOn(false);
					rotationMenu.setOn(false);
					upgradeRoller.setOn(false);
					trapShop.setLastTimeClosed(System.currentTimeMillis());
					money -= selectedItem.getCost();
				} else if(selectedItem.getCost()>money) {
					dynamicGuis.addAll(StringLibrary.makeItFitC("Insufficient Funds", new Vector2f(trapShop.getLoc().getX(), trapShop.getLoc().y-StringLibrary.getSize().y*2), trapShop.getSize().x*1.6f));
				} else if(oldTile.getId() == selectedItem.getId()) {
					dynamicGuis.addAll(StringLibrary.makeItFitC("That trap is already there!", new Vector2f(trapShop.getLoc().getX(), trapShop.getLoc().y-StringLibrary.getSize().y*6), trapShop.getSize().x*1.6f));
				} else if(selectedItem.isRotatable()) {
					Vector2f size1 = new Vector2f(.8f, .8f);
					ShopItem[][] rotations = new ShopItem[2][2];
					for(int i = 0; i<2; i++) {
						for(int j = 0; j<2; j++) {
							rotations[i][j] = ShopItemLibrary.getItem(selectedItem.getId());
							rotations[i][j].drawTile().setScale(new Vector2f(.06f, .06f*(float)DisplayManager.getAspectratio()));
						}
					}
					rotationMenu = new RotationDialogueBox(new Vector2f(-size1.x/2, -size1.y/2), size1, rotations,  "Which way should it point?");
				}
			}
		}
	}
	
	private void shopExitLogic(float mouseX, float mouseY) {
		if(trapShop.isExitClicked(mouseX, mouseY) || !temple.isEditState() || floorSelect.shouldExitShop()) {
			trapShop.setOn(false);
			rotationMenu.setOn(false);
			upgradeRoller.setOn(false);
		}
	}
	
	private void upgradeRollerInitiationLogic(float mouseX, float mouseY, ArrayList<GuiTexture> dynamicGuis) {
		if(trapShop.isOn() && trapShop.isUpgradeClicked(mouseX, mouseY) && !upgradeRoller.isOn()) {
			if(temple.getCurrentFloor().getTile((int)trapShop.getGridLoc().x, (int)trapShop.getGridLoc().y).getId() > 1) {
				Trap trap = (Trap) temple.getCurrentFloor().getTile((int)trapShop.getGridLoc().x, (int)trapShop.getGridLoc().y);
				int levelCost = trap.getLevel()*100+50;
				if(money-levelCost>-0) {
					upgradeRoller = new UpgradeRoller(new Vector2f(-.53f, -.8f), new Vector2f(.8f, .4f), trap);
					money-=levelCost;
				}
				else {
					dynamicGuis.addAll(StringLibrary.makeItFitC("Insufficient Funds",new Vector2f(trapShop.getLoc().x, trapShop.getLoc().y-.2f), trapShop.getSize().x));
				}
			}
		}
	}
	
	private void upgradeSelectionLogic(float mouseX, float mouseY) {
		if(upgradeRoller.isOn() && upgradeRoller.getTimeOpened()+250 < System.currentTimeMillis()) {
			if(upgradeRoller.itemIsClicked(mouseX, mouseY)) {
				upgradeRoller.getTrap().upgrade(upgradeRoller.getClickedUpgrade(mouseX, mouseY));
				upgradeRoller.setOn(false);
				trapShop.setOn(false);
				trapShop.setLastTimeClosed(System.currentTimeMillis());
			}
		}
	}
	
	private void rotationMenuLogic(float mouseX, float mouseY) {
		if(rotationMenu.isOn() && rotationMenu.shopIsClicked(mouseX, mouseY)) {
			int selected = rotationMenu.getShopItem(mouseX, mouseY);
			if(selected != 0) {
				rotationMenu.setSelection(selected);
			}
			if(rotationMenu.getSelection() != 0 /*&& rotationMenu.isConfirmed(mouseX, mouseY)*/) {
				temple.addTrapToCurrentFloor(trapShop.getGridLoc(), rotationMenu.getGivenTile().getId()+rotationMenu.getSelection()-1);
				rotationMenu.setSelection(0);
				rotationMenu.setOn(false);
				trapShop.setOn(false);
				trapShop.setLastTimeClosed(System.currentTimeMillis());
			}
			if(rotationMenu.isCanceled(mouseX, mouseY)) {
				rotationMenu.setOn(false);
			}
		}
	}
	
	private void waveStarterLogic(float mouseX, float mouseY) {
		if(waveStarter.isStartWaveClicked(mouseX, mouseY)) {
			temple.changeEditState();
		}
	}
	
	public void tick(long milli) {
		temple.tick(milli);
	}
	
}
