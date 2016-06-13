package gui;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.Loader;

public class StringRenderer {
	
	//Capital N Num+actual num .png
	
	public static void initChars(Loader loader) {
		//StringLibrary.a = new GuiTexture (loader.loadTexture("a"),new Vector2f(0.025f,-0.025f), new Vector2f(0.2f,0.2f));
	}
	
	public static void drawString(String s, Vector2f loc) {
		for(int i = 0; i<s.length(); i++) {
			String letter = ""+s.charAt(i);
			//TODO Letter is drawn, gets size of letter, changes loc by that much then calls again minus the s. Recursion boyz.
		}
	}
}
