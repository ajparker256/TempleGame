package main;

import grid.Grid;
import gui.Animation;
import gui.GuiRenderer;
import gui.GuiTexture;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import entities.Camera;
import entities.Explorer;
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

	public static void main(String[] args) throws FileNotFoundException {

		money=1327;
		buttons = new ArrayList<Button>();
		group = new ArrayList<Explorer>();
		Camera camera = new Camera();
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Random random = new Random();
		new TextureLibrary(loader);
		Grid grid= new Grid(new Vector2f(0f,-1),0.05f,10);

		
		
		
		
		
	SoundLibrary.music = Sound.loadSound("song");
	
	StringLibrary.init(loader);
	
	
	
	List<GuiTexture> guis = new ArrayList<GuiTexture>();
	List<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	
	
	
	
	
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
	
	Explorer bob = new Explorer(20, new Vector2f(.5f,-.5f), new Vector2f(0, .1f), new Vector2f(.02f, .02f));
	
	
	//This is the string tester
	List<GuiTexture> test;
	StringLibrary.setSize(new Vector2f(.02f, .02f));
	test = StringLibrary.drawString("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", new Vector2f(-.98f,-.7f));
	test.addAll(StringLibrary.drawString("Hello World", new Vector2f(-.98f, .5f)));
	//Makes the background white
	guis.add(new GuiTexture(loader.loadTexture("White"), new Vector2f(.9f,-.9f), new Vector2f(2f, 2f)));
	
	
	

	//guis.add(new GuiTexture(loader.loadTexture("A"), new Vector2f(0f, 0f), new Vector2f(1f,1f)));
	for(int i = 0; i<test.size(); i++) {
		dynamicGuis.add(test.get(i));
	}
	

	milli = System.currentTimeMillis();
	while(!Display.isCloseRequested()){
		milli = System.currentTimeMillis() - milli;
		dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
		//enemy update stuff
		//Renders from TOP TO BOTTOM!
		//RENDERS FROM CENTER OF IMAGE! (90% certain)
		dynamicGuis.addAll(grid.render());
		guiRenderer.render(guis);
		guiRenderer.render(test);
		guiRenderer.render(bob.getWalkingAnimation(loader, 30).getFrame());
		bob.move((int)milli);
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
	
	public void update() {
		//TODO Test traps
		//TODO Update Explorer AI
		//TODO Update player input such as placing traps
		//TODO Update ArrayLists of explorers, traps, Tiles, etc. if not already done so.
	}
	
	public void updateMouse() {
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
	}
}
	

