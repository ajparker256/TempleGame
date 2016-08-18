package main;

import grid.Blank;
import grid.Dirt;
import grid.Exit;
import grid.Grid;
import grid.Tile;
import gui.GuiRenderer;
import gui.GuiTexture;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import librarys.PageLibrary;
import librarys.ShopItemLibrary;
import librarys.SoundLibrary;
import librarys.StringLibrary;
import librarys.TileLibrary;
import librarys.UpgradeLibrary;
import pathing.Group;
import pathing.Squad;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import buttons.*;
import entities.Flame;
import entities.Projectile;
import explorerTypes.Athlete;
import explorerTypes.Exploder;
import explorerTypes.Explorer;
import explorerTypes.Miner;
import explorerTypes.TreasureHunter;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import shopItems.ShopItem;
import sound.Sound;
import tools.MathM;
import traps.Trap;
import upgrades.UpgradeRoller;
//Main game loop
public class Main {
	//Amount of ambient light
public static final float ambient = 1f;
public static int money;
public static ArrayList<Explorer> group;
public static ArrayList<Button> buttons;
public static long milli;
public static ArrayList<Grid> grids;
public static int gridToBeRendered;
public static Shop epicShopofEpicness;
public static boolean wasJustDown = false;
public static ArrayList<Squad> squads;
public static GuiTexture background;
public static int round;
public static RotationDialogueBox rotationDialogueBox;
public static ArrayList<Projectile> projectiles;
public static double timeInRound;
public static ArrayList<Grid> gridsReadOnly;
public static UpgradeRoller upgradeRoller;
public static int state;
public static boolean isEditPhase;
public static Button startWave;
public static LinkedPageSystem testPageSystem;
public static void main(String[] args) throws FileNotFoundException {
	gridsReadOnly = new ArrayList<Grid>();
	grids = new ArrayList<Grid>();
	squads = new ArrayList<Squad>();
	money=2000;
	round = 0;
	isEditPhase = true;
	buttons = new ArrayList<Button>();
	group = new ArrayList<Explorer>();
	DisplayManager.createDisplay();
	Loader loader = new Loader();
	StringLibrary.init();
	//Aspect Ratio should be 1 to 2
	StringLibrary.setSize(new Vector2f(.02f, .04f));
	GuiLibrary.init(loader);
	UpgradeLibrary.init();
	SoundLibrary.init();
	PageLibrary.init(new Vector2f(-.9f, -.8f), new Vector2f(.4f, 1.6f));
	
	for(int i = 0; i<8; i++) {
		grids.add(new Grid(new Vector2f(-.5f,-.8f),0.05f,5+i, i));
		gridsReadOnly.add(grids.get(i).copy());
	}
	rotationDialogueBox = new RotationDialogueBox();
	
	ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
	ArrayList<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	projectiles= new ArrayList<Projectile>();
	GuiLibrary.background = loader.loadTexture("background");

	GuiRenderer guiRenderer = new GuiRenderer(loader);
	Sound.loopSound(SoundLibrary.music);	
	AnimationLibrary.init(loader);
	
	testPageSystem = new LinkedPageSystem(new Vector2f(-.8f, -.4f), new Vector2f(.4f, .8f));
	populateLeftMenu(testPageSystem);
	
	startWave = new Button(new Vector2f(.8f, -.09f), new Vector2f(1, -1));
	
	upgradeRoller = new UpgradeRoller();
	
	guis.add(new GuiTexture(GuiLibrary.backgroundDraft1, new Vector2f(-1f, -1f), new Vector2f(1.8f, 1.8f)));
/*	Group group1 = new Group(0);
	group1.add(new Explorer (group1));
	group1.add(new Miner (group1));
	group1.add(new Explorer (group1));
	group1.add(new Miner (group1));
	
	Group group2 = new Group(0);
	group2.add(new Explorer (group2));
	group2.add(new Miner (group2));
	group2.add(new Explorer (group2));
	group2.add(new Miner (group2));
	
	Group group3 = new Group(0);
	group3.add(new Explorer (group3));
	group3.add(new Miner (group3));
	group3.add(new Explorer (group3));
	group3.add(new Miner (group3));
	
	Group group4 = new Group(0);
	group4.add(new Explorer (group4));
	group4.add(new Miner (group4));
	group4.add(new Explorer (group4));
	group4.add(new Miner (group4));*/
	
	Group group1 = new Group(0);
	Group group2 = new Group(0);
	Group group3 = new Group(0);	
	Group group4 = new Group(2);

	
//	ArrayList<Group> squad1List=new ArrayList<Group>();
//	squad1List.add(group1);
	ArrayList<Group> squad2List = new ArrayList<Group>();
	squad2List.add(group2);
	squad2List.add(group3);
//	ArrayList<Group> squad3List = new ArrayList<Group>();
//	squad3List.add(group4);
	/*for(Group g : squad1List) {
		g.add(new Miner (g));
		g.add(new Miner (g));
		g.add(new Explorer (g));
		g.add(new TreasureHunter(g));
		}*/
	for(Group g : squad2List) {
		g.add(new Miner (g));
		g.add(new Athlete (g));
		g.add(new Exploder (g));
		g.add(new TreasureHunter(g));

	}
/*	for(Group g : squad3List) {
		g.add(new Miner (g));
		g.add(new Miner (g));
		g.add(new Miner (g));
		g.add(new TreasureHunter(g));

	}
	Squad squad1=new Squad(squad1List, 0);*/
	Squad squad2= new Squad(squad2List, 0);
	//Squad squad3= new Squad(squad3List, 2);
	//squads.add(squad1);
	squads.add(squad2);
	//squads.add(squad3);
	
	int x = 0;
	for(Squad sq : squads) {
		for(Group gr : sq.getGroups()) {
			gr.setInitialLocation(new Vector2f(gr.getInitialLocation().x-.1f*x, gr.getInitialLocation().y));
		}
		x++;
	}
	Vector2f leftSideMenuLoc = new Vector2f(-.8f, -.6f);
	Vector2f leftSideMenuSize = new Vector2f(.4f, 1.2f);
	

	//Page credits = new Page(leftSideMenuLoc, leftSideMenuSize, "Credits", "These are the credits, this was done by AJ Parker and Jackson Mills, with art collaboration from Hunter Ferrell.");
	//Page story = new Page(leftSideMenuLoc, leftSideMenuSize, "Story", "There once was a king by the name of VERYLONGNAME, he lived in a verylongkingdom with many verylongwords", GuiLibrary.rareBackground);
	
	ShopItemLibrary.init();
	ShopItem[][] traps = new ShopItem[2][ShopItemLibrary.getItems().size()/2];
	ArrayList<ShopItem> items = ShopItemLibrary.getItems();
	int k = 0;
	for(int i = 0; i<traps.length; i++) {
		for(int j = 0; j<traps[0].length; j++) {
			traps[i][j] = items.get(k);
			k++;
		}
	}
	epicShopofEpicness = new Shop(new Vector2f(.5f, -.1f), new Vector2f(.25f, .5f), traps);
	//Makes the background white
	guis.add(new GuiTexture(loader.loadTexture("White"), new Vector2f(.9f,-.9f), new Vector2f(2f, 2f)));

	
	//projectiles.add(new Projectile(3, 1, 9, 0));
	//projectiles.add(new Projectile(3, 1, 8, 0));
	double counter = 0;
	milli = System.currentTimeMillis();
	state = 0; //Title Screen State
	//state = 1; //Gameplay state
	//state = 2; //Pause
//	Testing the instanceof keyword as it applies to extended objects within lists
/*
 	String[] testList = {"1", "2"};
	Menu test = new Menu(new Vector2f(), new Vector2f(), testList, "Title");
	Linkable testPage = new Page(new Vector2f(), new Vector2f(), "Title", "Description");
	Linkable[] testArray = new Linkable[2];
	testArray[0] = test;
	testArray[1] = testPage;
	boolean b = test instanceof Menu;
	boolean b2 = testArray[0] instanceof Menu;
	boolean b3 = testArray[1] instanceof Menu;
	System.out.println("Test is an instance of Menu. This statement is " + b);
	System.out.println("TestArray[0] is an instance of Menu. This statement is " + b2);
	System.out.println("TestArray[1] is an instance of Menu. This statement is " + b3);
	*/
	while(!Display.isCloseRequested()){
	//	guis.add(background);
		milli = System.currentTimeMillis() - milli;
		
		if(state == 0) {
			if(counter < 1000) {
				dynamicGuis.add(new GuiTexture(GuiLibrary.backgroundDraft1, new Vector2f(0.5f, 0f), new Vector2f(1.55f, 1.2f)));
				counter += milli;
			} else  {
				state = 1;
			}
		}
		if(state == 1 && !isEditPhase) {
			for(Squad s : squads) {
				s.tick((int)milli,grids.get(s.getGroups().get(s.getGroups().size()-1).getFloor()));
			}
			
			for(Grid tempGrid:grids){
				for(int i=0;i<(int)tempGrid.getTileCount().x;i++){
					for(int j=0;j<(int)tempGrid.getTileCount().y;j++){
						tempGrid.getTile(i, j).tick(milli);
					}
				}
			}
			
			for(int i=0;i<projectiles.size();i++){
				Projectile projectile=projectiles.get(i);
				projectile.tick(milli);
				if(projectile.isKill())
					projectiles.remove(projectile);
				
			
			}
			//		credits.render(dynamicGuis);
			
		}
		if(state == 1 || state == 2) {
			update(dynamicGuis, milli);
			dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
			//General Knowledge about rendering
			//Renders from TOP TO BOTTOM!
			//RENDERS FROM CENTER OF IMAGE! (90% certain)
			//The screen is -1 to 1 for x and 0 to -1 for y in floats
			
			//Determines which of the grids to render
			if(isEditPhase)
				dynamicGuis.addAll(gridsReadOnly.get(gridToBeRendered).render());
			else
				dynamicGuis.addAll(grids.get(gridToBeRendered).render());
			
			for(Grid g: grids) {
				dynamicGuis.addAll(g.renderFloorSelect());
			}
			for(Squad squad : squads) {
				dynamicGuis.addAll(squad.render());
			}
			for(Projectile projectile : projectiles){
				if(projectile.canRender())
					dynamicGuis.add(projectile.render());
			}
		//	story.render(dynamicGuis);
			if(epicShopofEpicness.isOn()) {
				epicShopofEpicness.render(dynamicGuis);
			}
			if(rotationDialogueBox != null && rotationDialogueBox.isOn()) {
				rotationDialogueBox.render(dynamicGuis);
			}
			if(upgradeRoller.isOn()) {
				upgradeRoller.render(dynamicGuis);
			}
			
			testPageSystem.render(dynamicGuis);
			
			
		}
		//PageLibrary.trapMenu.render(dynamicGuis);
		//Reinitialize milli after all methods that call it are done. Then render and do other stuff.
		milli = System.currentTimeMillis();
		guiRenderer.render(guis);
		guiRenderer.render(dynamicGuis);
		DisplayManager.updateDisplay();
		dynamicGuis.clear();
		
		
	}
	
	guiRenderer.cleanUp();
	loader.cleanUp();
	DisplayManager.closeDisplay();
		
	}

	public static void titleScreenLogic(double milli) {
		
	}
	
	public static void update(ArrayList<GuiTexture> dynamicGuis, double milli) {
		//TODO find a way to make this only on click, currently non-func. Works always when mouse is down regardless of event
		if(!Mouse.getEventButtonState() && Mouse.isButtonDown(0) && state == 1) {
			updateMouse(dynamicGuis);
			wasJustDown = true;
		} else if(Mouse.isButtonDown(2)) {
			state = 2;
		} else {
			wasJustDown = false;
			state = 1;
		}
		//10s per round atm
		timeInRound += milli;
			if(timeInRound/1000 > 10) {
				round++;
				for(Grid g : grids) {
					for(Point p : g.getTreasureLocs()) {
						g.getTile(p.x, p.y).getIncome();
					}
				}
				//Recall units or push them all out then stall somehow
				timeInRound = 0;
			}
	}
	
	public static void updateMouse(ArrayList<GuiTexture> dynamicGuis) {
		//This scales mouseX to be in the range of 1 to -1;
		float mouseX = (float)Mouse.getX()*2/DisplayManager.WIDTH - 1;
		//This scales mouseY to be in the range of 1 at the top and -1 at the bottom
		float mouseY = (float)Mouse.getY()*2/DisplayManager.HEIGHT -1;
		
		shopInitiationLogic(mouseX, mouseY);
		shopSelectionLogic(mouseX, mouseY, dynamicGuis);
		epicShopofEpicness.scrollLogic(mouseX, mouseY);
		shopExitLogic(mouseX, mouseY);
		
		upgradeRollerInitiationLogic(mouseX, mouseY, dynamicGuis);
		upgradeSelection(mouseX, mouseY);
		
		rotationDialogueBoxLogic(mouseX, mouseY);
		
		waveInitiationLogic(mouseX, mouseY);
		//Floor Select / Purchasing Floors Logic (TODO)
		floorSelect(mouseX, mouseY);
		
		testPageSystem.checkForMouseEvents(mouseX, mouseY);
		
		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
	}
	
	private static Linkable test() {
		String[] hello = new String[2];
		hello[0] = "Hello";
		hello[1] = " World";
		Menu newMenu = new Menu(new Vector2f(), new Vector2f(), hello, "");
		return newMenu;
	}
	
	private static void waveInitiationLogic(float mouseX, float mouseY) {
		if(startWave.isClicked(mouseX, mouseY)) {
			isEditPhase = !isEditPhase;
			int i = 0; 
			for(Grid g : gridsReadOnly) {
					grids.add(i, g.copy());
					grids.remove(i+1);
					i++;
			}
		}
	}
//TODO optimize if necessary	
	private static void populateLeftMenu(LinkedPageSystem linkedSystem) {
		ArrayList<String> derivativeLinks = PageLibrary.categoriesMenu.getEntries();
		linkedSystem.addNewMenu((Menu)PageLibrary.categoriesMenu);
		linkedSystem.setCurrentScreenId(PageLibrary.categoriesMenu.getTitle());
		ArrayList<String> moreLinks = new ArrayList<String>();
		while(!derivativeLinks.isEmpty()) {
		for(String key : derivativeLinks) {
			Linkable option = PageLibrary.getLinkable(key);
			if(option instanceof Menu) {
				ArrayList<String> links = ((Menu) option).getEntries();
				for(String link : links) {
					if(!derivativeLinks.contains(link)) {
						moreLinks.add(link);
					}
				}
				linkedSystem.addNewMenu((Menu)option);
			} else if(option instanceof Page) {
				linkedSystem.addNewPage((Page)option);
			} else {
				System.out.println("ERROR IN POPULATION OF LEFT MENU");
			}
		}
		derivativeLinks.clear();
		derivativeLinks.addAll(moreLinks);
		moreLinks.clear();
		}
	}
	
	private static void shopExitLogic(float mouseX, float mouseY) {
		if(epicShopofEpicness.isExitClicked(mouseX, mouseY) || !isEditPhase) {
			epicShopofEpicness.setOn(false);
			rotationDialogueBox.setOn(false);
			upgradeRoller.setOn(false);
		}
	}
	
	private static void shopInitiationLogic(float mouseX, float mouseY) {
		if(gridsReadOnly.get(gridToBeRendered).getGridButton().isClicked(mouseX, mouseY) && !rotationDialogueBox.isOn() && epicShopofEpicness.getLastTimeClosed()+250 < System.currentTimeMillis() && epicShopofEpicness.getLastTimeClicked()+500<System.currentTimeMillis() && !upgradeRoller.isOn() && isEditPhase) {
			int x = (Mouse.getX()-(int)((gridsReadOnly.get(gridToBeRendered).getLoc().x-gridsReadOnly.get(gridToBeRendered).getSize()+1f)*DisplayManager.WIDTH/2))/(int)(gridsReadOnly.get(gridToBeRendered).getSize()*DisplayManager.WIDTH);
			int y = (Mouse.getY()-(int)((gridsReadOnly.get(gridToBeRendered).getLoc().y-gridsReadOnly.get(gridToBeRendered).getSize()+1f)*DisplayManager.HEIGHT/2))/(int)(gridsReadOnly.get(gridToBeRendered).getSize()*DisplayManager.HEIGHT*DisplayManager.getAspectratio());
			epicShopofEpicness.setGridLoc(new Vector2f((float)x, (float)y));
			epicShopofEpicness.setOn(true);
			epicShopofEpicness.setLastTimeClicked(System.currentTimeMillis());
		}
	}
	
	private static void upgradeRollerInitiationLogic(float mouseX, float mouseY, ArrayList<GuiTexture> dynamicGuis) {
		if(epicShopofEpicness.isOn() && epicShopofEpicness.isUpgradeClicked(mouseX, mouseY) && !upgradeRoller.isOn()) {
			if(Main.gridsReadOnly.get(gridToBeRendered).getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y).getId() > 1) {
				Trap trap = (Trap) Main.gridsReadOnly.get(gridToBeRendered).getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y);
				int levelCost = trap.getLevel()*100+50;
				if(money-levelCost>-0) {
					upgradeRoller = new UpgradeRoller(new Vector2f(-.53f, -.8f), new Vector2f(.8f, .4f), trap);
					money-=levelCost;
				}
				else {
					dynamicGuis.addAll(StringLibrary.makeItFitC("Insufficient Funds",new Vector2f(epicShopofEpicness.getLoc().x, epicShopofEpicness.getLoc().y-.2f), epicShopofEpicness.getSize().x));
				}
			}
		}
	}
	
	private static void shopSelectionLogic(float mouseX, float mouseY, ArrayList<GuiTexture> dynamicGuis) {
		if(epicShopofEpicness.isOn() && epicShopofEpicness.shopIsClicked(mouseX, mouseY)) {
			Tile oldTile=gridsReadOnly.get(gridToBeRendered).getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y);
			int selection = epicShopofEpicness.getShopItem(mouseX, mouseY);
			if(selection != -1) {
				ShopItem selectedItem = ShopItemLibrary.getItem(selection);
				if(selectedItem.getCost()<=money && oldTile.getId() != selectedItem.getId() && !selectedItem.isRotatable()) {
					Tile selectedTrap = TileLibrary.getTile(oldTile.getX(), oldTile.getY(), oldTile.getSize(), selection);
					selectedTrap.setTrapRefs(oldTile.getTrapRefs());
					
					//REMOVE WHEN READ ONLY IMPLEMENTED FULLY
					//grid.setTile(oldTile.getX(), oldTile.getY(), selectedTrap);
					gridsReadOnly.get(gridToBeRendered).setTile(oldTile.getX(), oldTile.getY(), selectedTrap);
					epicShopofEpicness.setOn(false);
					rotationDialogueBox.setOn(false);
					upgradeRoller.setOn(false);
					epicShopofEpicness.setLastTimeClosed(System.currentTimeMillis());
					money -= selectedItem.getCost();
				} else if(selectedItem.getCost()>money) {
					dynamicGuis.addAll(StringLibrary.makeItFit("Insufficient Funds", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*2), epicShopofEpicness.getSize().x*1.6f));
				} else if(oldTile.getId() == selectedItem.getId()) {
					dynamicGuis.addAll(StringLibrary.makeItFitC("That trap is already there!", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*6), epicShopofEpicness.getSize().x*1.6f));
				} else if(selectedItem.isRotatable()) {
					Vector2f size1 = new Vector2f(.8f, .8f);
					ShopItem[][] rotations = new ShopItem[2][2];
					for(int i = 0; i<2; i++) {
						for(int j = 0; j<2; j++) {
							rotations[i][j] = ShopItemLibrary.getItem(selectedItem.getId());
							rotations[i][j].drawTile().setScale(new Vector2f(.06f, .06f*(float)DisplayManager.getAspectratio()));
						}
					}
					rotationDialogueBox = new RotationDialogueBox(new Vector2f(-size1.x/2, -size1.y/2), size1, rotations,  "Which way should it point?");
				}
			}
		}
	}
	
	private static void upgradeSelection(float mouseX, float mouseY) {
		if(upgradeRoller.isOn() && upgradeRoller.getTimeOpened()+250 < System.currentTimeMillis()) {
			if(upgradeRoller.itemIsClicked(mouseX, mouseY)) {
				upgradeRoller.getTrap().upgrade(upgradeRoller.getClickedUpgrade(mouseX, mouseY));
				upgradeRoller.setOn(false);
				epicShopofEpicness.setOn(false);
				epicShopofEpicness.setLastTimeClosed(System.currentTimeMillis());
			}
		}
	}
	
	private static void rotationDialogueBoxLogic(float mouseX, float mouseY) {
		if(rotationDialogueBox.isOn() && rotationDialogueBox.shopIsClicked(mouseX, mouseY)) {
			int selected = rotationDialogueBox.getShopItem(mouseX, mouseY);
			if(selected != 0) {
				rotationDialogueBox.setSelection(selected);
			}
			if(rotationDialogueBox.getSelection() != 0 /*&& rotationDialogueBox.isConfirmed(mouseX, mouseY)*/) {
				gridsReadOnly.get(gridToBeRendered).setTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y, TileLibrary.getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y, .05f, rotationDialogueBox.getGivenTile().getId()+rotationDialogueBox.getSelection()-1));
				rotationDialogueBox.setSelection(0);
				rotationDialogueBox.setOn(false);
				epicShopofEpicness.setOn(false);
				epicShopofEpicness.setLastTimeClosed(System.currentTimeMillis());
			}
			if(rotationDialogueBox.isCanceled(mouseX, mouseY)) {
				rotationDialogueBox.setOn(false);
			}
		}
	}
	
	private static void floorSelect(float mouseX, float mouseY) {
		for(Grid g : grids) {
			if(g.isLevelSelected(mouseX, mouseY) && gridToBeRendered != g.getFloor()) {
				gridToBeRendered = g.getFloor();
				epicShopofEpicness.setOn(false);
				break;
			}
		}
	}
	
}