package main;

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

	
	
	//shop
	boolean exit=false;
	GuiRenderer guiRenderer = new GuiRenderer(loader);
	Sound.loopSound(SoundLibrary.music);
	
	//Test for Animation and moving the Unit. This is independent of a Unit class
	//TODO Attach the animation to the explorer so that it is done in tandem. 
	//Currently displaying and movement are taken care of in the while loop below. 
	
	//Initializes Explorer frames for later
	GuiLibrary.explorerStanding = loader.loadTexture("BasicExplorer");
	GuiLibrary.explorerWalkingL = loader.loadTexture("BasicExplorer Walking1");
	GuiLibrary.explorerWalkingR = loader.loadTexture("BasicExplorer Walking2");
	GuiLibrary.explorerStanding1 = loader.loadTexture("BasicExplorerR");
	GuiLibrary.explorerWalkingL1 = loader.loadTexture("BasicExplorer WalkingR1");
	GuiLibrary.explorerWalkingR1 = loader.loadTexture("BasicExplorer WalkingR2");
	AnimationLibrary.init(loader);
	
	Group group1 = new Group();
	group1.add(new Explorer (group1));
	group1.add(new Explorer (group1));
	group1.add(new Explorer (group1));
	group1.add(new Explorer (group1));
	Tile[][] traps = new Tile[4][2];
	for(int i = 0; i<traps.length; i++) {
		for(int j = 0; j<traps[0].length; j++) {
			traps[i][j] = new Dirt( i, j, .01f, new Vector2f(-.9f+j*.01f, -.5f+i*.01f));
		}
	}
	//traps[traps.length-1][traps[0].length-1] = new DartTrap(new Vector2f(-.9f,-.5f), .02f, new Vector2f(-.9f,-.4f), new Vector2f(1,0), loader);
	epicShopofEpicness = new Shop(4, 2, new Vector2f(-.9f, -.5f), new Vector2f(.25f, .25f), new Vector2f(4,4), traps);
	
	//This is the string tester
	List<GuiTexture> test;
	StringLibrary.setSize(new Vector2f(.02f, .02f));
	//ABCDEFGHIJKLMNOPQRSTUVWXYZ defghijklmnopqrstuvwxyz
	test = StringLibrary.drawString("0123456789abc", new Vector2f(-.98f,-.7f));
	test = StringLibrary.drawString("0123456789abcdefghijklmnopqrstuvwxyz  ,.!?!asdf!j?", new Vector2f(-.98f,-.3f));
	test.addAll(StringLibrary.drawString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", new Vector2f(-.98f,-.6f)));
	test.addAll(StringLibrary.drawString("asdfweqrlkxjvnmzpoitubnlkwqrw", new Vector2f(-.98f,-.4f)));
	test.addAll(StringLibrary.drawString("Hello World", new Vector2f(-.98f, -.8f)));
	test.addAll(StringLibrary.makeItFit("Lets make this set of words fit into a regular sized text box", new Vector2f(0f, -.5f), .5f));
	//Makes the background white
	guis.add(new GuiTexture(loader.loadTexture("White"), new Vector2f(.9f,-.9f), new Vector2f(2f, 2f)));
	
	/*
	Animation arrows = new Animation(new Vector2f(-.8f,-.5f));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows1"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows2"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows3"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows4"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows5"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows6"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows7"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows8"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows9"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.addFrame(new GuiTexture(loader.loadTexture("/Trap Animations/Dart Trap/Arrows10"), arrows.getLoc(), new Vector2f(.2f, .2f)));
	arrows.setDelay(10);
	*/

	//guis.add(new GuiTexture(loader.loadTexture("A"), new Vector2f(0f, 0f), new Vector2f(1f,1f)));
	for(int i = 0; i<test.size(); i++) {
		dynamicGuis.add(test.get(i));
	}
	milli = System.currentTimeMillis();
	while(!Display.isCloseRequested()){
		milli = System.currentTimeMillis() - milli;
		update();
		if(epicShopofEpicness.isOn())
			epicShopofEpicness.render(dynamicGuis);
		dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
		//enemy update stuff
		//Renders from TOP TO BOTTOM!
		//RENDERS FROM CENTER OF IMAGE! (90% certain)
		//The screen is -1 to 1 for x and 0 to -1 for y in floats
		//dynamicGuis.addAll(grid.render());
		guiRenderer.render(guis);
		guiRenderer.render(test);
		//dynamicGuis.addAll((bob.getWalkingAnimation(loader, 30).getFrame()));
		group1.move((int)milli,grid);
		dynamicGuis.addAll(group1.render());
		//guiRenderer.render(arrows.getFrame());
		
		
		
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
	
	public static void update() {
		updateMouse();
		//TODO Test traps
		//TODO Update Explorer AI
		//TODO Update player input such as placing traps
		//TODO Update ArrayLists of explorers, traps, Tiles, etc. if not already done so.
	}
	
	public static void updateMouse() {
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		if(grid.getGridButton().isClicked(mouseX, mouseY)) {
			epicShopofEpicness.setGridLoc(new Vector2f((float)((int)(mouseX-grid.getLoc().x)/(int)grid.getTileCount().x),
											(float)((int)(mouseY-grid.getLoc().y)/(int)grid.getTileCount().y)));
			epicShopofEpicness.setOn(true);
		}
		if(epicShopofEpicness.shopIsClicked(mouseX, mouseY)) {
			Tile selectedTrap = epicShopofEpicness.getShopItem(mouseX, mouseY);
			if(selectedTrap.getPrice()<=money) {
				grid.setTile((int)epicShopofEpicness.getPlacementLoc().x, (int)epicShopofEpicness.getPlacementLoc().y, selectedTrap);
			}
		}
		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
	}
}
	

