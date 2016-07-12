package main;

import grid.Blank;
import grid.CursedIdol;
import grid.DartTrap;
import grid.Dirt;
import grid.Exit;
import grid.Grid;
import grid.Tile;
import grid.TreasureTrap;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import librarys.SoundLibrary;
import librarys.StringLibrary;
import librarys.TextureLibrary;
import librarys.TileLibrary;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import buttons.Button;
import buttons.Shop;
import entities.Camera;
import entities.Explorer;
import entities.Group;
import entities.Miner;
import entities.Squad;
import entities.Unit;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import sound.Sound;
import tools.MathM;
//Main game loop
public class Main {
	//Amount of ambient light
public static final float ambient = 1f;
public static int money;
public static ArrayList<Explorer> group;
public static ArrayList<Button> buttons;
public static long milli;
public static ArrayList<Grid> grids;
public static Grid grid;
public static Shop epicShopofEpicness;
public static boolean wasJustDown = false;
public static ArrayList<Squad> squads;
public static GuiTexture background;
public static int round;
public static void main(String[] args) throws FileNotFoundException {
		grids = new ArrayList<Grid>();
		squads = new ArrayList<Squad>();
		money=1327;
		round = 0;
		buttons = new ArrayList<Button>();
		group = new ArrayList<Explorer>();
		Camera camera = new Camera();
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Random random = new Random();
		new TextureLibrary(loader);
		StringLibrary.init(loader);
		GuiLibrary.init(loader);
		
		grid= new Grid(new Vector2f(-.5f,-.8f),0.05f,10, 0);
		grids.add(grid);	
		grids.add(new Grid(new Vector2f(-.5f,-.8f),0.05f,10, 1));
		grid.setIsOn(true);
		
		//TODO figure out why this gave nothing but black screen
		//background = new GuiTexture(GuiLibrary.desertBackdrop, new Vector2f(-.9f,-1), new Vector2f(1,1));
		
		
		
	SoundLibrary.music = Sound.loadSound("song");

	
	
	
	
	ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
	ArrayList<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	
	
	
	
	
	GuiLibrary.background = loader.loadTexture("background");

	
	
	boolean exit=false;
	GuiRenderer guiRenderer = new GuiRenderer(loader);
	Sound.loopSound(SoundLibrary.music);
	
	//Initializes Explorer frames for later
	
	AnimationLibrary.init(loader);
	
	Group group1 = new Group(0);
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
	group4.add(new Miner (group4));
	
	ArrayList<Group> squad1List=new ArrayList<Group>();
	squad1List.add(group1);
	squad1List.add(group2);
	squad1List.add(group3);
	squad1List.add(group4);
	Squad squad1=new Squad(squad1List, 1);
	squads.add(squad1);
	
	int x = 0;
	for(Squad sq : squads) {
		for(Group gr : sq.getGroups()) {
			gr.setInitialLocation(new Vector2f(gr.getInitialLocation().x-.1f*x, gr.getInitialLocation().y));
		}
		x++;
	}
	
	
	
	Tile[][] traps = new Tile[2][4];
	for(int i = 0; i<traps.length; i++) {
		for(int j = 0; j<traps[0].length; j++) {
			traps[i][j] = new Dirt( i, j, .03f, new Vector2f(-.9f+j*.03f, -.5f+i*.03f));
			//System.out.println(traps[i][j].getLocation());
		}
	}
	traps[1][1] = new Blank(-20,-20, .03f, new Vector2f(-.87f, -.47f));
	traps[0][1] = new CursedIdol(-20,-20, .03f);
	traps[0][0] = new Exit(-20,-20, .03f);
	traps[1][3] = new TreasureTrap(.03f, Main.grids.indexOf(Main.grid));
	//traps[traps.length-1][traps[0].length-1] = new DartTrap(new Vector2f(-.9f,-.5f), .02f, new Vector2f(-.9f,-.4f), new Vector2f(1,0), loader);
	epicShopofEpicness = new Shop(new Vector2f(.5f, -.1f), new Vector2f(.3f, .4f), traps);
	
	//This is the string tester
	List<GuiTexture> test;
	//Aspect Ratio should be 1 to 2
	StringLibrary.setSize(new Vector2f(.02f, .04f));
	//ABCDEFGHIJKLMNOPQRSTUVWXYZ defghijklmnopqrstuvwxyz
	test = StringLibrary.drawString("0123456789abc", new Vector2f(-.98f,-.7f));
	test = StringLibrary.drawString("0123456789abcdefghijklmnopqrstuvwxyz  ,.!?!asdf!j?", new Vector2f(-.98f,-.3f));
	test.addAll(StringLibrary.drawString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", new Vector2f(-.98f,-.6f)));
	test.addAll(StringLibrary.drawString("asdfweqrlkxjvnmzpoitubnlkwqrw", new Vector2f(-.98f,-.4f)));
	test.addAll(StringLibrary.drawString("Hello World", new Vector2f(-.98f, -.8f)));
	test.addAll(StringLibrary.makeItFit("Lets make this set of words fit into a regular sized text box", new Vector2f(0f, -.5f), .5f));
	//Makes the background white
	guis.add(new GuiTexture(loader.loadTexture("White"), new Vector2f(.9f,-.9f), new Vector2f(2f, 2f)));
	
	/*Vector2f quickLoc = new Vector2f(-.8f, .6f);
	ArrayList<Integer> arrows = new ArrayList<Integer>();
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows1"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows2"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows3"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows4"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows5"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows6"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows7"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows8"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows9"));
	arrows.add(loader.loadTexture("/Trap Animations/Dart Trap/Arrows10"));
	Animation arrow = new Animation(arrows, quickLoc, new Vector2f(.2f,.2f));*/
	
	double timeInRound = 0;
	//guis.add(new GuiTexture(loader.loadTexture("A"), new Vector2f(0f, 0f), new Vector2f(1f,1f)));
	for(int i = 0; i<test.size(); i++) {
		dynamicGuis.add(test.get(i));
	}
	milli = System.currentTimeMillis();
	while(!Display.isCloseRequested()){
	//	guis.add(background);
		milli = System.currentTimeMillis() - milli;
		timeInRound += milli;
		
		//10s per round atm
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
		update(dynamicGuis);
		if(epicShopofEpicness.isOn()) {
			epicShopofEpicness.render(dynamicGuis);
		}
		dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
		
		//enemy update stuff
		//Renders from TOP TO BOTTOM!
		//RENDERS FROM CENTER OF IMAGE! (90% certain)
		//The screen is -1 to 1 for x and 0 to -1 for y in floats
		dynamicGuis.addAll(grid.render());
		
		guiRenderer.render(guis);
		guiRenderer.render(test);
		
		//TODO add for each grid, and inside only do the tihngs that are on the right grid
		squad1.tick((int)milli,grids.get(squad1.getGroups().get(squad1.getGroups().size()-1).getFloor()));
		
		
		for(Grid g: grids) {
			dynamicGuis.addAll(g.renderFloorSelect());
		}
		for(Squad squad : squads) {
			dynamicGuis.addAll(squad.render());
		}
		
		//Reinitialize milli after all methods that call it are done. Then render and do other stuff.
		milli = System.currentTimeMillis();
		guiRenderer.render(dynamicGuis);
		DisplayManager.updateDisplay();
		dynamicGuis.clear();
		
		
	}
	
	guiRenderer.cleanUp();
	loader.cleanUp();
	DisplayManager.closeDisplay();
		
	}
	
	public static void update(ArrayList<GuiTexture> dynamicGuis) {
		//TODO find a way to make this only on click, currently non-func. Works always when mouse is down regardless of event
		if(!Mouse.getEventButtonState() && Mouse.isButtonDown(0)) {
			updateMouse(dynamicGuis);
			wasJustDown = true;
		} else {
			wasJustDown = false;
		}
		
		//TODO Test traps
		//TODO Update Explorer AI
		//TODO Update player input such as placing traps
		//TODO Update ArrayLists of explorers, traps, Tiles, etc. if not already done so.
	}
	
	public static void updateMouse(ArrayList<GuiTexture> dynamicGuis) {
		//This scales mouseX to be in the range of 1 to -1;
		float mouseX = (float)Mouse.getX()*2/DisplayManager.WIDTH - 1;
		//This scales mouseY to be in the range of 1 at the top and -1 at the bottom
		float mouseY = (float)Mouse.getY()*2/DisplayManager.HEIGHT -1;
		if(epicShopofEpicness.isExitClicked(mouseX, mouseY)) {
			epicShopofEpicness.setOn(false);
		}
		if(grid.getGridButton().isClicked(mouseX, mouseY)) {
			int x = (Mouse.getX()-(int)((grid.getLoc().x-grid.getSize()+1f)*DisplayManager.WIDTH/2))/(int)(grid.getSize()*DisplayManager.WIDTH);
			int y = (Mouse.getY()-(int)((grid.getLoc().y-grid.getSize()+1f)*DisplayManager.HEIGHT/2))/(int)(grid.getSize()*DisplayManager.HEIGHT*DisplayManager.getAspectratio());
			epicShopofEpicness.setGridLoc(new Vector2f((float)x, (float)y));
			epicShopofEpicness.setOn(true);
		}
		if(epicShopofEpicness.isOn() && epicShopofEpicness.shopIsClicked(mouseX, mouseY)) {
			Tile oldTile=grid.getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y);
			Tile selectedTrap = TileLibrary.getTile(oldTile.getX(), oldTile.getY(), oldTile.getSize(), epicShopofEpicness.getShopItem(mouseX, mouseY));
			if(selectedTrap.getPrice()<=money && oldTile.getId() != selectedTrap.getId()) {
				//oldTile=grid.getTile((int)epicShopofEpicness.getPlacementLoc().x, (int)epicShopofEpicness.getPlacementLoc().y);
				//System.out.println(oldTile.getPosition());
				//Tile newTile=new Dirt(oldTile.getX(), oldTile.getY(), oldTile.getSize(), Main.grid.getLoc());
				grid.setTile(oldTile.getX(), oldTile.getY(), selectedTrap);
				epicShopofEpicness.setOn(false);
				money -= selectedTrap.getPrice();
			} else if(selectedTrap.getPrice()>money) {
				dynamicGuis.addAll(StringLibrary.makeItFit("Insufficient Funds", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*2), epicShopofEpicness.getSize().x*1.6f));
			} else if(oldTile.getId() == selectedTrap.getId()) {
				dynamicGuis.addAll(StringLibrary.makeItFitC("That trap is already there!", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*6), epicShopofEpicness.getSize().x*1.6f));
			}
		}
		if(epicShopofEpicness.isOn()) {
			if(epicShopofEpicness.isUpArrowClicked(mouseX, mouseY))
				epicShopofEpicness.scrollUp();
			if(epicShopofEpicness.isDownArrowClicked(mouseX, mouseY))
				epicShopofEpicness.scrollDown();
		}
		for(Grid g : grids) {
			if(g.isLevelSelected(mouseX, mouseY)) {
				Main.grid = g;
				break;
			}
		}
		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
	}
}


	

