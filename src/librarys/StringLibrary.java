package librarys;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import renderEngine.Loader;

public class StringLibrary {
	//Numbers
	public static GuiTexture[] nums;
	
	public static Vector2f size;
	
	public static void init(Loader loader) {
		
		size = new Vector2f(0.1f, 0.1f);
		
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
	
	public static void setSize(Vector2f s) {
		size = s;
	}
}
