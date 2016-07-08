package main;

import grid.Blank;
import grid.CursedIdol;
import grid.DartTrap;
import grid.Dirt;
import grid.Grid;
import grid.Tile;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;

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
public static Grid grid;
public static Shop epicShopofEpicness;
public static boolean wasJustDown = false;
	public static void main(String[] args) throws FileNotFoundException {

		money=1327;
		buttons = new ArrayList<Button>();
		group = new ArrayList<Explorer>();
		Camera camera = new Camera();
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Random random = new Random();
		new TextureLibrary(loader);
	
		grid= new Grid(new Vector2f(-.5f,-.8f),0.05f,10);

		
		
		
		
		
	SoundLibrary.music = Sound.loadSound("song");

	StringLibrary.init(loader);
	
	
	
	ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
	ArrayList<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	
	
	
	
	
	GuiLibrary.background = loader.loadTexture("background");

	
	
	boolean exit=false;
	GuiRenderer guiRenderer = new GuiRenderer(loader);
	Sound.loopSound(SoundLibrary.music);
	
	//Initializes Explorer frames for later
	GuiLibrary.init(loader);
	AnimationLibrary.init(loader);
	
	Group group1 = new Group(1);
	group1.add(new Explorer (group1));
	group1.add(new Miner (group1));
	group1.add(new Explorer (group1));
	group1.add(new Miner (group1));
	
	Group group2 = new Group(1);
	group2.add(new Explorer (group2));
	group2.add(new Miner (group2));
	group2.add(new Explorer (group2));
	group2.add(new Miner (group2));
	
	Group group3 = new Group(1);
	group3.add(new Explorer (group3));
	group3.add(new Miner (group3));
	group3.add(new Explorer (group3));
	group3.add(new Miner (group3));
	
	Group group4 = new Group(1);
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
	Tile[][] traps = new Tile[2][4];
	for(int i = 0; i<traps.length; i++) {
		for(int j = 0; j<traps[0].length; j++) {
			traps[i][j] = new Dirt( i, j, .03f, new Vector2f(-.9f+j*.03f, -.5f+i*.03f));
			//System.out.println(traps[i][j].getLocation());
		}
	}
	traps[1][1] = new Blank(1,1, .03f, new Vector2f(-.87f, -.47f));
	traps[0][1] = new CursedIdol(0,1, .03f);
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
	

	//guis.add(new GuiTexture(loader.loadTexture("A"), new Vector2f(0f, 0f), new Vector2f(1f,1f)));
	for(int i = 0; i<test.size(); i++) {
		dynamicGuis.add(test.get(i));
	}
	milli = System.currentTimeMillis();
	while(!Display.isCloseRequested()){
		milli = System.currentTimeMillis() - milli;
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
		//dynamicGuis.addAll((bob.getWalkingAnimation(loader, 30).getFrame()));
		squad1.tick((int)milli,grid);
		//group1.move((int)milli,grid);
		//group2.move((int)milli, grid);
	//	group3.move((int)milli, grid);
		//group4.move((int)milli, grid);
		
		dynamicGuis.addAll(group1.render());
		dynamicGuis.addAll(group2.render());
		dynamicGuis.addAll(group3.render());
		dynamicGuis.addAll(group4.render());
		//guiRenderer.render(arrows.getFrame());
		
		//TODO FLAW IN MOVING, SINCE IT IS DONE IN FLOATS, THE LARGER X OF SCREEN MAKES HORIZONTAL MOVEMENT FASTER FOR THE SAME
		//FLOAT VALUE IN THE EXPLORERS VELOCITY!!!
		
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
				StringLibrary.makeItFit("Insufficient Funds", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*2), epicShopofEpicness.getSize().x*1.6f);
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
		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
	}
}


	

