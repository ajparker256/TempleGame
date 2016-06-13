package main;

import gui.GuiRenderer;
import gui.GuiTexture;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import librarys.GuiLibrary;
import librarys.SoundLibrary;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;


import entities.Camera;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import sound.Sound;
//Main game loop
public class Main {
	//Amount of ambient light
public static final float ambient = 0.5f;
private static float spawnRate=0;
private static int sides=1;

	public static void main(String[] args) throws FileNotFoundException {
		
	Camera camera = new Camera();
	DisplayManager.createDisplay();
	Loader loader = new Loader();
	Random random = new Random();

//yarrrrrr

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

	List<GuiTexture> guis = new ArrayList<GuiTexture>();
	List<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	GuiLibrary.gun = new GuiTexture (loader.loadTexture("gun"),new Vector2f(0.8f,-0.8f), new Vector2f(0.8f,0.8f));
	guis.add(GuiLibrary.gun);
	GuiLibrary.crosshair = new GuiTexture (loader.loadTexture("crosshair"),new Vector2f(0.025f,-0.025f), new Vector2f(0.2f,0.2f));
	guis.add(GuiLibrary.crosshair);
	GuiLibrary.bulletTexture=loader.loadTexture("bulletTexture");
	GuiLibrary.health=loader.loadTexture("test");
	//shop
	GuiLibrary.background = loader.loadTexture("background");
	GuiLibrary.shop = loader.loadTexture("shop");
	GuiLibrary.nextWave = loader.loadTexture("nextWave");
	boolean exit=false;
	GuiRenderer guiRenderer = new GuiRenderer(loader);

	

	//
	//player
	//
	 
	
	Sound.loopSound(SoundLibrary.music);
	while(!Display.isCloseRequested()){

		
		

		//enemy update stuff
		guiRenderer.render(guis);
		guiRenderer.render(dynamicGuis);
		DisplayManager.updateDisplay();
		dynamicGuis.clear();
		
		
		
		
			
		
	}
	
	guiRenderer.cleanUp();
	loader.cleanUp();
	DisplayManager.closeDisplay();
		
	}
}
	

