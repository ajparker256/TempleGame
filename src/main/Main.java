package main;

import gui.GuiRenderer;
import gui.GuiTexture;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import librarys.GuiLibrary;
import librarys.SoundLibrary;
import librarys.TextureLibrary;

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
public static int money;

	public static void main(String[] args) throws FileNotFoundException {
		money=1327;
		
	Camera camera = new Camera();
	DisplayManager.createDisplay();
	Loader loader = new Loader();
	Random random = new Random();

//BlueLadybug
	

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
	TextureLibrary.Num1= loader.loadTexture("Num1");
	TextureLibrary.Num2= loader.loadTexture("Num2");
	TextureLibrary.Num3= loader.loadTexture("Num3");
	TextureLibrary.Num4= loader.loadTexture("Num4");
	TextureLibrary.Num5= loader.loadTexture("Num5");
	TextureLibrary.Num6= loader.loadTexture("Num6");
	TextureLibrary.Num7= loader.loadTexture("Num7");
	TextureLibrary.Num8= loader.loadTexture("Num8");
	TextureLibrary.Num9= loader.loadTexture("Num9");
	TextureLibrary.Num0= loader.loadTexture("Num0");
	List<GuiTexture> guis = new ArrayList<GuiTexture>();
	List<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	GuiLibrary.gun = new GuiTexture (loader.loadTexture("gun"),new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f));
//	guis.add(GuiLibrary.gun);
	GuiLibrary.crosshair = new GuiTexture (loader.loadTexture("crosshair"),new Vector2f(0.025f,-0.025f), new Vector2f(0.1f,0.1f));
//	guis.add(GuiLibrary.crosshair);
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
		if(money%10==0){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num0, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==1){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num1, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==2){
		
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num2, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==3){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num3, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==4){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num4, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==5){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num5, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==6){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num6, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==7){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num7, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==8){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num8, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}else 	if(money%10==9){
			dynamicGuis.add(new GuiTexture(TextureLibrary.Num9, new Vector2f(0.9f,-0.9f), new Vector2f(0.1f,0.1f)));
		}
		if((money%100)-money%10==0){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num0, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==10){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num1, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==20){
			
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num2, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==30){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num3, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==40){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num4, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==50){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num5, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==60){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num6, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==70){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num7, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==80){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num8, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%100)-money%10==90){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num9, new Vector2f(0.8f,-0.9f), new Vector2f(0.1f,0.1f)));
			}
		
			if((money%1000)-money%100-money%10==0){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num0, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==100){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num1, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==200){
			
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num2, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==300){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num3, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==400){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num4, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==500){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num5, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==600){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num6, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==700){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num7, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==800){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num8, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%1000)-money%100==900){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num9, new Vector2f(0.7f,-0.9f), new Vector2f(0.1f,0.1f)));
			}

			if((money%10000)-money%100-money%10==0){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num0, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==1000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num1, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==2000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num2, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==3000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num3, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==4000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num4, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==5000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num5, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==6000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num6, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==7000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num7, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==8000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num8, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}else 	if((money%10000)-money%1000==9000){
				dynamicGuis.add(new GuiTexture(TextureLibrary.Num9, new Vector2f(0.6f,-0.9f), new Vector2f(0.1f,0.1f)));
			}
		
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
	

