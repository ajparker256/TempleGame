package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import sound.Sound;

//Used to display to window, mostly done by openGL for you
public class DisplayManager {

	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	private static final int FPSCAP= 120;
	private static final float ASPECTRATIO=((float)WIDTH)/((float)HEIGHT);
	private static long lastFrameTime;
	private static float dTime;
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Insert name here");
			AL.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0,0,WIDTH,HEIGHT);
		lastFrameTime = getCurrentTime();
	}
		public DisplayManager(){
			
		}
	public static void updateDisplay(){
		Display.sync(FPSCAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		dTime = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
	}
	//Useful when referencing location within the window, or using libraries that reference the entire screen
	public static int getWidth(){
		return Display.getWidth();
	}
	public static int getHeight(){
		return Display.getHeight();
	}
	public static int getX(){
		return Display.getX();
	}
	public static int getY(){
		return Display.getY();
	}
	public static void closeDisplay(){
		//Sound.cleanUp();
		AL.destroy();
		Display.destroy();
	}
	public static float getdTime(){
		return dTime;
	}
	
	private static long getCurrentTime(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	public static double getAspectratio() {
		return ASPECTRATIO;
	}
		
	
	}
