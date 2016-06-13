package librarys;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import renderEngine.Loader;

public class StringLibrary {
	//Numbers
	public static GuiTexture[] nums;
	
	public static Vector2f size;
	
	public static void init(Loader loader) {
		
		nums = new GuiTexture[10];
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

	}
	
	public static ArrayList<GuiTexture> drawString(String s, Vector2f loc) {
		ArrayList<GuiTexture> string = new ArrayList<GuiTexture>();
		for(int i = 0; i<s.length(); i++) {
			char letter = s.charAt(i);
			string.add(new GuiTexture(getLetter(letter), new Vector2f(loc.x+i*size.x, loc.y+i*size.y), new Vector2f(size.x,size.y)));
		}
		return string;
	}
	
	public static int getLetter(char c) {
		//Return number textures
		if(c == '0') 
			return TextureLibrary.Num0;
		if(c == '1') 
			return TextureLibrary.Num1;
		if(c == '2') 
			return TextureLibrary.Num2;
		if(c == '3') 
			return TextureLibrary.Num3;
		if(c == '4') 
			return TextureLibrary.Num4;
		if(c == '5') 
			return TextureLibrary.Num5;
		if(c == '6') 
			return TextureLibrary.Num6;
		if(c == '7') 
			return TextureLibrary.Num7;
		if(c == '8') 
			return TextureLibrary.Num8;
		if(c == '9') 
			return TextureLibrary.Num9;
		
		
		//Return letter textures
		if(c == 'a') {
		//	return TextureLibrary.a;
		}
		
		//Else return -1 showing error
		return 0;
	}
	
	public static void setSize(Vector2f s) {
		size = s;
	}
}
