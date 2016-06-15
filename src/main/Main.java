package main;

import grid.Grid;
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

	public static void main(String[] args) throws FileNotFoundException {

		money=1327;
		buttons = new ArrayList<Button>();
		group = new ArrayList<Explorer>();
		Camera camera = new Camera();
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Random random = new Random();
		Grid grid = new Grid(new Vector2f(0f,0f),new Vector2f(0.5f,0.5f),10);

		/*
	SoundLibrary.music = Sound.loadSound("song");
	SoundLibrary.gunSound1 = Sound.loadSound("shot");
	SoundLibrary.gunSound2 = Sound.loadSound("shot");
	SoundLibrary.gunSound3 = Sound.loadSound("shot");
	SoundLibrary.gunSound4= Sound.loadSound("shot");
	SoundLibrary.gunSound5 = Sound.loadSound("shot");
	SoundLibrary.gunSound6 = Sound.loadSound("shot");
	SoundLibrary.gunSound7 = Sound.loadSound("shot");
	SoundLibrary.gunSound8 = Sound.loadSound("shot");
	SoundLibrary.gunSound9= Sound.loadSound("shot");
	SoundLibrary.gunSound10= Sound.loadSound("shot");
	SoundLibrary.zombieSound = Sound.loadSound("splat");
	SoundLibrary.reloadSound = Sound.loadSound("reload");
	*/
	//gui
	/*TextureLibrary.Num1= loader.loadTexture("Num1");
	TextureLibrary.Num2= loader.loadTexture("Num2");
	TextureLibrary.Num3= loader.loadTexture("Num3");
	TextureLibrary.Num4= loader.loadTexture("Num4");
	TextureLibrary.Num5= loader.loadTexture("Num5");
	TextureLibrary.Num6= loader.loadTexture("Num6");
	TextureLibrary.Num7= loader.loadTexture("Num7");
	TextureLibrary.Num8= loader.loadTexture("Num8");
	TextureLibrary.Num9= loader.loadTexture("Num9");
	TextureLibrary.Num0= loader.loadTexture("Num0"); */
	
	StringLibrary.init(loader);
	
	List<GuiTexture> guis = new ArrayList<GuiTexture>();
	List<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	
	GuiLibrary.background = loader.loadTexture("background");
	//guis.add(new GuiTexture(GuiLibrary.background, new Vector2f(0, 0), new Vector2f(2f, 2f)));
	GuiLibrary.gun = new GuiTexture (loader.loadTexture("gun"),new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f));
//	guis.add(GuiLibrary.gun);
	GuiLibrary.crosshair = new GuiTexture (loader.loadTexture("crosshair"),new Vector2f(0.025f,-0.025f), new Vector2f(0.1f,0.1f));
//	guis.add(GuiLibrary.crosshair);
	GuiLibrary.bulletTexture=loader.loadTexture("bulletTexture");
	GuiLibrary.health=loader.loadTexture("test");
	
	
	//shop
	
	//GuiLibrary.shop = loader.loadTexture("shop");
	GuiLibrary.nextWave = loader.loadTexture("nextWave");
	boolean exit=false;
	GuiRenderer guiRenderer = new GuiRenderer(loader);
	Sound.loopSound(SoundLibrary.music);
	
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
	while(!Display.isCloseRequested()){
		dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
		//enemy update stuff
		//Renders from TOP TO BOTTOM!
		//RENDERS FROM CENTER OF IMAGE! (90% certain)
		guiRenderer.render(guis);
		guiRenderer.render(test);

		
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
	

