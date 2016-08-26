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
public static FloorSelect testFloorSelect;
private static UIControl userInterface;
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
	//Vector2f locationOfLeftMenu = new Vector2f(-.9f, -.8f);
	//Vector2f sizeOfLeftMenu = new Vector2f(.4f, 1.6f);
	//Vector2f borderSize = new Vector2f(.1f, .1f*(float)DisplayManager.getAspectratio());
	//PageLibrary.init(new Vector2f(locationOfLeftMenu.x-StringLibrary.getSize().x/2, locationOfLeftMenu.y), sizeOfLeftMenu);
	
	//testPageSystem = new LinkedPageSystem(new Vector2f(locationOfLeftMenu.x-borderSize.x/2, locationOfLeftMenu.y-borderSize.y/2), new Vector2f(sizeOfLeftMenu.x+borderSize.x, sizeOfLeftMenu.y+borderSize.y));
	//populateLeftMenu(testPageSystem);
	
	//testFloorSelect = new FloorSelect(new Vector2f(-.4f, .8f), new Vector2f(.8f, .1f));
	
	userInterface = new UIControl();
	
	for(int i = 0; i<8; i++) {
		grids.add(new Grid(0.05f,5+i, i));
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
	

	
	startWave = new Button(new Vector2f(.8f, -.09f), new Vector2f(1, -1));
	
	upgradeRoller = new UpgradeRoller();
	
	guis.add(new GuiTexture(GuiLibrary.backgroundDraft1, new Vector2f(-1f, -1f), new Vector2f(1.8f, 1.8f)));
	Group group1 = new Group(0);
	group1.add(new Exploder (group1));
	group1.add(new Miner (group1));
	group1.add(new Athlete (group1));
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
	group4.add(new Miner (group4));

	
//	ArrayList<Group> squad1List=new ArrayList<Group>();
//	squad1List.add(group1);
	ArrayList<Group> squad2List = new ArrayList<Group>();
	squad2List.add(group1);
	squad2List.add(group2);
	squad2List.add(group3);
	squad2List.add(group4);
//	ArrayList<Group> squad3List = new ArrayList<Group>();
//	squad3List.add(group4);
	/*for(Group g : squad1List) {
		g.add(new Miner (g));
		g.add(new Miner (g));
		g.add(new Explorer (g));
		g.add(new TreasureHunter(g));
		}*/
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
	guis.add(new GuiTexture(loader.loadTexture("White"), new Vector2f(.9f,-.9f), new Vector2f(2f, 2f)));

	
	double counter = 0;
	milli = System.currentTimeMillis();
	state = 0; //Title Screen State
	//state = 1; //Gameplay state
	//state = 2; //Pause

	while(!Display.isCloseRequested()){
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
			
		}
		if(state == 1 || state == 2) {
			update(dynamicGuis, milli);
			dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
			userInterface.render(dynamicGuis);
			for(Squad squad : squads) {
				dynamicGuis.addAll(squad.render());
			}
			for(Projectile projectile : projectiles){
				if(projectile.canRender())
					dynamicGuis.add(projectile.render());
			}
		

					
			
		}
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
				timeInRound = 0;
			}
	}
	
	public static void updateMouse(ArrayList<GuiTexture> dynamicGuis) {
		//This scales mouseX to be in the range of 1 to -1;
		float mouseX = (float)Mouse.getX()*2/DisplayManager.WIDTH - 1;
		//This scales mouseY to be in the range of 1 at the top and -1 at the bottom
		float mouseY = (float)Mouse.getY()*2/DisplayManager.HEIGHT -1;
		
		waveInitiationLogic(mouseX, mouseY);
		userInterface.doMouseEvents(mouseX, mouseY, dynamicGuis);

		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
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
	
}